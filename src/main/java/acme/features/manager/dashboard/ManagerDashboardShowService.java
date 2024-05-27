
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

		dashboard.setAverageCostOfProjects(this.repository.avgProjectCost());
		dashboard.setAverageEstimatedCostOfUserStories(this.repository.avgProjectCost());
		dashboard.setDeviationOfCostOfProjects(this.repository.deviationProjectCost());
		dashboard.setDeviationOfEstimatedCostOfUserStories(this.repository.deviationEstimatedCost());

		dashboard.setMaximumCostOfProjects(this.repository.maxProjectCost());
		dashboard.setMaximumEstimatedCostOfUserStories(this.repository.maxEstimatedCost());
		dashboard.setMinimumCostOfProjects(this.repository.minProjectCost());
		dashboard.setMinimumEstimatedCostOfUserStories(this.repository.minEstimatedCost());

		dashboard.setTotalCouldUserStories(this.repository.countCould());
		dashboard.setTotalWontUserStories(this.repository.countWont());
		dashboard.setTotalShouldUserStories(this.repository.countShould());
		dashboard.setTotalMustUserStories(this.repository.countMust());

		super.getBuffer().addData(dashboard);

	}

	@Override
	public void unbind(final ManagerDashboard dashboard) {
		assert dashboard != null;
		Dataset dataset = super.unbind(dashboard, "totalMustUserStories", "totalShouldUserStories", "totalCouldUserStories", "totalWontUserStories", "averageEstimatedCostOfUserStories", "deviationOfEstimatedCostOfUserStories",
			"minimumEstimatedCostOfUserStories", "maximumEstimatedCostOfUserStories", "averageCostOfProjects", "deviationOfCostOfProjects", "minimumCostOfProjects", "maximumCostOfProjects");
		super.getResponse().addData(dataset);
	}

}
