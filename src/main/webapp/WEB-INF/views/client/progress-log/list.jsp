<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="client.progress-log.list.label.recordId"
		path="recordId" width="40%" />
	<acme:list-column code="client.progress-log.list.label.completeness"
		path="completeness" width="20%" />
	<acme:list-column
		code="client.progress-log.list.label.responsiblePerson"
		path="reponsiblePerson" width="20%" />

</acme:list>

<jstl:if test="${isDraft}">
	<acme:button code="client.list.label.create"
		action="/client/progress-log/create?masterId=${masterId}" />
</jstl:if>

