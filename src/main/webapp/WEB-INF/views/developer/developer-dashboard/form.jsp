<%--
- form.jsp
-
- Copyright (C) 2012-2023 Rafael Corchuelo.
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
	<acme:message code="developer.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th>
			<acme:message code="developer.dashboard.label.total-training-modules-with-an-update-moment"/>
		</th>
		<td>
			<acme:print value="${totalTrainingModulesWithUpdateMoment}"/>
		</td>
	</tr>	
</table>

<table class="table table-sm">
	<tr>
		<th>
			<acme:message code="developer.dashboard.label.total-training-sessions-with-link"/>
		</th>
		<td>
			<acme:print value="${totalTrainingSessionsWithLink}"/>
		</td>
	</tr>	
</table>	

<h2>
	<acme:message code="developer.dashboard.form.title.statistics"/>
</h2>

<table class="table table-sm">
	<tr>
		<th>
			<acme:message code="developer.dashboard.form.label.statistics-title"/>
		</th>
		<th>
			<acme:message code="developer.dashboard.form.label.statistics-average"/>
		</th>
		<th>
			<acme:message code="developer.dashboard.form.label.statistics-deviation"/>
		</th>
		<th>
			<acme:message code="developer.dashboard.form.label.statistics-maximum"/>
		</th>
		<th>
			<acme:message code="developer.dashboard.form.label.statistics-minimum"/>
		</th>
	</tr>
	<tr>
		<td>
			<acme:message code="developer.dashboard.form.label.statistics-object-training-module"/>
		</td>
		<td>
			<acme:print value="${trainingModuleTimeStatistics.getAverageString()} h"/>
		</td>
		<td>
			<acme:print value="${trainingModuleTimeStatistics.getDeviationString()} h"/>
		</td>
		<td>
			<acme:print value="${trainingModuleTimeStatistics.getMaximumString()} h"/>
		</td>
		<td>
			<acme:print value="${trainingModuleTimeStatistics.getMinimumString()} h"/>
		</td>
	</tr>	
</table>


<acme:return/>