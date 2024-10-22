
package acme.features.client.progresslog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.contract.Contract;
import acme.entities.contract.ProgressLog;
import acme.roles.Client;

@Service
public class ClientProgressLogPublishService extends AbstractService<Client, ProgressLog> {

	@Autowired
	private ClientProgressLogRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int progressLogId;
		Contract contract;
		ProgressLog progressLog;
		progressLogId = super.getRequest().getData("id", int.class);
		contract = this.repository.findOneContractByProgressLogId(progressLogId);
		progressLog = this.repository.findOneProgressLogById(progressLogId);
		status = super.getRequest().getPrincipal().getActiveRoleId() == contract.getClient().getId() && progressLog != null && progressLog.isDraft() && contract != null && super.getRequest().getPrincipal().hasRole(Client.class);

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

		super.bind(object, "recordId", "completeness", "comment", "registrationMoment", "reponsiblePerson", "isDraft");
	}

	@Override
	public void validate(final ProgressLog object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("recordId")) {
			ProgressLog existing;
			existing = this.repository.findOneProgressLogByCode(object.getRecordId());
			super.state(existing == null || existing.equals(object), "recordId", "client.progresslog.form.error.duplicated");
		}
		if (!super.getBuffer().getErrors().hasErrors("completeness")) {

			Double objectCompleteness = object.getCompleteness();
			List<ProgressLog> pls = this.repository.findBefore(object.getContract().getId());
			ProgressLog lastVersion = this.repository.findOneProgressLogById(object.getId());
			Double lastCompleteness = pls.get(0).getCompleteness();
			boolean condition = objectCompleteness.equals(lastVersion.getCompleteness()) || objectCompleteness > lastCompleteness;
			super.state(condition && objectCompleteness < 100, "completeness", "client.progresslog.form.error.completeness");
		}

	}

	@Override
	public void perform(final ProgressLog progresslog) {
		assert progresslog != null;

		progresslog.setDraft(false);

		this.repository.save(progresslog);
	}

	@Override
	public void unbind(final ProgressLog object) {
		assert object != null;
		Dataset dataset;

		dataset = super.unbind(object, "recordId", "completeness", "comment", "registrationMoment", "reponsiblePerson", "isDraft");

		dataset.put("isDraft", object.isDraft());

		super.getResponse().addData(dataset);
	}

}
