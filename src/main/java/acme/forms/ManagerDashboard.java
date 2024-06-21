
package acme.forms;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	private Integer					totalMustUserStories;

	private Integer					totalShouldUserStories;

	private Integer					totalCouldUserStories;

	private Integer					totalWontUserStories;

	private Double				averageEstimatedCostOfUserStories;

	private Double				deviationOfEstimatedCostOfUserStories;

	private Integer					minimumEstimatedCostOfUserStories;

	private Integer					maximumEstimatedCostOfUserStories;

	private Double				averageCostOfProjects;

	private Double				deviationOfCostOfProjects;

	private Double				minimumCostOfProjects;

	private Double				maximumCostOfProjects;

}
