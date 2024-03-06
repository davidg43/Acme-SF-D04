
package acme.forms;

import javax.validation.constraints.PositiveOrZero;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	@PositiveOrZero
	private int					totalNumberOfPrincipalsWithEachRole;

	@PositiveOrZero
	private double				ratioNoticesWithEmailAndLink;

	@PositiveOrZero
	private double				ratioCriticalObjectives;

	@PositiveOrZero
	private double				ratioNonCriticalObjectives;

	@PositiveOrZero
	private double				averageRiskValue;

	@PositiveOrZero
	private double				minimumRiskValue;

	@PositiveOrZero
	private double				maximumRiskValue;

	@PositiveOrZero
	private double				standardDeviationRiskValue;

	@PositiveOrZero
	private double				averageClaimsPosted;

	@PositiveOrZero
	private double				minimumClaimsPosted;

	@PositiveOrZero
	private double				maximumClaimsPosted;

	@PositiveOrZero
	private double				standardDeviationClaimsPosted;
}
