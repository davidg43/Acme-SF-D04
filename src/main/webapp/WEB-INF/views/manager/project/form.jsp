<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form> 
    <acme:input-textbox code="project.form.label.code" path="code"/>
    <acme:input-textbox code="project.form.label.title" path="title"/>
    <acme:input-textbox code="project.form.label.abstractText" path="abstractText"/>
    <acme:input-money code="project.form.label.cost" path="cost"/>
    <acme:input-url code="project.form.label.link" path="link"/>
    <acme:input-select code="project.form.label.manager" path="manager" choices="${managers}"/>
    <acme:input-checkbox code="project.form.label.isDraft" path="isDraft"/>
    
    <jstl:choose>     
        <jstl:when test="${_command == 'show' && !project.isDraft}">
            <acme:button code="project.form.button.userStories" action="/project/userStory/list?projectId=${project.id}"/>           
        </jstl:when>
        <jstl:when test="${acme:anyOf(_command, 'show|update|delete') && project.isDraft}">
            <acme:button code="project.form.button.userStories" action="/project/userStory/list?projectId=${project.id}"/>
            <jstl:if test="${!project.hasFatalErrors}">
                <acme:submit code="project.form.button.update" action="/project/update"/>
                <acme:submit code="project.form.button.delete" action="/project/delete"/>
            </jstl:if>
        </jstl:when>
        <jstl:when test="${_command == 'create'}">
            <acme:submit code="project.form.button.create" action="/project/create"/>
        </jstl:when>        
    </jstl:choose>
</acme:form>
