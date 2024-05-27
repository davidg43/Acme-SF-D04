<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="user-story.form.label.title" path="title" />
	<acme:input-textbox code="user-story.form.label.description"
		path="description" />

	<acme:input-textbox code="user-story.form.label.acceptanceCriteria"
		path="acceptanceCriteria" />
	<acme:input-integer code="user-story.form.label.estimatedCost"
		path="estimatedCost" />
	<acme:input-select code="user-story.form.label.priority" path="priority" choices="${priorities}"/>
	<acme:input-url code="user-story.form.label.link" path="link" />
	<acme:input-checkbox code="user-story.form.label.isDraft"
		path="isDraft" readonly="true" />

	<jstl:choose>

		<jstl:when
			test="${acme:anyOf(_command, 'show|update|delete|publish') && isDraft == true}">
			<acme:submit code="user-story.form.button.update"
				action="/manager/user-story/update" />
			<acme:submit code="user-story.form.button.delete"
				action="/manager/user-story/delete" />

			<jstl:if test="${isDraft == true}">
				<acme:submit code="user-story.form.button.publish"
					action="/manager/user-story/publish" />
			</jstl:if>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="user-story.form.button.create"
				action="/manager/user-story/create" />
		</jstl:when>

	</jstl:choose>
</acme:form>
