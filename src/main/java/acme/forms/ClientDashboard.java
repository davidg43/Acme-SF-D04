
package acme.forms;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	private int					totalNumberProgressLogsRateLess25;

	private int					totalNumberProgressLogsRateBetween25And50;

	private int					totalNumberProgressLogsRateBetween50And75;

	private int					totalNumberProgressLogsRateMoreThan75;

	private double				averageBudgetOfContracts;

	private double				deviationBudgetOfContracts;

	private double				minimumBudgetOfContracts;

	private double				maximumBudgetOfContracts;

}
