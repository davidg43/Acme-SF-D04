<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="project.form.label.code" path="code" />
	<acme:input-textbox code="project.form.label.title" path="title" />
	<acme:input-textbox code="project.form.label.abstractText"
		path="abstractText" />
	<acme:input-money code="project.form.label.cost" path="cost" />
	<acme:input-url code="project.form.label.link" path="link" />
	<acme:input-checkbox code="project.form.label.isDraft" path="isDraft" readonly="true" />
	<acme:input-checkbox code="project.form.label.hasFatalErrors" path="hasFatalErrors"  readonly="true"/>
	<acme:button code="project.form.button.assignment" action="/manager/assignment/list?projectId=${projectId}"/>

	<jstl:choose>

		<jstl:when
			test="${acme:anyOf(_command, 'show|update|delete|publish') && isDraft == true}">
			<acme:submit code="project.form.button.update"
				action="/manager/project/update" />
			<acme:submit code="project.form.button.delete"
				action="/manager/project/delete" />
				
			<jstl:if test="${isDraft == true && publishable == true }">
				<acme:submit code="project.form.button.publish" action="/manager/project/publish"/>
			</jstl:if>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="project.form.button.create" action="/manager/project/create"/>
		</jstl:when>
		
	
		
	</jstl:choose>
</acme:form>
