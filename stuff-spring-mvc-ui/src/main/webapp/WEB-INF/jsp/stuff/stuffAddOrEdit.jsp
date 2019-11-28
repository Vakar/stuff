<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Stuff</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/loginForm.css" />">
    <script src="<c:url value="/resources/js/jquery.slim.min.js" />"></script>
    <script src="<c:url value="/resources/js/popper.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>

<%--INCLUDE TOP NAVIGATION BAR--%>
<jsp:include page="../comons/navbar.jsp"/>

<div>
    <form:form method="POST" action="/stuff/stuff/save" commandName="stuffDto">
        <div>
            <form:hidden path="id"/>
        </div>
        <div class="form-group">
            <form:label path="name">Name</form:label>
            <form:input path="name"/>
            <form:errors path="name"/>
        </div>
        <div class="form-group">
            <form:label path="cost">Cost</form:label>
            <form:input path="cost"/>
            <form:errors path="cost"/>
        </div>
        <input class="btn btn-primary btn-block" type="submit" value="Submit"/>
    </form:form>
</div>
</body>
</html>
