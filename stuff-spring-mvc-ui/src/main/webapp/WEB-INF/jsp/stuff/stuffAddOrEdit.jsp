<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Stuff</title>
    <%--INCLUDE PAGE DEPENDENCIES--%>
    <jsp:include page="../comons/pageDependencies.jsp"/>
</head>
<body>

<%--INCLUDE TOP NAVIGATION BAR--%>
<jsp:include page="../comons/navbar.jsp"/>

<%--CONTAINER START--%>
<div class="container mb-3 mt-3">

    <%--ADD NEW STUFF FORM START--%>
    <div>
        <form:form method="POST" action="/stuff/stuff/save" commandName="stuffDto">
            <div>
                <form:hidden path="id"/>
            </div>
            <div class="form-group">
                <form:label path="name">Stuff Name</form:label>
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="text-danger"/>
            </div>
            <div class="form-group">
                <form:label path="cost">Stuff Cost</form:label>
                <form:input path="cost" cssClass="form-control"/>
                <form:errors path="cost" cssClass="text-danger"/>
            </div>
            <button class="btn btn-primary" type="submit">Submit</button>
        </form:form>
    </div>
    <%--ADD NEW STUFF FORM END--%>

</div>
<%--CONTAINER END--%>

</body>
</html>
