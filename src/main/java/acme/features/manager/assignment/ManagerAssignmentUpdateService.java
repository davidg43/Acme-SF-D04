
package acme.features.manager.assignment;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.project.Assignment;
import acme.entities.project.UserStory;
import acme.features.manager.project.ManagerProjectRepository;
import acme.roles.Manager;

@Service
public class ManagerAssignmentUpdateService extends AbstractService<Manager, Assignment> {

	@Autowired
	private ManagerProjectRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int assigmentId = super.getRequest().getData("id", int.class);
		Manager manager = this.repository.findManagerProjectByAssignmentId(assigmentId);
		status = manager != null && super.getRequest().getPrincipal().hasRole(Manager.class) && manager.getId() == super.getRequest().getPrincipal().getActiveRoleId();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {

		int id = super.getRequest().getData("id", int.class);
		Assignment assigment = this.repository.findAssignmentById(id);

		super.getBuffer().addData(assigment);

	}

	@Override
	public void bind(final Assignment assigment) {
		assert assigment != null;

		super.bind(assigment, "project.title", "userStory");
	}

	@Override
	public void validate(final Assignment assigment) {
		assert assigment != null;
		boolean updateable = this.repository.findProjectOfAnAssignmentByAssignmentId(assigment.getId()).isDraft();

		if (!super.getBuffer().getErrors().hasErrors("userStory")) {
			boolean condition = assigment.getProject().getManager().getId() == assigment.getUserStory().getManager().getId();
			super.state(condition, "*", "manager.project.form.error.ownership");
		}

		if (!super.getBuffer().getErrors().hasErrors("project")) {
			super.state(updateable, "*", "manager.project.form.updateable");
			super.state(assigment.getProject().isDraft() == true, "*", "manager.project.form.create-denied");
		}

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
		SelectChoices userStoriesChoices;
		Collection<UserStory> userStories = this.repository.findAllUserStoriesOfAManagerById(id);

		userStoriesChoices = SelectChoices.from(userStories, "title", assigment.getUserStory());

		dataset = super.unbind(assigment, "project.title", "userStory");

		dataset.put("masterId", assigment.getProject().getId());
		dataset.put("userStories", userStoriesChoices);

		super.getResponse().addData(dataset);
	}

}
