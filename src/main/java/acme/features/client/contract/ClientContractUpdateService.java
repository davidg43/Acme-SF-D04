
package acme.features.client.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.contract.Contract;
import acme.roles.Client;

@Service
public class ClientContractUpdateService extends AbstractService<Client, Contract> {

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

		super.bind(contract, "code", "moment", "providerName", "customerName", "goals", "budget", "isDraft", "project");
	}

	@Override
	public void validate(final Contract contract) {
		assert contract != null;
		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Contract existing;

			existing = this.repository.findOneContractByCode(contract.getCode());

			super.state(existing == null || existing.equals(contract), "recordId", "client.progresslog.form.error.duplicated");
		}
		if (!super.getBuffer().getErrors().hasErrors("budget"))
			super.state(contract.getBudget().getAmount() >= 0, "budget", "client.contract.form.error.negative-budget");

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
		SelectChoices choices;

		choices = SelectChoices.from(this.repository.findAllPublishedProjects(), "title", contract.getProject());
		isDraft = contract.isDraft() == true;

		Dataset dataset;

		dataset = super.unbind(contract, "code", "moment", "providerName", "customerName", "goals", "budget", "isDraft", "project");
		dataset.put("contractId", contract.getId());
		dataset.put("isDraft", isDraft);
		dataset.put("projects", choices);

		super.getResponse().addData(dataset);
	}

}
