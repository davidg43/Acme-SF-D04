
package acme.features.client.progresslog;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.contract.Contract;
import acme.entities.contract.ProgressLog;
import acme.roles.Client;

@Service
public class ClientProgressLogShowService extends AbstractService<Client, ProgressLog> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ClientProgressLogRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int id;
		Contract contract;

		id = super.getRequest().getData("id", int.class);
		contract = this.repository.findOneContractByProgressLogId(id);
		status = contract != null && (!contract.isDraft() || super.getRequest().getPrincipal().hasRole(contract.getClient()));

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		ProgressLog object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneProgressLogById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void unbind(final ProgressLog object) {
		assert object != null;
		Dataset dataset;

		SelectChoices contractChoices;
		Collection<Contract> contracts = this.repository.findAllContractsByClientId(super.getRequest().getPrincipal().getActiveRoleId());

		contractChoices = SelectChoices.from(contracts, "code", object.getContract());

		dataset = super.unbind(object, "recordId", "contract", "completeness", "comment", "registrationMoment", "reponsiblePerson", "isDraft");
		dataset.put("contracts", contractChoices);

		dataset.put("masterId", object.getContract().getId());
		dataset.put("draftMode", object.getContract().isDraft());

		super.getResponse().addData(dataset);
	}

}
