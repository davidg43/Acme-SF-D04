
package acme.features.client.progresslog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.contract.ProgressLog;
import acme.roles.Client;

@Service
public class ClientProgressLogPublishService extends AbstractService<Client, ProgressLog> {

	@Autowired
	private ClientProgressLogRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int masterId;
		ProgressLog progressLog;
		masterId = super.getRequest().getData("id", int.class);
		progressLog = this.repository.findOneProgressLogById(masterId);
		status = progressLog != null && progressLog.isDraft() && super.getRequest().getPrincipal().hasRole(Client.class) && progressLog.getContract().getClient().getId() == super.getRequest().getPrincipal().getActiveRoleId();

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

	}

	@Override
	public void perform(final ProgressLog progresslog) {
		assert progresslog != null;

		progresslog.setDraft(false);

		this.repository.save(progresslog);
	}

	////	@Override
	////	public void unbind(final ProgressLog object) {
	////		//		assert object != null;
	////		//		Dataset dataset;
	////		//
	////		//		dataset = super.unbind(object, "recordId", "completeness", "comment", "registrationMoment", "reponsiblePerson", "isDraft");
	////		//
	////		//		dataset.put("isDraft", object.isDraft());
	////		//
	////		//		super.getResponse().addData(dataset);
	//	}

}
