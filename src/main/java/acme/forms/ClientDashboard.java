
package acme.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	@NotNull
	private int					totalNumberProgressLogsRateLess25;

	@NotNull
	private int					totalNumberProgressLogsRateBetween25And50;

	@NotNull
	private int					totalNumberProgressLogsRateBetween50And75;

	@NotNull
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
