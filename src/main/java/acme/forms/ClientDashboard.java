
package acme.forms;

import javax.validation.constraints.PositiveOrZero;

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

	@PositiveOrZero
	private double				averageBudgetOfContracts;

	@PositiveOrZero
	private double				deviationBudgetOfContracts;
	@PositiveOrZero
	private int					minimumBudgetOfContracts;

	@PositiveOrZero
	private int					maximumBudgetOfContracts;

}
