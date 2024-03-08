
package acme.forms;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	private int					totalNumberOfPrincipalsWithEachRole;

	private double				ratioNoticesWithEmailAndLink;

	private double				ratioCriticalObjectives;

	private double				ratioNonCriticalObjectives;

	private double				averageRiskValue;

	private double				minimumRiskValue;

	private double				maximumRiskValue;

	private double				standardDeviationRiskValue;

	private double				averageClaimsPosted;

	private double				minimumClaimsPosted;

	private double				maximumClaimsPosted;

	private double				standardDeviationClaimsPosted;
}
