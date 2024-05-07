<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="manager.list.label.title" path="title" width="40%"/>
	<acme:list-column code="manager.list.label.priority" path="priority" width="20%"/>
	<acme:list-column code="manager.list.label.description" path="description" width="40%"/>
</acme:list>

<acme:button code="manager.list.label.create" action="/manager/user-story/create"/>


