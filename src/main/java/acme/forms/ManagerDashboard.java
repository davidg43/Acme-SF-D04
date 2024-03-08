
package acme.forms;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	private int					totalMustUserStories;

	private int					totalShouldUserStories;

	private int					totalCouldUserStories;

	private int					totalWontUserStories;

	private double				averageEstimatedCostOfUserStories;

	private double				deviationOfEstimatedCostOfUserStories;

	private int					minimumEstimatedCostOfUserStories;

	private int					maximumEstimatedCostOfUserStories;

	private double				averageCostOfProjects;

	private double				deviationOfCostOfProjects;

	private int					minimumCostOfProjects;

	private int					maximumCostOfProjects;

}
