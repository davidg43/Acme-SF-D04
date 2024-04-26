
package acme.features.client.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.contract.Contract;
import acme.roles.Client;

@Service
public class ClientContractShowService extends AbstractService<Client, Contract> {

	@Autowired
	private ClientContractRepository repository;


	@Override
	public void authorise() {
		boolean status;
		Contract contract;
		int masterId;
		Client client;

		masterId = super.getRequest().getData("id", int.class);
		contract = this.repository.findContractById(masterId);
		client = this.repository.findClientByContractId(masterId);

		status = super.getRequest().getPrincipal().hasRole(client) && contract != null;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("id", int.class);
		Contract contract = this.repository.findContractById(id);

		super.getBuffer().addData(contract);
	}

	@Override
	public void unbind(final Contract contract) {
		assert contract != null;
		boolean isDraft;
		SelectChoices choices;

		choices = SelectChoices.from(this.repository.findAllProjects(), "title", contract.getProject());
		isDraft = contract.isDraft() == true;

		Dataset dataset;

		dataset = super.unbind(contract, "code", "moment", "providerName", "customerName", "goals", "budget", "isDraft", "project");
		dataset.put("contractId", contract.getId());
		dataset.put("isDraft", isDraft);
		dataset.put("projects", choices);

		super.getResponse().addData(dataset);
	}

}
