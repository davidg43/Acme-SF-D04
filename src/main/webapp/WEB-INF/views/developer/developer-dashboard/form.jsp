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
		<th scope="row">
			<acme:message code="developer.dashboard.label.total-training-modules-with-an-update-moment"/>
		</th>
		<td>
			<acme:print value="${totalTrainingModulesWithUpdateMoment}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="developer.dashboard.label.total-training-sessions-with-link"/>
		</th>
		<td>
			<acme:print value="${totalTrainingSessionsWithLink}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="developer.dashboard.label.average-training-module-time"/>
		</th>
		<td>
			<acme:print value="${averageTrainingModuleTime}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="developer.dashboard.label.deviation-training-module-time"/>
		</th>
		<td>
			<acme:print value="${deviationTrainingModuleTime}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="developer.dashboard.label.min-training-module-time"/>
		</th>
		<td>
			<acme:print value="${minimumTrainingModuleTime}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="developer.dashboard.label.max-training-module-time"/>
		</th>
		<td>
			<acme:print value="${maximumTrainingModuleTime}"/>
		</td>
	</tr>	
</table>


<acme:return/>