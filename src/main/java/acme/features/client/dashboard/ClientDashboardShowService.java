
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
		int totalNumberProgressLogsRateLess25;
		int totalNumberProgressLogsRateBetween25And50;
		int totalNumberProgressLogsRateBetween50And75;
		int totalNumberProgressLogsRateMoreThan75;
		double averageBudgetOfContracts;
		double deviationBudgetOfContracts;
		double minimumBudgetOfContracts;
		double maximumBudgetOfContracts;

		totalNumberProgressLogsRateLess25 = this.repository.totalNumberProgressLogsRateLess25();
		totalNumberProgressLogsRateBetween25And50 = this.repository.totalNumberProgressLogsRateBetween25And50();
		totalNumberProgressLogsRateBetween50And75 = this.repository.totalNumberProgressLogsRateBetween50And75();
		totalNumberProgressLogsRateMoreThan75 = this.repository.totalNumberProgressLogsRateMoreThan75();
		averageBudgetOfContracts = this.repository.averageBudgetOfContracts();
		deviationBudgetOfContracts = this.repository.deviationBudgetOfContracts();
		minimumBudgetOfContracts = this.repository.minimumBudgetOfContracts();
		maximumBudgetOfContracts = this.repository.maximumBudgetOfContracts();

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
