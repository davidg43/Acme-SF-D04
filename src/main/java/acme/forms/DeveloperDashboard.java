
package acme.forms;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeveloperDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	private int					totalTrainingModulesWithUpdateMoment;

	private int					totalTrainingSessionsWithLink;

	private double				averageTrainingModuleTime;

	private double				deviationTrainingModuleTime;

	private int					minimumTrainingModuleTime;

	private int					maximumTrainingModuleTime;

}
