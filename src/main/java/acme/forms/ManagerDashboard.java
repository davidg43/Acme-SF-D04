
package acme.forms;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	private Integer				totalMustUserStories;
	private Integer				totalShouldUserStories;
	private Integer				totalCouldUserStories;
	private Integer				totalWontUserStories;

	private Double				averageEstimatedCostOfUserStories;
	private Double				deviationOfEstimatedCostOfUserStories;
	private Integer				minimumEstimatedCostOfUserStories;
	private Integer				maximumEstimatedCostOfUserStories;

	// Campos para EUR
	private Double				averageCostOfProjectsEUR;
	private Double				deviationOfCostOfProjectsEUR;
	private Double				minimumCostOfProjectsEUR;
	private Double				maximumCostOfProjectsEUR;

	// Campos para GBP
	private Double				averageCostOfProjectsGBP;
	private Double				deviationOfCostOfProjectsGBP;
	private Double				minimumCostOfProjectsGBP;
	private Double				maximumCostOfProjectsGBP;

	// Campos para USD
	private Double				averageCostOfProjectsUSD;
	private Double				deviationOfCostOfProjectsUSD;
	private Double				minimumCostOfProjectsUSD;
	private Double				maximumCostOfProjectsUSD;

}
