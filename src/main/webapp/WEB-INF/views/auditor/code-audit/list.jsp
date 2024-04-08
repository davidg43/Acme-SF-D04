<%--
- list.jsp
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

<acme:list>
	<acme:list-column code="auditor.code-audit.list.correctiveActions" path="correctiveActions" width="40%"/>
	<acme:list-column code="auditor.code-audit.list.type" path="type" width="20%"/>	
	<acme:list-column code="auditor.code-audit.list.project" path="project" width="40%"/>	
</acme:list>


<acme:button code="auditor.code-audit.list.button.create" action="/auditor/code-audit/create"/>
<acme:button code="auditor.code-audit.list.button.update" action="/auditor/code-audit/update"/>
<acme:button code="auditor.code-audit.list.button.delete" action="/auditor/code-audit/delete"/>