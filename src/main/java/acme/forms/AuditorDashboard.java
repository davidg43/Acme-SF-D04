
package acme.forms;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AuditorDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	private int					totalStaticCodeAudits;

	private int					totalDynamicCodeAudits;

	private double				averageNumberOfAuditRecords;

	private double				deviationNumberOfAuditRecords;

	private int					minimumNumberOfAuditRecords;

	private int					maximumNumberOfAuditRecords;

	private double				averagePeriodInAuditRecords;

	private double				deviationPeriodInAuditRecords;

	private double				minimumPeriodInAuditRecords;

	private double				maximumPeriodInAuditRecords;

}
