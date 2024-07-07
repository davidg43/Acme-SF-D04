
package acme.forms;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	private Integer				totalNumberProgressLogsRateLess25;

	private Integer				totalNumberProgressLogsRateBetween25And50;

	private Integer				totalNumberProgressLogsRateBetween50And75;

	private Integer				totalNumberProgressLogsRateMoreThan75;

	private Double				averageBudgetOfContracts;

	private Double				deviationBudgetOfContracts;

	private Double				minimumBudgetOfContracts;

	private Double				maximumBudgetOfContracts;

}
