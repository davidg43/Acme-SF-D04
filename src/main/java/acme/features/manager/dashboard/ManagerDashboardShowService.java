
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

		dashboard.setAverageCostOfProjects(this.repository.avgProjectCost(id));
		dashboard.setAverageEstimatedCostOfUserStories(this.repository.avgProjectCost(id));
		dashboard.setDeviationOfCostOfProjects(this.repository.deviationProjectCost(id));
		dashboard.setDeviationOfEstimatedCostOfUserStories(this.repository.deviationEstimatedCost(id));

		dashboard.setMaximumCostOfProjects(this.repository.maxProjectCost(id));
		dashboard.setMaximumEstimatedCostOfUserStories(this.repository.maxEstimatedCost(id));
		dashboard.setMinimumCostOfProjects(this.repository.minProjectCost(id));
		dashboard.setMinimumEstimatedCostOfUserStories(this.repository.minEstimatedCost(id));

		dashboard.setTotalCouldUserStories(this.repository.countCould(id));
		dashboard.setTotalWontUserStories(this.repository.countWont(id));
		dashboard.setTotalShouldUserStories(this.repository.countShould(id));
		dashboard.setTotalMustUserStories(this.repository.countMust(id));

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
