
package acme.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AuditorDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	@NotNull
	private int					totalStaticCodeAudits;

	@NotNull
	private int					totalDynamicCodeAudits;

	@PositiveOrZero
	private double				averageNumberOfAuditRecords;

	@PositiveOrZero
	private double				deviationNumberOfAuditRecords;

	@PositiveOrZero
	private int					minimumNumberOfAuditRecords;

	@PositiveOrZero
	private int					maximumNumberOfAuditRecords;

	@PositiveOrZero
	private double				averagePeriodInAuditRecords;

	@PositiveOrZero
	private double				deviationPeriodInAuditRecords;

	@PositiveOrZero
	private int					minimumPeriodInAuditRecords;

	@PositiveOrZero
	private int					mwximumPeriodInAuditRecords;

}
