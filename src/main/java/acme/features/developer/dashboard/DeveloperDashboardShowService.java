
package acme.features.developer.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Principal;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.datatypes.Statistics;
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
		final Principal principal = super.getRequest().getPrincipal();
		int userAccountId = principal.getAccountId();

		Integer totalTrainingModulesWithUpdateMoment = this.repository.totalTrainingModulesWithUpdateMoment(userAccountId);
		Integer totalTrainingSessionsWithLink = this.repository.totalTrainingSessionsWithLink(userAccountId);

		Double averageTrainingModuleTime = this.repository.findAverageTrainingModuleTime(userAccountId);
		Double deviationTrainingModuleTime = this.repository.findDeviationTrainingModuleTime(userAccountId);
		Double minimumTrainingModuleTime = this.repository.findMinimumTrainingModuleTime(userAccountId);
		Double maximumTrainingModuleTime = this.repository.findMaximumTrainingModuleTime(userAccountId);

		if (averageTrainingModuleTime != null && deviationTrainingModuleTime != null && minimumTrainingModuleTime != null && maximumTrainingModuleTime != null) {

			final Statistics trainingModuleTimeStatistics = new Statistics();
			trainingModuleTimeStatistics.setAverage(averageTrainingModuleTime);
			trainingModuleTimeStatistics.setDeviation(deviationTrainingModuleTime);
			trainingModuleTimeStatistics.setMaximum(maximumTrainingModuleTime);
			trainingModuleTimeStatistics.setMinimum(minimumTrainingModuleTime);

			final DeveloperDashboard dashboard = new DeveloperDashboard();

			dashboard.setTotalTrainingModulesWithUpdateMoment(totalTrainingModulesWithUpdateMoment);
			dashboard.setTotalTrainingSessionsWithLink(totalTrainingSessionsWithLink);
			dashboard.setTrainingModuleTimeStatistics(trainingModuleTimeStatistics);

			super.getBuffer().addData(dashboard);
		} else {
			final Statistics trainingModuleTimeStatistics = new Statistics();
			trainingModuleTimeStatistics.setAverage(0.0);
			trainingModuleTimeStatistics.setDeviation(0.0);
			trainingModuleTimeStatistics.setMaximum(0.0);
			trainingModuleTimeStatistics.setMinimum(0.0);

			final DeveloperDashboard dashboard = new DeveloperDashboard();

			dashboard.setTotalTrainingModulesWithUpdateMoment(totalTrainingModulesWithUpdateMoment);
			dashboard.setTotalTrainingSessionsWithLink(totalTrainingSessionsWithLink);
			dashboard.setTrainingModuleTimeStatistics(trainingModuleTimeStatistics);
			super.getBuffer().addData(dashboard);

		}
	}

	@Override
	public void unbind(final DeveloperDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, //
			"totalTrainingModulesWithUpdateMoment", "totalTrainingSessionsWithLink", // 
			"trainingModuleTimeStatistics");

		super.getResponse().addData(dataset);
	}

}
