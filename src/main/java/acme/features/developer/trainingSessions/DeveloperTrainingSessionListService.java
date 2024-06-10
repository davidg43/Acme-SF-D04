
package acme.features.developer.trainingSessions;

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
		/*
		 * boolean status;
		 * int masterId;
		 * TrainingModule trainingModule;
		 * 
		 * masterId = super.getRequest().getData("masterId", int.class);
		 * trainingModule = this.repository.findOneTrainingModuleById(masterId);
		 * status = trainingModule != null && (!trainingModule.getDraftMode() || super.getRequest().getPrincipal().hasRole(trainingModule.getDeveloper()));
		 * 
		 * super.getResponse().setAuthorised(status);
		 */

		boolean status;
		int id;
		TrainingModule trainingModule;

		id = super.getRequest().getData("masterId", int.class);
		trainingModule = this.repository.findOneTrainingModuleById(id);
		status = trainingModule != null && super.getRequest().getPrincipal().hasRole(trainingModule.getDeveloper());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Collection<TrainingSession> objects;
		int masterId;

		masterId = super.getRequest().getData("masterId", int.class);
		objects = this.repository.findManyTrainingSessionsByTrainingModuleId(masterId);

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

		if (object.getIsDraftMode())
			dataset.put("draftMode", "No");
		else {
			final Locale local = super.getRequest().getLocale();
			dataset.put("draftMode", local.equals(Locale.ENGLISH) ? "Yes" : "Sí");
		}

		int masterId;

		masterId = super.getRequest().getData("masterId", int.class);

		super.getResponse().addGlobal("masterId", masterId);

		super.getResponse().addData(dataset);
	}

	@Override
	public void unbind(final Collection<TrainingSession> objects) {
		assert objects != null;

		int masterId;
		TrainingModule trainingModule;
		final boolean showCreate;

		masterId = super.getRequest().getData("masterId", int.class);
		trainingModule = this.repository.findOneTrainingModuleById(masterId);
		showCreate = trainingModule.getDraftMode() && super.getRequest().getPrincipal().hasRole(trainingModule.getDeveloper());

		super.getResponse().addGlobal("masterId", masterId);
		super.getResponse().addGlobal("showCreate", showCreate);
	}

}
