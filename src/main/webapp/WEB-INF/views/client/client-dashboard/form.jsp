<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<h2>
	<acme:message code="client.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="client.dashboard.label.total-numberprogressLogs-rate-less-25"/>
		</th>
		<td>
			<acme:print value="${totalNumberProgressLogsRateLess25}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.dashboard.label.total-number-pl-rate-2550"/>
		</th>
		<td>
			<acme:print value="${totalNumberProgressLogsRateBetween25And50}"/>
		</td>
	</tr>
		<tr>
		<th scope="row">
			<acme:message code="client.dashboard.label.total-number-pl-rate-5075"/>
		</th>
		<td>
			<acme:print value="${totalNumberProgressLogsRateBetween50And75}"/>
		</td>
	</tr>
	<tr>
		<tr>
		<th scope="row">
			<acme:message code="client.dashboard.label.total-number-pl-rate-75"/>
		</th>
		<td>
			<acme:print value="${totalNumberProgressLogsRateMoreThan75}"/>
		</td>
	</tr>
	<tr>
		<tr>
		<th scope="row">
			<acme:message code="client.dashboard.label.average-budget"/>
		</th>
		<td>
			<acme:print value="${averageBudgetOfContracts}"/>
		</td>
	</tr>
	<tr>
		<tr>
		<th scope="row">
			<acme:message code="client.dashboard.label.deviation-budget"/>
		</th>
		<td>
			<acme:print value="${deviationBudgetOfContracts}"/>
		</td>
	</tr>
	<tr>
		<tr>
		<th scope="row">
			<acme:message code="client.dashboard.label.minimum-budget"/>
		</th>
		<td>
			<acme:print value="${minimumBudgetOfContracts}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="client.dashboard.label.maximum-budget"/>
		</th>
		<td>
			<acme:print value="${maximumBudgetOfContracts}"/>
		</td>
	</tr>
	
	

</table>