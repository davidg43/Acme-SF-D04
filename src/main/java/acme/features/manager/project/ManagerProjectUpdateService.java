
package acme.features.manager.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.project.Project;
import acme.roles.Manager;

@Service
public class ManagerProjectUpdateService extends AbstractService<Manager, Project> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectRepository repository;

	// AbstractService<Manager, Project> -------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int masterId;
		Project project;
		Manager manager;

		masterId = super.getRequest().getData("id", int.class);
		project = this.repository.findProjectById(masterId);
		manager = this.repository.findManagerByProjectId(masterId);
		status = project != null && project.isDraft() && super.getRequest().getPrincipal().hasRole(Manager.class) && project.getManager().equals(manager);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Project project;
		int id;

		id = super.getRequest().getData("id", int.class);
		project = this.repository.findProjectById(id);

		super.getBuffer().addData(project);
	}

	@Override
	public void bind(final Project project) {
		assert project != null;

		super.bind(project, "code", "title", "abstractText", "hasFatalErrors", "cost", "link", "isDraft");
	}

	@Override
	public void validate(final Project project) {
		assert project != null;

		if (!super.getBuffer().getErrors().hasErrors("hasFatalErrors"))
			super.state(!project.isHasFatalErrors(), "project", "manager.project.form.error.fatal-errors");

		if (!super.getBuffer().getErrors().hasErrors("cost"))
			super.state(project.getCost().getAmount() >= 0, "cost", "manager.project.form.error.negative-cost");

		if (!super.getBuffer().getErrors().hasErrors("isDraft"))
			super.state(project.isDraft(), "isDraft", "manager.project.form.error.published");
	}

	@Override
	public void perform(final Project project) {
		assert project != null;

		this.repository.save(project);
	}

	@Override
	public void unbind(final Project project) {
		assert project != null;

		Dataset dataset;

		dataset = super.unbind(project, "code", "title", "abstractText", "hasFatalErrors", "cost", "link", "isDraft");
		dataset.put("projectId", project.getId());
		super.getResponse().addData(dataset);
	}

}
