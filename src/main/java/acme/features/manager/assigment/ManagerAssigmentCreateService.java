
package acme.features.manager.assigment;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.project.Assigment;
import acme.entities.project.Project;
import acme.entities.project.UserStory;
import acme.features.manager.project.ManagerProjectRepository;
import acme.roles.Manager;

@Service
public class ManagerAssigmentCreateService extends AbstractService<Manager, Assigment> {

	@Autowired
	private ManagerProjectRepository repository;


	@Override
	public void authorise() {
		boolean status = super.getRequest().getPrincipal().hasRole(Manager.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {

		Assigment assigment = new Assigment();

		super.getBuffer().addData(assigment);

	}

	@Override
	public void bind(final Assigment assigment) {
		assert assigment != null;

		super.bind(assigment, "project", "userStory");
	}

	@Override
	public void validate(final Assigment assigment) {
		assert assigment != null;

		if (!super.getBuffer().getErrors().hasErrors("project"))
			super.state(!assigment.getProject().isHasFatalErrors(), "project", "manager.project.form.error.fatal-errors");

	}

	@Override
	public void perform(final Assigment assigment) {
		assert assigment != null;

		this.repository.save(assigment);
	}

	@Override
	public void unbind(final Assigment assigment) {
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
