
package acme.features.manager.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.project.Project;
import acme.roles.Manager;

@Service
public class ManagerProjectShowService extends AbstractService<Manager, Project> {

	@Autowired
	private ManagerProjectRepository repository;


	@Override
	public void authorise() {
		boolean status;
		Project project;
		int masterId;
		Manager manager;

		masterId = super.getRequest().getData("id", int.class);
		project = this.repository.findProjectById(masterId);
		manager = this.repository.findManagerByProjectId(masterId);

		status = super.getRequest().getPrincipal().hasRole(manager) || project != null;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		Project project = this.repository.findProjectById(id);

		super.getBuffer().addData(project);
	}

	@Override
	public void unbind(final Project project) {
		assert project != null;
		Dataset dataset;

		dataset = super.unbind(project, "code", "title", "abstractText", "hasFatalErrors", "cost", "isDraft");
		dataset.put("projectId", project.getId());

		super.getResponse().addData(dataset);
	}

}
