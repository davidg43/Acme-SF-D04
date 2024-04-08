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
	<acme:input-textbox code="auditor.code-audit.form.correctiveActions" path="correctiveActions" readonly="true" />
	<acme:input-textbox code="auditor.code-audit.form.type" path="type" readonly="true" />
	<acme:input-textbox code="auditor.code-audit.form.mark" path="mark" readonly="true" />
	<acme:input-textbox code="auditor.code-audit.form.project" path="project" readonly="true" />
	<acme:input-url code="auditor.code-audit.form.link" path="link" readonly="true" />
	<acme:input-moment code="auditor.code-audit.form.execution" path="execution" readonly="true" />
	<acme:input-textbox code="auditor.code-audit.form.auditor" path="auditor" readonly="true" />	
</acme:form>
