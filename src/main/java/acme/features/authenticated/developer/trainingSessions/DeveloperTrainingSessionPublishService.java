
package acme.features.authenticated.developer.trainingSessions;

import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Principal;
import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.trainingModule.TrainingSession;
import acme.roles.Developer;

@Service
public class DeveloperTrainingSessionPublishService extends AbstractService<Developer, TrainingSession> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private DeveloperTrainingSessionsRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		final Principal principal = super.getRequest().getPrincipal();
		final int userAccountId = principal.getAccountId();

		int id = super.getRequest().getData("id", int.class);
		TrainingSession trainingSession = this.repository.findOneTrainingSessionById(id);

		final boolean authorise = trainingSession != null && trainingSession.getTrainingModule().getDeveloper().getUserAccount().getId() == userAccountId;

		super.getResponse().setAuthorised(authorise);
	}

	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		TrainingSession trainingSession = this.repository.findOneTrainingSessionById(id);

		super.getBuffer().addData(trainingSession);
	}

	@Override
	public void bind(final TrainingSession object) {
		assert object != null;

		super.bind(object, "code", "iniDate", "finalDate", "location", "instructor", "contactEmail", "link");
	}

	@Override
	public void validate(final TrainingSession object) {
		assert object != null;

		final String INI_DATE = "iniDate";
		final String FINAL_DATE = "finalDate";
		final String CREATION_MOMENT = "creationMoment";

		if (!super.getBuffer().getErrors().hasErrors(INI_DATE) && !super.getBuffer().getErrors().hasErrors(FINAL_DATE)) {
			final boolean startBeforeEnd = MomentHelper.isAfter(object.getFinalDate(), object.getIniDate());
			super.state(startBeforeEnd, FINAL_DATE, "developer.training-session.form.error.end-before-start");

			if (startBeforeEnd) {
				final boolean startOneWeekBeforeEndMinimum = MomentHelper.isLongEnough(object.getIniDate(), object.getFinalDate(), 7, ChronoUnit.DAYS);

				super.state(startOneWeekBeforeEndMinimum, FINAL_DATE, "developer.training-session.form.error.small-display-period");
			}
		}

		if (!super.getBuffer().getErrors().hasErrors(CREATION_MOMENT) && !super.getBuffer().getErrors().hasErrors(INI_DATE)) {
			final boolean startBeforeCreation = MomentHelper.isAfter(object.getIniDate(), object.getTrainingModule().getCreationMoment());
			super.state(startBeforeCreation, INI_DATE, "developer.training-session.form.error.start-before-creation");
		}

		if (!super.getBuffer().getErrors().hasErrors(CREATION_MOMENT) && !super.getBuffer().getErrors().hasErrors(FINAL_DATE)) {
			final boolean endBeforeCreation = MomentHelper.isAfter(object.getFinalDate(), object.getTrainingModule().getCreationMoment());
			super.state(endBeforeCreation, FINAL_DATE, "developer.training-session.form.error.end-before-creation");
		}

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			final int trainingSessionId = super.getRequest().getData("id", int.class);
			final boolean duplicatedCode = this.repository.findAllTrainingSessions().stream().filter(e -> e.getId() != trainingSessionId).anyMatch(e -> e.getCode().equals(object.getCode()));

			super.state(!duplicatedCode, "code", "developer.training-session.form.error.duplicated-code");
		}
	}

	@Override
	public void perform(final TrainingSession object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final TrainingSession object) {
		assert object != null;

		Dataset dataset = super.unbind(object, "code", "iniDate", "finalDate", "location", "instructor", "contactEmail", "link");

		super.getResponse().addData(dataset);
	}

}
