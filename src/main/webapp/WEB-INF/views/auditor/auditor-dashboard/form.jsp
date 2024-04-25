<%--
- form.jsp
-
- Copyright (C) 2012-2024 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<h2>
	<acme:message code="auditor.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="auditor.dashboard.label.total-static-code-audits"/>
		</th>
		<td>
			<acme:print value="${totalStaticCodeAudits}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="auditor.dashboard.label.total-dynamic-code-audits"/>
		</th>
		<td>
			<acme:print value="${totalDynamicCodeAudits}"/>
		</td>
	</tr>
		<tr>
		<th scope="row">
			<acme:message code="auditor.dashboard.label.average-number-of-audit-records"/>
		</th>
		<td>
			<acme:print value="${averageNumberOfAuditRecords}"/>
		</td>
	</tr>
		</tr>
		<tr>
		<th scope="row">
			<acme:message code="auditor.dashboard.label.deviation-number-of-audit-records"/>
		</th>
		<td>
			<acme:print value="${deviationNumberOfAuditRecords}"/>
		</td>
	</tr>
		</tr>
		<tr>
		<th scope="row">
			<acme:message code="auditor.dashboard.label.maximum-number-of-audit-records"/>
		</th>
		<td>
			<acme:print value="${maximumNumberOfAuditRecords}"/>
		</td>
	</tr>
		</tr>
		<tr>
		<th scope="row">
			<acme:message code="auditor.dashboard.label.minimum-number-of-audit-records"/>
		</th>
		<td>
			<acme:print value="${minimumNumberOfAuditRecords}"/>
		</td>
	</tr>
		</tr>
		<tr>
		<th scope="row">
			<acme:message code="auditor.dashboard.label.average-period-in-audit-records"/>
		</th>
		<td>
			<acme:print value="${averagePeriodInAuditRecords}"/>
		</td>
	</tr>
		<tr>
		<th scope="row">
			<acme:message code="auditor.dashboard.label.deviation-period-in-audit-records"/>
		</th>
		<td>
			<acme:print value="${deviationPeriodInAuditRecords}"/>
		</td>
	</tr>
		<tr>
		<th scope="row">
			<acme:message code="auditor.dashboard.label.minimum-period-in-audit-records"/>
		</th>
		<td>
			<acme:print value="${minimumPeriodInAuditRecords}"/>
		</td>
	</tr>
		<tr>
		<th scope="row">
			<acme:message code="auditor.dashboard.label.maximum-period-in-audit-records"/>
		</th>
		<td>
			<acme:print value="${maximumPeriodInAuditRecords}"/>
		</td>
	</tr>

</table>