
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

	private Double				averageBudgetOfContractsEUR;

	private Double				deviationBudgetOfContractsEUR;

	private Double				minimumBudgetOfContractsEUR;

	private Double				maximumBudgetOfContractsEUR;

	private Double				averageBudgetOfContractsGBP;

	private Double				deviationBudgetOfContractsGBP;

	private Double				minimumBudgetOfContractsGBP;

	private Double				maximumBudgetOfContractsGBP;

	private Double				averageBudgetOfContractsUSD;

	private Double				deviationBudgetOfContractsUSD;

	private Double				minimumBudgetOfContractsUSD;

	private Double				maximumBudgetOfContractsUSD;

}
