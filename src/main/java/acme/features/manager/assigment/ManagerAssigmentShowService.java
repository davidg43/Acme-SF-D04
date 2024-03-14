
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
public class ManagerAssigmentShowService extends AbstractService<Manager, Assigment> {

	@Autowired
	private ManagerProjectRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int id = super.getRequest().getData("id", int.class);
		Manager manager = this.repository.findProjectByAssigmentId(id);
		status = super.getRequest().getPrincipal().getActiveRoleId() == manager.getId();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {

		Assigment assigment;
		int id;

		id = super.getRequest().getData("id", int.class);
		assigment = this.repository.findAssigmentById(id);

		super.getBuffer().addData(assigment);

	}

	@Override
	public void unbind(final acme.entities.project.Assigment assigment) {
		assert assigment != null;
		int id = super.getRequest().getPrincipal().getActiveRoleId();
		SelectChoices projectChoices;
		SelectChoices userStoriesChoices;

		Collection<Project> projects = this.repository.findAllProjectsByManagerId(id);
		Collection<UserStory> userStories = this.repository.findAllUserStoriesOfAManagerById(id);

		Dataset dataset;

		projectChoices = SelectChoices.from(projects, "title", assigment.getProject());
		userStoriesChoices = SelectChoices.from(userStories, "title", assigment.getUserStory());

		dataset = super.unbind(assigment, "project", "userStory");

		dataset.put("projects", projectChoices);
		dataset.put("userStories", userStoriesChoices);

		super.getResponse().addData(dataset);

	}

}
