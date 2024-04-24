<%--
- form.jsp
-
- Copyright (C) 2012-2024 Rafael Corchuelo.
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

<acme:form> 
	<acme:input-textbox code="auditor.code-audit.form.label.code" path="code"/>
	<acme:input-textbox code="auditor.code-audit.form.label.correctiveActions" path="correctiveActions"/>	
	<acme:input-select code="auditor.code-audit.form.label.type" path="type" choices="${types}"/>
	<acme:input-textbox code="auditor.code-audit.form.label.mark" path="mark" readonly="true"/>
	<acme:input-select code="auditor.code-audit.form.label.project" path="project" choices="${projects}"/>
	<acme:input-moment code="auditor.code-audit.form.label.execution" path="execution" readonly="true"/>
	<acme:input-url code="auditor.code-audit.form.label.link" path="link"/>

	<acme:hidden-data path="id"/>
	<acme:hidden-data path="draftMode"/>

	<jstl:choose>
		<jstl:when test="${_command == 'show' && draftMode == false}">
			<acme:button code="auditor.code-audit.form.button.audit-record" action="/auditor/audit-record/list?masterId=${id}"/>			
		</jstl:when>	 
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && draftMode == true}">
			<acme:button code="auditor.code-audit.form.button.audit-record" action="/auditor/audit-record/list?masterId=${id}"/>
			<acme:submit code="auditor.code-audit.form.button.update" action="/auditor/code-audit/update"/>
			<acme:submit code="auditor.code-audit.form.button.delete" action="/auditor/code-audit/delete"/>
			<acme:submit code="auditor.code-audit.form.button.publish" action="/auditor/code-audit/publish"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="auditor.code-audit.form.button.create" action="/auditor/code-audit/create"/>
		</jstl:when>		
	</jstl:choose>
</acme:form>