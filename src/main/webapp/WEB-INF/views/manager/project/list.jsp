<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="manager.list.label.code" path="code" width="30%"/>
	<acme:list-column code="manager.list.label.title" path="title" width="60%"/>
	<acme:list-column code="manager.list.label.hasFatalErrors" path="hasFatalErrors" width="10%"/>
</acme:list>


