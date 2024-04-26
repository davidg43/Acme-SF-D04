<%--
- list.jsp
-
- Copyright (C) 2012-2023 Rafael Corchuelo.
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
	<acme:list-column code="developer.training-session.list.label.code" path="code" width="25%"/>
	<acme:list-column code="developer.training-module.list.label.draftMode" path="draftMode" width="25%"/>
	<acme:list-column code="developer.training-session.list.label.trainingModule" path="trainingModule" width="25%"/>
</acme:list>


<acme:button code="developer.training-session.list.button.create" action="/developer/training-session/create?masterId=${masterId}"/>
	

