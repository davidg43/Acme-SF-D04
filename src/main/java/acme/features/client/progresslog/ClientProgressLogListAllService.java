
package acme.features.client.progresslog;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.contract.ProgressLog;
import acme.roles.Client;

@Service
public class ClientProgressLogListAllService extends AbstractService<Client, ProgressLog> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ClientProgressLogRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(super.getRequest().getPrincipal().hasRole(Client.class));
	}

	@Override
	public void load() {
		Collection<ProgressLog> objects;
		int masterId;
		masterId = super.getRequest().getPrincipal().getActiveRoleId();
		objects = this.repository.findAllProgressOfClient(masterId);
		super.getBuffer().addData(objects);

	}

	@Override
	public void unbind(final ProgressLog object) {
		assert object != null;
		Dataset dataset;
		dataset = super.unbind(object, "recordId", "contract", "completeness");
		super.getResponse().addData(dataset);
	}

}
