
package acme.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeveloperDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	@NotNull
	private int					totalTrainingModulesWithUpdateMoment;

	@NotNull
	private int					totalTrainingSessionsWithLink;

	@PositiveOrZero
	private double				averageTrainingModuleTime;

	@PositiveOrZero
	private double				deviationTrainingModuleTime;

	@PositiveOrZero
	private int					minimumTrainingModuleTime;

	@PositiveOrZero
	private int					maximumTrainingModuleTime;

}
