
package acme.features.manager.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.project.Assignment;
import acme.features.manager.project.ManagerProjectRepository;
import acme.roles.Manager;

@Service
public class ManagerAssignmentDeleteService extends AbstractService<Manager, Assignment> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int id = super.getRequest().getData("id", int.class);
		Manager manager = this.repository.findManagerProjectByAssignmentId(id);
		status = super.getRequest().getPrincipal().getActiveRoleId() == manager.getId();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Assignment assignment;
		int id;

		id = super.getRequest().getData("id", int.class);
		assignment = this.repository.findAssignmentById(id);

		super.getBuffer().addData(assignment);
	}

	@Override
	public void bind(final Assignment assignment) {
		assert assignment != null;

		super.bind(assignment, "project.title", "userStory");
	}

	@Override
	public void validate(final Assignment assignment) {
		boolean updateable = this.repository.findProjectOfAnAssignmentByAssignmentId(assignment.getId()).isDraft();

		if (!super.getBuffer().getErrors().hasErrors("project"))
			super.state(updateable, "*", "manager.project.form.updateable");

	}

	@Override
	public void perform(final Assignment assignment) {
		assert assignment != null;

		this.repository.delete(assignment);
	}

}
