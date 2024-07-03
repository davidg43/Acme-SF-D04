
package acme.features.manager.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.project.Project;
import acme.roles.Manager;

@Service
public class ManagerProjectDeleteService extends AbstractService<Manager, Project> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int masterId;
		Project project;

		masterId = super.getRequest().getData("id", int.class);
		project = this.repository.findProjectById(masterId);
		status = project != null && project.isDraft() && super.getRequest().getPrincipal().hasRole(Manager.class) && project.getManager().getId() == super.getRequest().getPrincipal().getActiveRoleId();

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
	}

	@Override
	public void perform(final Project project) {
		assert project != null;

		this.repository.deleteAll(this.repository.findAllAssignmentsOfAProjectById(project.getId()));
		this.repository.deleteAll(this.repository.findAllProgressLogsByProjectId(project.getId()));
		this.repository.deleteAll(this.repository.findAllContractOfAProjectById(project.getId()));

		this.repository.deleteAll(this.repository.findAllAuditRecordsOfAProjectById(project.getId()));
		this.repository.deleteAll(this.repository.findAllCodeAuditsOfAProjectById(project.getId()));

		this.repository.deleteAll(this.repository.findAllSponsorShipOfAProjectById(project.getId()));

		this.repository.deleteAll(this.repository.findAllTrainingSessionsOfAProjectById(project.getId()));
		this.repository.deleteAll(this.repository.findAllTrainingModuleOfAProjectById(project.getId()));
		this.repository.delete(project);
	}

	@Override
	public void unbind(final Project project) {
		assert project != null;
		boolean userStoriesPublishables;
		boolean isDraft;

		userStoriesPublishables = this.repository.findAllUserStoriesOfAProjectById(project.getId()).stream().allMatch(x -> x.isDraft() == false) && this.repository.findAllUserStoriesOfAProjectById(project.getId()).size() > 0
			&& project.isHasFatalErrors() == false;
		isDraft = project.isDraft() == true;

		Dataset dataset;

		dataset = super.unbind(project, "code", "title", "abstractText", "hasFatalErrors", "cost", "link", "isDraft");
		dataset.put("projectId", project.getId());
		dataset.put("publishable", userStoriesPublishables);
		dataset.put("isDraft", isDraft);

		super.getResponse().addData(dataset);
	}

}
