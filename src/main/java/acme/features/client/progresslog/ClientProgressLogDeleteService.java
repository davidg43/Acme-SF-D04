
package acme.features.client.progresslog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.contract.Contract;
import acme.entities.contract.ProgressLog;
import acme.roles.Client;

@Service
public class ClientProgressLogDeleteService extends AbstractService<Client, ProgressLog> {

	@Autowired
	private ClientProgressLogRepository repository;


	@Override
	public void authorise() {
		boolean status;
		ProgressLog progressLog;
		Contract contract;
		Client client;
		int id = super.getRequest().getData("id", int.class);
		progressLog = this.repository.findOneProgressLogById(id);
		contract = progressLog == null ? null : progressLog.getContract();
		client = contract == null ? null : contract.getClient();
		status = progressLog != null && progressLog.isDraft() && contract != null && contract.isDraft() && super.getRequest().getPrincipal().hasRole(client) && contract.getClient().getId() == super.getRequest().getPrincipal().getActiveRoleId();
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
	}

	@Override
	public void perform(final ProgressLog object) {
		assert object != null;

		this.repository.delete(object);
	}

	//	@Override
	//	public void unbind(final ProgressLog object) {
	//		assert object != null;
	//		Dataset dataset;
	// 
	//
	//		dataset = super.unbind(object, "recordId", "contract", "completeness", "comment", "registrationMoment", "reponsiblePerson");
	//		dataset.put("contracts", contractChoices);
	//
	//		super.getResponse().addData(dataset);
	//	}
}
