
package acme.features.client.contract;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.contract.Contract;
import acme.entities.project.Project;
import acme.roles.Client;

@Service
public class ClientContractCreateService extends AbstractService<Client, Contract> {

	@Autowired
	private ClientContractRepository repository;


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(super.getRequest().getPrincipal().hasRole(Client.class));
	}

	@Override
	public void load() {
		Contract object;
		Client client;

		client = this.repository.findClientByClientId(super.getRequest().getPrincipal().getActiveRoleId());
		object = new Contract();
		object.setDraft(true);
		object.setClient(client);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Contract contract) {
		assert contract != null;
		int projectId;
		Project project;
		projectId = super.getRequest().getData("project", int.class);
		project = this.repository.findOneProjectById(projectId);
		Date currentMoment = MomentHelper.getCurrentMoment();
		Date moment = new Date(currentMoment.getTime());
		super.bind(contract, "code", "moment", "providerName", "customerName", "goals", "budget", "isDraft", "project");
		contract.setProject(project);
		contract.setMoment(moment);
	}

	@Override
	public void validate(final Contract contract) {
		assert contract != null;
		Collection<Contract> contracts = null;
		if (!super.getBuffer().getErrors().hasErrors("code")) {
			contracts = this.repository.findAllContractsOfAClientById(contract.getClient().getId());
			boolean condition = contracts.contains(contract);
			super.state(!condition, "code", "client.contract.form.error.duplicated");
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

		this.repository.save(contract);
	}

	@Override
	public void unbind(final Contract contract) {
		assert contract != null;
		boolean isDraft;
		Collection<Project> projects;
		projects = this.repository.findAllPublishedProjects();
		SelectChoices choices;
		choices = SelectChoices.from(projects, "title", contract.getProject());
		isDraft = contract.isDraft() == true;

		Dataset dataset;

		dataset = super.unbind(contract, "code", "moment", "providerName", "customerName", "goals", "budget", "isDraft", "project");
		dataset.put("contractId", contract.getId());
		dataset.put("isDraft", isDraft);
		dataset.put("project", choices.getSelected().getKey());
		dataset.put("projects", choices);

		super.getResponse().addData(dataset);
	}

}
