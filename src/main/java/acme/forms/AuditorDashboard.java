
package acme.forms;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AuditorDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	private Integer				totalStaticCodeAudits;

	private Integer				totalDynamicCodeAudits;

	private Double				averageNumberOfAuditRecords;

	private Double				deviationNumberOfAuditRecords;

	private Integer				minimumNumberOfAuditRecords;

	private Integer				maximumNumberOfAuditRecords;

	private Double				averagePeriodInAuditRecords;

	private Double				deviationPeriodInAuditRecords;

	private Double				minimumPeriodInAuditRecords;

	private Double				maximumPeriodInAuditRecords;

}
