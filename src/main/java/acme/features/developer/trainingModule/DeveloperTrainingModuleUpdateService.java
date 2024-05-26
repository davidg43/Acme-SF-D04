
package acme.features.developer.trainingModule;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Principal;
import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.project.Project;
import acme.entities.trainingModule.DifficultyLevel;
import acme.entities.trainingModule.TrainingModule;
import acme.roles.Developer;

@Service
public class DeveloperTrainingModuleUpdateService extends AbstractService<Developer, TrainingModule> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private DeveloperTrainingModuleRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int masterId;

		final Principal principal = super.getRequest().getPrincipal();
		final int userAccountId = principal.getAccountId();

		masterId = super.getRequest().getData("id", int.class);
		TrainingModule trainingModule = this.repository.findOneTrainingModuleById(masterId);
		Developer developer = trainingModule == null ? null : trainingModule.getDeveloper();
		status = trainingModule != null && trainingModule.getDraftMode() && principal.hasRole(developer) && trainingModule.getDeveloper().getUserAccount().getId() == userAccountId;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);

		TrainingModule object = this.repository.findOneTrainingModuleById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final TrainingModule object) {
		assert object != null;

		int projectId = super.getRequest().getData("project", int.class);
		Project project = this.repository.findOneProjectById(projectId);

		Date currentMoment = MomentHelper.getCurrentMoment();
		Date updateMoment = new Date(currentMoment.getTime() - 1);

		super.bind(object, "code", "details", "difficultyLevel", "link", "totalTime", "project");

		TrainingModule original = this.repository.findOneTrainingModuleById(object.getId());
		object.setCreationMoment(original.getCreationMoment());
		object.setUpdateMoment(updateMoment);
		object.setProject(project);
	}

	@Override
	public void validate(final TrainingModule object) {
		assert object != null;

		final String UPDATE_MOMENT = "updateMoment";

		if (!super.getBuffer().getErrors().hasErrors(UPDATE_MOMENT)) {
			final boolean startBeforeEnd = MomentHelper.isAfter(object.getUpdateMoment(), object.getCreationMoment());
			super.state(startBeforeEnd, UPDATE_MOMENT, "developer.trainingModule.form.error.end-before-start");
		}

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			final int trainingModuleId = super.getRequest().getData("id", int.class);
			final boolean duplicatedCode = this.repository.findAllTrainingModules().stream().filter(e -> e.getId() != trainingModuleId).anyMatch(e -> e.getCode().equals(object.getCode()));

			super.state(!duplicatedCode, "code", "developer.trainingModule.form.error.duplicated-code");
		}

		if (!super.getBuffer().getErrors().hasErrors("totalTime")) {
			final boolean negativeTotalTime = object.getTotalTime() < 0;

			super.state(!negativeTotalTime, "totalTime", "developer.trainingModule.form.error.negative-total-time");
		}

		if (!super.getBuffer().getErrors().hasErrors("project")) {
			Project project = object.getProject();

			super.state(!project.isDraft(), "project", "developer.trainingModule.form.error.invalid-project");
		}
	}

	@Override
	public void perform(final TrainingModule object) {
		assert object != null;

		Date currentMoment = MomentHelper.getCurrentMoment();
		object.setUpdateMoment(currentMoment);

		this.repository.save(object);
	}

	@Override
	public void unbind(final TrainingModule object) {
		assert object != null;

		SelectChoices choices = SelectChoices.from(DifficultyLevel.class, object.getDifficultyLevel());

		Collection<Project> projects = this.repository.findAllProjectsPublished();
		SelectChoices projectsChoices = SelectChoices.from(projects, "code", object.getProject());

		Dataset dataset = super.unbind(object, "code", "details", "difficultyLevel", "link", "totalTime", "draftMode", "project");

		dataset.put("creationMoment", object.getCreationMoment());

		dataset.put("difficultyLevelOptions", choices);
		dataset.put("project", projectsChoices.getSelected().getKey());
		dataset.put("projects", projectsChoices);

		super.getResponse().addData(dataset);
	}

}
