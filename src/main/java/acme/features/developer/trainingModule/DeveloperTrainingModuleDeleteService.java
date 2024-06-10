
package acme.features.developer.trainingModule;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.project.Project;
import acme.entities.trainingModule.DifficultyLevel;
import acme.entities.trainingModule.TrainingModule;
import acme.entities.trainingModule.TrainingSession;
import acme.roles.Developer;

@Service
public class DeveloperTrainingModuleDeleteService extends AbstractService<Developer, TrainingModule> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private DeveloperTrainingModuleRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int masterId;
		TrainingModule trainingModule;
		Developer developer;

		masterId = super.getRequest().getData("id", int.class);
		trainingModule = this.repository.findOneTrainingModuleById(masterId);
		developer = trainingModule == null ? null : trainingModule.getDeveloper();
		status = trainingModule != null && trainingModule.getDraftMode() && //
			super.getRequest().getPrincipal().hasRole(developer) && //
			trainingModule.getDeveloper().getId() == super.getRequest().getPrincipal().getActiveRoleId();

		super.getResponse().setAuthorised(status);

	}

	@Override
	public void load() {
		TrainingModule object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneTrainingModuleById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final TrainingModule object) {
		assert object != null;

		int projectId;
		Project project;

		projectId = super.getRequest().getData("project", int.class);
		project = this.repository.findOneProjectById(projectId);

		super.bind(object, "code", "creationMoment", "details", "difficultyLevel", "updateMoment", "link");
		object.setProject(project);
	}

	@Override
	public void validate(final TrainingModule object) {
		assert object != null;

		int masterId = super.getRequest().getData("id", int.class);
		List<TrainingSession> ls = this.repository.findManyTrainingSessionsByTrainingModuleId(masterId).stream().toList();
		final boolean thereAreDraftedTrainingSessions = ls.stream().allMatch(Session -> Session.getIsDraftMode());
		super.state(thereAreDraftedTrainingSessions, "*", "developer.trainingModule.form.error.trainingSession-Nodraft");

	}

	@Override
	public void perform(final TrainingModule object) {
		assert object != null;

		Collection<TrainingSession> sessions;
		sessions = this.repository.findManyTrainingSessionsByTrainingModuleId(object.getId());
		this.repository.deleteAll(sessions);
		this.repository.delete(object);
	}

	@Override
	public void unbind(final TrainingModule object) {
		assert object != null;

		/*
		 * int developerId;
		 * Collection<Project> projects;
		 * SelectChoices choices;
		 * Dataset dataset;
		 * 
		 * developerId = super.getRequest().getPrincipal().getActiveRoleId();
		 * projects = this.repository.findManyProjectsByDeveloperId(developerId);
		 * choices = SelectChoices.from(projects, "code", object.getProject());
		 * 
		 * dataset = super.unbind(object, "code", "creationMoment", "details", "difficultyLevel", "updateMoment", "link", "draftMode");
		 * dataset.put("project", choices.getSelected().getKey());
		 * dataset.put("projects", choices);
		 * 
		 * super.getResponse().addData(dataset);
		 */

		Collection<Project> projects = this.repository.findAllProjects();
		SelectChoices projectsChoices = SelectChoices.from(projects, "code", object.getProject());

		SelectChoices choices = SelectChoices.from(DifficultyLevel.class, object.getDifficultyLevel());

		Dataset dataset = super.unbind(object, "code", "creationMoment", "details", "difficultyLevel", "updateMoment", "link", "totalTime", "project", "draftMode");

		dataset.put("difficultyLevelOptions", choices);
		dataset.put("project", projectsChoices.getSelected().getKey());
		dataset.put("projects", projectsChoices);

		super.getResponse().addData(dataset);
	}

}
