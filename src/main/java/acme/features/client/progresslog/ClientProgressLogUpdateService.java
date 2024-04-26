
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
public class ClientProgressLogUpdateService extends AbstractService<Client, ProgressLog> {

	@Autowired
	private ClientProgressLogRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int progressLogId;
		Contract contract;

		progressLogId = super.getRequest().getData("id", int.class);
		contract = this.repository.findOneContractByProgressLogId(progressLogId);
		status = contract != null && contract.isDraft() && super.getRequest().getPrincipal().hasRole(contract.getClient());

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
	public void bind(final ProgressLog object) {
		assert object != null;

		super.bind(object, "recordId", "contract", "completeness", "comment", "registrationMoment", "reponsiblePerson");
	}

	@Override
	public void validate(final ProgressLog object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("recordId")) {
			ProgressLog existing;

			existing = this.repository.findOneProgressLogByCode(object.getRecordId());

			super.state(existing == null || existing.equals(object), "recordId", "client.progresslog.form.error.duplicated");
		}
	}

	@Override
	public void perform(final ProgressLog progresslog) {
		assert progresslog != null;

		this.repository.save(progresslog);
	}

	@Override
	public void unbind(final ProgressLog object) {
		assert object != null;
		Dataset dataset;

		SelectChoices contractChoices;
		Collection<Contract> contracts = this.repository.findAllContractsByClientId(super.getRequest().getPrincipal().getActiveRoleId());

		contractChoices = SelectChoices.from(contracts, "code", object.getContract());

		dataset = super.unbind(object, "recordId", "contract", "completeness", "comment", "registrationMoment", "reponsiblePerson");
		dataset.put("contracts", contractChoices);

		super.getResponse().addData(dataset);
	}
}
