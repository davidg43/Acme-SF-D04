
package acme.features.manager.assigment;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.project.Assigment;
import acme.features.manager.project.ManagerProjectRepository;
import acme.roles.Manager;

@Service
public class ManagerAssigmentListService extends AbstractService<Manager, Assigment> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int id;

		id = super.getRequest().getData("projectId", int.class);
		Manager manager = this.repository.findManagerByProjectId(id);
		status = super.getRequest().getPrincipal().getActiveRoleId() == manager.getId();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		int id = super.getRequest().getData("projectId", int.class);
		Collection<Assigment> objects = this.repository.findAllAssigmentsOfAProjectById(id);
		super.getBuffer().addData(objects);
	}

	@Override
	public void unbind(final Assigment assigment) {
		assert assigment != null;

		Dataset dataset;

		dataset = super.unbind(assigment, "project", "userStory");

		super.getResponse().addData(dataset);
	}

}
