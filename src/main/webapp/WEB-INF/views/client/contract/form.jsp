<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-select code="client.list.label.project.title" path="project" choices="${projects}"/>

	<acme:input-textbox code="contract.form.label.code" path="code" />
	<acme:input-textbox code="contract.form.label.moment" path="moment"  readonly = "True"/>
	<acme:input-textbox code="contract.form.label.providerName" path="providerName" />
	<acme:input-textbox code="contract.form.label.customerName" path="customerName" />
	<acme:input-textbox code="contract.form.label.goals" path="goals" />
	<acme:input-money code="contract.form.label.budget" path="budget" />
	<acme:input-checkbox code="contract.form.label.isDraft" path="isDraft" readonly="true" />
	

	<jstl:choose>

		<jstl:when
			test="${acme:anyOf(_command, 'show|update|delete|publish') && isDraft == true}">
			<acme:submit code="project.form.button.update" action="/client/contract/update" />
			<acme:submit code="project.form.button.delete" action="/client/contract/delete" />
			
			<jstl:if test="${isDraft == true}">
				<acme:submit code="contract.form.button.publish" action="/client/contract/publish"/>
			</jstl:if>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="contract.form.button.create" action="/client/contract/create"/>
		</jstl:when>
		
	
		
	</jstl:choose>
</acme:form>
