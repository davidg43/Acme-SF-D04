
package acme.features.client.contract;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.contract.Contract;
import acme.entities.project.Project;
import acme.roles.Client;

@Service
public class ClientContractDeleteService extends AbstractService<Client, Contract> {

	@Autowired
	private ClientContractRepository repository;


	@Override
	public void authorise() {
		boolean status;
		Contract contract;
		int id = super.getRequest().getData("id", int.class);
		contract = this.repository.findContractById(id);
		status = contract != null && contract.isDraft() && super.getRequest().getPrincipal().hasRole(Client.class) && contract.getClient().getId() == super.getRequest().getPrincipal().getActiveRoleId();
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

		super.bind(contract, "code", "moment", "providerName", "customerName", "goals", "budget", "isDraft");
	}

	@Override
	public void validate(final Contract contract) {
		assert contract != null;
	}

	@Override
	public void perform(final Contract contract) {
		assert contract != null;
		this.repository.deleteAll(this.repository.findAllProgressLogsByContractId(contract.getId()));
		this.repository.delete(contract);

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
