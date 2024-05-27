<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-select code="manager.assignment.list.label.project" path="project" choices="${projects}"/>
	<acme:input-select code="manager.assignment.list.label.userStory" path="userStory" choices="${userStories}" />


	<jstl:choose>
		<jstl:when
			test="${acme:anyOf(_command, 'show|update|delete')}">
			<acme:submit code="assignment.form.button.update"
				action="/manager/assignment/update" />
			<acme:submit code="assignment.form.button.delete"
				action="/manager/assignment/delete" />
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="project.form.button.create"
				action="/manager/assignment/create" />

		</jstl:when>
	</jstl:choose>
</acme:form>
