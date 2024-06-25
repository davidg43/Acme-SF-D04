<%@page%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-select code="client.progresslog.form.label.contract" path="contract" choices = "${contracts}" />
	<acme:input-textbox code="client.progresslog.form.label.recordId" path="recordId"/>
	<acme:input-double code="client.progresslog.form.label.completeness" path="completeness"  />
	<acme:input-textbox code="client.progresslog.form.label.comment" path="comment"   />
	<acme:input-moment code="client.progresslog.form.label.registrationMoment" path="registrationMoment" readonly = "True"/>
	<acme:input-textbox code="client.progresslog.form.label.responsiblePerson" path="reponsiblePerson" />
	<acme:input-checkbox code="client.progresslog.form.label.isDraft" path="isDraft" readonly="true" />
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && isDraft == true}">
			<acme:submit code="client.progresslog.form.button.update" action="/client/progress-log/update"/>
			<acme:submit code="client.progresslog.form.button.delete" action="/client/progress-log/delete"/>
			
			<jstl:if test="${isDraft == true}">
				<acme:submit code="contract.form.button.publish" action="/client/progress-log/publish"/>
			</jstl:if>
		</jstl:when>
		
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="client.progresslog.form.button.create" action="/client/progress-log/create"/>
		</jstl:when>
				
	</jstl:choose>		
	
</acme:form>