
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
		Collection<Contract> contracts;
		contracts = this.repository.findAllContractsOfAProjectById(contract.getProject().getId());
		double budget = contracts.stream().mapToDouble(c -> c.getBudget().getAmount()).sum();
		super.state(budget < contract.getProject().getCost().getAmount(), "*", "client.contract.form.error.validBudget");

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
		boolean contractPublishables;
		boolean isDraft;

		contractPublishables = this.repository.findAllContractsOfAClientById(contract.getId()).stream().allMatch(x -> x.isDraft() == false) && this.repository.findAllContractsOfAClientById(contract.getId()).size() > 0;
		isDraft = contract.isDraft() == true;

		Dataset dataset;

		dataset = super.unbind(contract, "code", "moment", "providerName", "customerName", "goals", "budget", "isDraft", "project");
		dataset.put("contractId", contract.getId());
		dataset.put("publishable", contractPublishables);
		dataset.put("isDraft", isDraft);

		super.getResponse().addData(dataset);
	}

}
