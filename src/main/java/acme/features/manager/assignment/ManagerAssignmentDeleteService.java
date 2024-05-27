
package acme.features.manager.assignment;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.project.Assignment;
import acme.entities.project.Project;
import acme.entities.project.UserStory;
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
		Manager manager = this.repository.findProjectByAssignmentId(id);
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

		super.bind(assignment, "project", "userStory");
	}

	@Override
	public void validate(final Assignment assignment) {
		boolean updateable = this.repository.findProjectOfAnAssignmentByAssignmentId(assignment.getId()).isDraft(); // Si true == updateable

		if (!super.getBuffer().getErrors().hasErrors("project"))
			super.state(updateable, "*", "manager.project.form.updateable");

	}

	@Override
	public void perform(final Assignment assignment) {
		assert assignment != null;

		this.repository.delete(assignment);
	}

	@Override
	public void unbind(final Assignment assignment) {
		assert assignment != null;

		Dataset dataset;

		int id = super.getRequest().getPrincipal().getActiveRoleId();
		SelectChoices projectChoices;
		SelectChoices userStoriesChoices;

		Collection<Project> projects = this.repository.findAllProjectsByManagerId(id);
		Collection<UserStory> userStories = this.repository.findAllUserStoriesOfAManagerById(id);
		boolean updateable = this.repository.findProjectOfAnAssignmentByAssignmentId(assignment.getId()).isDraft(); // Si true == updateable

		projectChoices = SelectChoices.from(projects, "title", assignment.getProject());
		userStoriesChoices = SelectChoices.from(userStories, "title", assignment.getUserStory());

		dataset = super.unbind(assignment, "project", "userStory");

		dataset.put("projects", projectChoices);
		dataset.put("userStories", userStoriesChoices);
		dataset.put("updateable", updateable);

		super.getResponse().addData(dataset);
	}

}
