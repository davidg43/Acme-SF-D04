
package acme.features.client.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.ClientDashboard;
import acme.roles.Client;

@Service
public class ClientDashboardShowService extends AbstractService<Client, ClientDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ClientDashboardRepository repository;

	// AbstractService<Client, ClientDashboard> -------------------------------------


	@Override
	public void authorise() {
		boolean auth = super.getRequest().getPrincipal().hasRole(Client.class);
		super.getResponse().setAuthorised(auth);
	}

	@Override
	public void load() {
		ClientDashboard dashboard = new ClientDashboard();
		int id = super.getRequest().getPrincipal().getActiveRoleId();

		dashboard.setTotalNumberProgressLogsRateLess25(this.repository.totalNumberProgressLogsRateLess25(id));
		dashboard.setTotalNumberProgressLogsRateBetween25And50(this.repository.totalNumberProgressLogsRateBetween25And50(id));
		dashboard.setTotalNumberProgressLogsRateBetween50And75(this.repository.totalNumberProgressLogsRateBetween50And75(id));
		dashboard.setTotalNumberProgressLogsRateMoreThan75(this.repository.totalNumberProgressLogsRateMoreThan75(id));

		dashboard.setAverageBudgetOfContractsEUR(this.repository.averageBudgetOfContractsEUR(id));
		dashboard.setDeviationBudgetOfContractsEUR(this.repository.deviationBudgetOfContractsEUR(id));
		dashboard.setMinimumBudgetOfContractsEUR(this.repository.minimumBudgetOfContractsEUR(id));
		dashboard.setMaximumBudgetOfContractsEUR(this.repository.maximumBudgetOfContractsEUR(id));

		dashboard.setAverageBudgetOfContractsGBP(this.repository.averageBudgetOfContractsGBP(id));
		dashboard.setDeviationBudgetOfContractsGBP(this.repository.deviationBudgetOfContractsGBP(id));
		dashboard.setMinimumBudgetOfContractsGBP(this.repository.minimumBudgetOfContractsGBP(id));
		dashboard.setMaximumBudgetOfContractsGBP(this.repository.maximumBudgetOfContractsGBP(id));

		dashboard.setAverageBudgetOfContractsUSD(this.repository.averageBudgetOfContractsUSD(id));
		dashboard.setDeviationBudgetOfContractsUSD(this.repository.deviationBudgetOfContractsUSD(id));
		dashboard.setMinimumBudgetOfContractsUSD(this.repository.minimumBudgetOfContractsUSD(id));
		dashboard.setMaximumBudgetOfContractsUSD(this.repository.maximumBudgetOfContractsUSD(id));

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final ClientDashboard dashboard) {
		assert dashboard != null;
		Dataset dataset = super.unbind(dashboard, "totalNumberProgressLogsRateLess25", "totalNumberProgressLogsRateBetween25And50", "totalNumberProgressLogsRateBetween50And75", "totalNumberProgressLogsRateMoreThan75", "averageBudgetOfContractsEUR",
			"deviationBudgetOfContractsEUR", "minimumBudgetOfContractsEUR", "maximumBudgetOfContractsEUR", "averageBudgetOfContractsGBP", "deviationBudgetOfContractsGBP", "minimumBudgetOfContractsGBP", "maximumBudgetOfContractsGBP",
			"averageBudgetOfContractsUSD", "deviationBudgetOfContractsUSD", "minimumBudgetOfContractsUSD", "maximumBudgetOfContractsUSD");
		super.getResponse().addData(dataset);
	}
}
