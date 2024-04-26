<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="client.list.label.code" path="code" width="30%"/>
	<acme:list-column code="client.list.label.customerName" path="customerName" width="60%"/>
	<acme:list-column code="client.list.label.providerName" path="providerName" width="10%"/>
	<acme:list-column code="client.list.label.goals" path="goals" width="10%"/>
	<acme:list-column code="client.list.label.budget" path="budget" width="10%"/>
	
	
</acme:list>