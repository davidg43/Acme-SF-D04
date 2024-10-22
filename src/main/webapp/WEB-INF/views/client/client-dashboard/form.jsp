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
		<th scope="row">
			<acme:message code="client.dashboard.label.total-number-pl-rate-75"/>
		</th>
		<td>
			<acme:print value="${totalNumberProgressLogsRateMoreThan75}"/>
		</td>
	</tr>
</table>

<h2>
	<acme:message code="client.dashboard.form.title.budget-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="client.dashboard.label.currency"/>
		</th>
		<th scope="row">
			<acme:message code="client.dashboard.label.average-budget"/>
		</th>
		<th scope="row">
			<acme:message code="client.dashboard.label.deviation-budget"/>
		</th>
		<th scope="row">
			<acme:message code="client.dashboard.label.minimum-budget"/>
		</th>
		<th scope="row">
			<acme:message code="client.dashboard.label.maximum-budget"/>
		</th>
	</tr>
	<tr>
		<td>
			<acme:message code="client.dashboard.label.eur"/>
		</td>
		<td>
			<acme:print value="${averageBudgetOfContractsEUR}"/>
		</td>
		<td>
			<acme:print value="${deviationBudgetOfContractsEUR}"/>
		</td>
		<td>
			<acme:print value="${minimumBudgetOfContractsEUR}"/>
		</td>
		<td>
			<acme:print value="${maximumBudgetOfContractsEUR}"/>
		</td>
	</tr>
	<tr>
		<td>
			<acme:message code="client.dashboard.label.gbp"/>
		</td>
		<td>
			<acme:print value="${averageBudgetOfContractsGBP}"/>
		</td>
		<td>
			<acme:print value="${deviationBudgetOfContractsGBP}"/>
		</td>
		<td>
			<acme:print value="${minimumBudgetOfContractsGBP}"/>
		</td>
		<td>
			<acme:print value="${maximumBudgetOfContractsGBP}"/>
		</td>
	</tr>
	<tr>
		<td>
			<acme:message code="client.dashboard.label.usd"/>
		</td>
		<td>
			<acme:print value="${averageBudgetOfContractsUSD}"/>
		</td>
		<td>
			<acme:print value="${deviationBudgetOfContractsUSD}"/>
		</td>
		<td>
			<acme:print value="${minimumBudgetOfContractsUSD}"/>
		</td>
		<td>
			<acme:print value="${maximumBudgetOfContractsUSD}"/>
		</td>
	</tr>
</table>