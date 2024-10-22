<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<h2>
	<acme:message code="client.dashboard.form.title.general-indicators" />
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row"><acme:message
				code="client.dashboard.label.totalMustUserStories" /></th>
		<td><acme:print value="${totalMustUserStories}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="client.dashboard.label.totalShouldUserStories" /></th>
		<td><acme:print value="${totalShouldUserStories}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="client.dashboard.label.totalCouldUserStories" /></th>
		<td><acme:print value="${totalCouldUserStories}" /></td>
	</tr>
	<tr>
	<tr>
		<th scope="row"><acme:message
				code="client.dashboard.label.totalWontUserStories" /></th>
		<td><acme:print value="${totalWontUserStories}" /></td>
	</tr>
	<tr>
	<tr>
		<th scope="row"><acme:message
				code="client.dashboard.label.averageEstimatedCostOfUserStories" /></th>
		<td><acme:print value="${averageEstimatedCostOfUserStories}" /></td>
	</tr>
	<tr>
	<tr>
		<th scope="row"><acme:message
				code="client.dashboard.label.deviationOfEstimatedCostOfUserStories" />
		</th>
		<td><acme:print value="${deviationOfEstimatedCostOfUserStories}" />
		</td>
	</tr>
	<tr>
	<tr>
		<th scope="row"><acme:message
				code="client.dashboard.label.minimumEstimatedCostOfUserStories" /></th>
		<td><acme:print value="${minimumEstimatedCostOfUserStories}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="client.dashboard.label.maximumEstimatedCostOfUserStories" /></th>
		<td><acme:print value="${maximumEstimatedCostOfUserStories}" /></td>
	</tr>
	
	<tr>
		<th scope="row"><acme:message
				code="client.dashboard.label.averageCostOfProjectsEUR" /></th>
		<td><acme:print value="${averageCostOfProjectsEUR}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="client.dashboard.label.deviationOfCostOfProjectsEUR" /></th>
		<td><acme:print value="${deviationOfCostOfProjectsEUR}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="client.dashboard.label.minimumCostOfProjectsEUR" /></th>
		<td><acme:print value="${minimumCostOfProjectsEUR}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="client.dashboard.label.maximumCostOfProjectsEUR" /></th>
		<td><acme:print value="${maximumCostOfProjectsEUR}" /></td>
	</tr>
	
	<tr>
		<th scope="row"><acme:message
				code="client.dashboard.label.averageCostOfProjectsGBP" /></th>
		<td><acme:print value="${averageCostOfProjectsGBP}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="client.dashboard.label.deviationOfCostOfProjectsGBP" /></th>
		<td><acme:print value="${deviationOfCostOfProjectsGBP}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="client.dashboard.label.minimumCostOfProjectsGBP" /></th>
		<td><acme:print value="${minimumCostOfProjectsGBP}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="client.dashboard.label.maximumCostOfProjectsGBP" /></th>
		<td><acme:print value="${maximumCostOfProjectsGBP}" /></td>
	</tr>
	
	
	<tr>
		<th scope="row"><acme:message
				code="client.dashboard.label.averageCostOfProjectsUSD" /></th>
		<td><acme:print value="${averageCostOfProjectsUSD}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="client.dashboard.label.deviationOfCostOfProjectsUSD" /></th>
		<td><acme:print value="${deviationOfCostOfProjectsUSD}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="client.dashboard.label.minimumCostOfProjectsUSD" /></th>
		<td><acme:print value="${minimumCostOfProjectsUSD}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="client.dashboard.label.maximumCostOfProjectsUSD" /></th>
		<td><acme:print value="${maximumCostOfProjectsUSD}" /></td>
	</tr>
	




</table>