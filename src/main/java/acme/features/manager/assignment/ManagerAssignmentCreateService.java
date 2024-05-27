
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
public class ManagerAssignmentCreateService extends AbstractService<Manager, Assignment> {

	@Autowired
	private ManagerProjectRepository repository;


	@Override
	public void authorise() {
		boolean status = super.getRequest().getPrincipal().hasRole(Manager.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {

		Assignment assigment = new Assignment();

		super.getBuffer().addData(assigment);

	}

	@Override
	public void bind(final Assignment assigment) {
		assert assigment != null;

		super.bind(assigment, "project", "userStory");
	}

	@Override
	public void validate(final Assignment assigment) {
		assert assigment != null;

		if (!super.getBuffer().getErrors().hasErrors("project"))
			super.state(!assigment.getProject().isHasFatalErrors(), "project", "manager.project.form.error.fatal-errors");

		if (!super.getBuffer().getErrors().hasErrors("project"))
			super.state(assigment.getProject().isDraft(), "*", "manager.project.form.create-denied");
	}

	@Override
	public void perform(final Assignment assigment) {
		assert assigment != null;

		this.repository.save(assigment);
	}

	@Override
	public void unbind(final Assignment assigment) {
		assert assigment != null;

		Dataset dataset;

		int id = super.getRequest().getPrincipal().getActiveRoleId();
		SelectChoices projectChoices;
		SelectChoices userStoriesChoices;

		Collection<Project> projects = this.repository.findAllProjectsByManagerId(id);
		Collection<UserStory> userStories = this.repository.findAllUserStoriesOfAManagerById(id);

		projectChoices = SelectChoices.from(projects, "title", assigment.getProject());
		userStoriesChoices = SelectChoices.from(userStories, "title", assigment.getUserStory());

		dataset = super.unbind(assigment, "project", "userStory");

		dataset.put("projects", projectChoices);
		dataset.put("userStories", userStoriesChoices);

		super.getResponse().addData(dataset);
	}

}
