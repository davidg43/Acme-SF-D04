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
				code="client.dashboard.label.averageCostOfProjects" /></th>
		<td><acme:print value="${averageCostOfProjects}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="client.dashboard.label.deviationOfCostOfProjects" /></th>
		<td><acme:print value="${deviationOfCostOfProjects}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="client.dashboard.label.minimumCostOfProjects" /></th>
		<td><acme:print value="${minimumCostOfProjects}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="client.dashboard.label.maximumCostOfProjects" /></th>
		<td><acme:print value="${maximumCostOfProjects}" /></td>
	</tr>




</table>