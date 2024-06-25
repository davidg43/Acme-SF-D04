
package acme.features.client.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.ClientDashboard;
import acme.roles.Client;

@Service
public class ClientDashboardShowService extends AbstractService<Client, ClientDashboard> {

	@Autowired
	private ClientDashboardRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {

		ClientDashboard dashboard;
		Integer totalNumberProgressLogsRateLess25;
		Integer totalNumberProgressLogsRateBetween25And50;
		Integer totalNumberProgressLogsRateBetween50And75;
		Integer totalNumberProgressLogsRateMoreThan75;
		Double averageBudgetOfContracts;
		Double deviationBudgetOfContracts;
		Double minimumBudgetOfContracts;
		Double maximumBudgetOfContracts;

		int id = super.getRequest().getPrincipal().getActiveRoleId();
		totalNumberProgressLogsRateLess25 = this.repository.totalNumberProgressLogsRateLess25(id);
		totalNumberProgressLogsRateBetween25And50 = this.repository.totalNumberProgressLogsRateBetween25And50(id);
		totalNumberProgressLogsRateBetween50And75 = this.repository.totalNumberProgressLogsRateBetween50And75(id);
		totalNumberProgressLogsRateMoreThan75 = this.repository.totalNumberProgressLogsRateMoreThan75(id);
		averageBudgetOfContracts = this.repository.averageBudgetOfContracts(id);
		deviationBudgetOfContracts = this.repository.deviationBudgetOfContracts(id);
		minimumBudgetOfContracts = this.repository.minimumBudgetOfContracts(id);
		maximumBudgetOfContracts = this.repository.maximumBudgetOfContracts(id);

		dashboard = new ClientDashboard();
		dashboard.setTotalNumberProgressLogsRateLess25(totalNumberProgressLogsRateLess25);
		dashboard.setTotalNumberProgressLogsRateBetween25And50(totalNumberProgressLogsRateBetween25And50);
		dashboard.setTotalNumberProgressLogsRateBetween50And75(totalNumberProgressLogsRateBetween50And75);
		dashboard.setTotalNumberProgressLogsRateMoreThan75(totalNumberProgressLogsRateMoreThan75);
		dashboard.setAverageBudgetOfContracts(averageBudgetOfContracts);
		dashboard.setDeviationBudgetOfContracts(deviationBudgetOfContracts);
		dashboard.setMinimumBudgetOfContracts(minimumBudgetOfContracts);
		dashboard.setMaximumBudgetOfContracts(maximumBudgetOfContracts);
		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final ClientDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, "totalNumberProgressLogsRateLess25", "totalNumberProgressLogsRateBetween25And50", "totalNumberProgressLogsRateBetween50And75", "totalNumberProgressLogsRateMoreThan75", "averageBudgetOfContracts",
			"deviationBudgetOfContracts", "minimumBudgetOfContracts", "maximumBudgetOfContracts");

		super.getResponse().addData(dataset);
	}

}
