<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="manager.assignment.list.label.project"
		path="project" width="50%" />
	<acme:list-column code="manager.assignment.list.label.userStory"
		path="userStory" width="50%" />
</acme:list>



	<acme:button code="manager.assignment.list.label.create"
		action="/manager/assignment/create" />


