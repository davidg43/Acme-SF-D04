
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

	private Double				averageTrainingModuleTime;

	private Double				deviationTrainingModuleTime;

	private Double				minimumTrainingModuleTime;

	private Double				maximumTrainingModuleTime;

}
