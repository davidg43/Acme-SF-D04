
package acme.features.client.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.contract.Contract;
import acme.roles.Client;

@Service
public class ClientContractDeleteService extends AbstractService<Client, Contract> {

	@Autowired
	private ClientContractRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int id = super.getRequest().getData("id", int.class);
		Client client = this.repository.findClientByContractId(id);
		status = super.getRequest().getPrincipal().getActiveRoleId() == client.getId();
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

}
