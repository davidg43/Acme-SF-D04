
package acme.features.manager.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.ManagerDashboard;
import acme.roles.Manager;

@Service
public class ManagerDashboardShowService extends AbstractService<Manager, ManagerDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerDashboardRepository repository;

	// AbstractService<Manager, ManagerDashboard> -------------------------------------


	@Override
	public void authorise() {
		boolean auth = super.getRequest().getPrincipal().hasRole(Manager.class);
		super.getResponse().setAuthorised(auth);

	}

	@Override
	public void load() {
		ManagerDashboard dashboard = new ManagerDashboard();
		int id = super.getRequest().getPrincipal().getActiveRoleId();

		dashboard.setAverageEstimatedCostOfUserStories(this.repository.avgEstimatedCost(id));
		dashboard.setDeviationOfEstimatedCostOfUserStories(this.repository.deviationEstimatedCost(id));

		dashboard.setMaximumEstimatedCostOfUserStories(this.repository.maxEstimatedCost(id));
		dashboard.setMinimumEstimatedCostOfUserStories(this.repository.minEstimatedCost(id));

		dashboard.setTotalCouldUserStories(this.repository.countCould(id));
		dashboard.setTotalWontUserStories(this.repository.countWont(id));
		dashboard.setTotalShouldUserStories(this.repository.countShould(id));
		dashboard.setTotalMustUserStories(this.repository.countMust(id));

		dashboard.setAverageCostOfProjectsUSD(this.repository.avgProjectCostUSD(id));
		dashboard.setDeviationOfCostOfProjectsUSD(this.repository.deviationProjectCostUSD(id));
		dashboard.setMaximumCostOfProjectsUSD(this.repository.maxProjectCostUSD(id));
		dashboard.setMinimumCostOfProjectsUSD(this.repository.minProjectCostUSD(id));

		dashboard.setAverageCostOfProjectsGBP(this.repository.avgProjectCostGBP(id));
		dashboard.setDeviationOfCostOfProjectsGBP(this.repository.deviationProjectCostGBP(id));
		dashboard.setMaximumCostOfProjectsGBP(this.repository.maxProjectCostGBP(id));
		dashboard.setMinimumCostOfProjectsGBP(this.repository.minProjectCostGBP(id));

		dashboard.setAverageCostOfProjectsEUR(this.repository.avgProjectCostEUR(id));
		dashboard.setDeviationOfCostOfProjectsEUR(this.repository.deviationProjectCostEUR(id));
		dashboard.setMaximumCostOfProjectsEUR(this.repository.maxProjectCostEUR(id));
		dashboard.setMinimumCostOfProjectsEUR(this.repository.minProjectCostEUR(id));

		super.getBuffer().addData(dashboard);

	}

	@Override
	public void unbind(final ManagerDashboard dashboard) {
		assert dashboard != null;
		Dataset dataset = super.unbind(dashboard, "totalMustUserStories", "totalShouldUserStories", "totalCouldUserStories", "totalWontUserStories", "averageEstimatedCostOfUserStories", "deviationOfEstimatedCostOfUserStories",
			"minimumEstimatedCostOfUserStories", "maximumEstimatedCostOfUserStories", "averageCostOfProjectsEUR", "deviationOfCostOfProjectsEUR", "minimumCostOfProjectsEUR", "maximumCostOfProjectsEUR", "averageCostOfProjectsGBP",
			"deviationOfCostOfProjectsGBP", "minimumCostOfProjectsGBP", "maximumCostOfProjectsGBP", "averageCostOfProjectsUSD", "deviationOfCostOfProjectsUSD", "minimumCostOfProjectsUSD", "maximumCostOfProjectsUSD");
		super.getResponse().addData(dataset);
	}

}
