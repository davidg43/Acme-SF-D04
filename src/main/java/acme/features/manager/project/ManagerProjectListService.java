
package acme.features.manager.project;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.project.Project;
import acme.roles.Manager;

@Service
public class ManagerProjectListService extends AbstractService<Manager, Project> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(super.getRequest().getPrincipal().hasRole(Manager.class));
	}

	@Override
	public void load() {
		int id = super.getRequest().getPrincipal().getActiveRoleId();
		Collection<Project> objects = this.repository.findProjectsOfAManagerById(id);

		super.getBuffer().addData(objects);
	}

	@Override
	public void unbind(final Project object) {
		assert object != null;
		boolean userStoriesPublishables;
		boolean isDraft;

		userStoriesPublishables = this.repository.findAllUserStoriesOfAProjectById(object.getId()).stream().allMatch(x -> x.isDraft() == false) && this.repository.findAllUserStoriesOfAProjectById(object.getId()).size() > 0
			&& object.isHasFatalErrors() == false;
		isDraft = object.isDraft() == true;

		Dataset dataset;

		dataset = super.unbind(object, "title", "code");
		dataset.put("publishable", userStoriesPublishables);
		dataset.put("isDraft", isDraft);

		super.getResponse().addData(dataset);
	}

}
