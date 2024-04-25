
package acme.features.authenticated.developer.trainingSessions;

import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.trainingModule.TrainingModule;
import acme.entities.trainingModule.TrainingSession;
import acme.roles.Developer;

@Service
public class DeveloperTrainingSessionListService extends AbstractService<Developer, TrainingSession> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private DeveloperTrainingSessionsRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int masterId;
		TrainingModule trainingModule;

		masterId = super.getRequest().getData("masterId", int.class);
		trainingModule = this.repository.findOneTrainingModuleById(masterId);
		status = trainingModule != null && //
			(!trainingModule.getDraftMode() || super.getRequest().getPrincipal().hasRole(trainingModule.getDeveloper()));

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Collection<TrainingSession> objects;
		int trainingModule;

		trainingModule = super.getRequest().getData("masterId", int.class);
		objects = this.repository.findManyTrainingSessionsByTrainingModuleId(trainingModule);

		super.getBuffer().addData(objects);
	}

	@Override
	public void unbind(final TrainingSession object) {

		assert object != null;

		Collection<TrainingModule> trainingModules = this.repository.findAllTrainingModules();
		SelectChoices trainingModulesChoices = SelectChoices.from(trainingModules, "code", object.getTrainingModule());

		final Dataset dataset = super.unbind(object, "code", "trainingModule");
		dataset.put("trainingModule", trainingModulesChoices.getSelected().getLabel());
		dataset.put("trainingModules", trainingModulesChoices);

		// TODO internacionalizar de isDraft a publish en el front
		if (object.getDraftMode()) {
			final Locale local = super.getRequest().getLocale();
			dataset.put("draftMode", local.equals(Locale.ENGLISH) ? "Yes" : "Sí");
		} else
			dataset.put("draftMode", "No");

		super.getResponse().addData(dataset);
	}

}
