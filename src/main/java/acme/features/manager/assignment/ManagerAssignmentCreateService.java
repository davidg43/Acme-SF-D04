
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
		boolean status;
		int projectId = super.getRequest().getData("masterId", Integer.class);
		Manager manager = this.repository.findManagerByProjectId(projectId);
		status = manager != null && super.getRequest().getPrincipal().hasRole(Manager.class) && manager.getId() == super.getRequest().getPrincipal().getActiveRoleId();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {

		Assignment assigment = new Assignment();
		Integer id;

		id = super.getRequest().getData("masterId", int.class);
		Project p = this.repository.findProjectById(id);

		assigment.setProject(p);

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

		if (!super.getBuffer().getErrors().hasErrors("userStory")) {
			boolean condition = assigment.getProject().getManager().getId() == assigment.getUserStory().getManager().getId();
			super.state(condition, "*", "manager.project.form.error.ownership");
		}

		if (!super.getBuffer().getErrors().hasErrors("project"))
			super.state(assigment.getProject().isDraft() == true, "*", "manager.project.form.create-denied");

		if (!super.getBuffer().getErrors().hasErrors("userStory")) {
			int masterId = assigment.getProject().getId();
			Collection<UserStory> us = this.repository.findAllUserStoriesOfAProjectById(masterId);
			super.state(!us.contains(assigment.getUserStory()), "userStory", "manager.project.form.UsDuplicated");
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

		int masterId = super.getRequest().getData("masterId", int.class);

		dataset.put("masterId", masterId);
		dataset.put("userStories", userStoriesChoices);

		super.getResponse().addData(dataset);
	}

}
