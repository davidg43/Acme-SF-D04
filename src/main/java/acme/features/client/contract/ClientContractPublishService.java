
package acme.features.client.contract;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.contract.Contract;
import acme.entities.project.Project;
import acme.roles.Client;

@Service
public class ClientContractPublishService extends AbstractService<Client, Contract> {

	@Autowired
	private ClientContractRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int masterId;
		Contract contract;
		Client client;

		masterId = super.getRequest().getData("id", int.class);
		contract = this.repository.findContractById(masterId);
		client = this.repository.findClientByContractId(masterId);
		status = contract != null && contract.isDraft() && super.getRequest().getPrincipal().hasRole(Client.class) && contract.getClient().equals(client);

		super.getResponse().setAuthorised(status);

	}

	@Override
	public void load() {
		Contract contract;
		int id;

		id = super.getRequest().getData("id", int.class);
		contract = this.repository.findContractById(id);

		super.getBuffer().addData(contract);
	}

	@Override
	public void bind(final Contract contract) {
		assert contract != null;
		int projectId;
		Project project;

		projectId = super.getRequest().getData("project", int.class);
		project = this.repository.findOneProjectById(projectId);

		super.bind(contract, "isDraft");
		contract.setProject(project);
	}

	@Override
	public void validate(final Contract contract) {
		assert contract != null;
		Collection<Contract> contracts = null;
		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Contract existing;

			existing = this.repository.findOneContractByCode(contract.getCode());

			super.state(existing == null || existing.equals(contract), "recordId", "client.progresslog.form.error.duplicated");
		}
		if (!super.getBuffer().getErrors().hasErrors("budget"))
			super.state(contract.getBudget().getAmount() >= 0, "budget", "client.contract.form.error.negative-budget");
		if (!super.getBuffer().getErrors().hasErrors("budget")) {
			contracts = this.repository.findAllContractsOfAClientById(contract.getClient().getId());
			double totalBudget = contracts.stream().mapToDouble(c -> c.getBudget().getAmount()).sum();
			double projectCost = contract.getProject().getCost().getAmount();
			totalBudget += contract.getBudget().getAmount();
			super.state(totalBudget <= projectCost, "budget", "client.contract.form.error.exceeds-project-cost");
		}

	}

	@Override
	public void perform(final Contract contract) {
		assert contract != null;

		contract.setDraft(false);
		this.repository.save(contract);
	}
	@Override
	public void unbind(final Contract contract) {
		assert contract != null;
		boolean isDraft;

		isDraft = contract.isDraft() == true;

		Dataset dataset;

		dataset = super.unbind(contract, "code", "moment", "providerName", "customerName", "goals", "budget", "isDraft", "project");

		dataset.put("isDraft", isDraft);

		super.getResponse().addData(dataset);
	}

}
