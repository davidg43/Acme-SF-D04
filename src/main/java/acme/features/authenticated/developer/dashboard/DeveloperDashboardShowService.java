
package acme.features.authenticated.developer.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Principal;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.DeveloperDashboard;
import acme.roles.Developer;

@Service
public class DeveloperDashboardShowService extends AbstractService<Developer, DeveloperDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected DeveloperDashboardRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		Principal principal = super.getRequest().getPrincipal();
		int id = principal.getAccountId();
		Developer developer = this.repository.findDeveloperById(id);
		status = developer != null && principal.hasRole(Developer.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		DeveloperDashboard object;
		final Integer totalNumberOfTrainingModulesWithAnUpdateMoment;
		final Integer totalNumberOfTrainingSessionsWithALink;
		final Double averageTimeOfTrainingModules;
		final Double deviationTimeOfTrainingModules;
		final Double minimumTimeOfTrainingModules;
		final Double maximumTimeOfTrainingModules;
		int developerId;

		developerId = super.getRequest().getPrincipal().getActiveRoleId();
		totalNumberOfTrainingModulesWithAnUpdateMoment = this.repository.totalTrainingModulesWithUpdateMoment(developerId);
		totalNumberOfTrainingSessionsWithALink = this.repository.totalTrainingSessionsWithLink(developerId);
		averageTimeOfTrainingModules = this.repository.findAverageTrainingModuleTime(developerId);
		deviationTimeOfTrainingModules = this.repository.findDeviationTrainingModuleTime(developerId);
		minimumTimeOfTrainingModules = this.repository.findMinimumTrainingModuleTime(developerId);
		maximumTimeOfTrainingModules = this.repository.findMaximumTrainingModuleTime(developerId);

		object = new DeveloperDashboard();
		object.setTotalTrainingModulesWithUpdateMoment(totalNumberOfTrainingModulesWithAnUpdateMoment);
		object.setTotalTrainingSessionsWithLink(totalNumberOfTrainingSessionsWithALink);
		object.setAverageTrainingModuleTime(averageTimeOfTrainingModules);
		object.setDeviationTrainingModuleTime(deviationTimeOfTrainingModules);
		object.setMinimumTrainingModuleTime(minimumTimeOfTrainingModules);
		object.setMaximumTrainingModuleTime(maximumTimeOfTrainingModules);
		super.getBuffer().addData(object);
	}

	@Override
	public void unbind(final DeveloperDashboard object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "totalTrainingModulesWithUpdateMoment", "totalTrainingSessionsWithLink", "averageTrainingModuleTime", //
			"deviationTrainingModuleTime", "minimumTrainingModuleTime", "maximumTrainingModuleTime");

		super.getResponse().addData(dataset);
	}

}
