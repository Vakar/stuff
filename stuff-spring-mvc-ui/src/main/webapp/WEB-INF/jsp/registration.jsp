<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Registration</title>
    <link href="<c:url value="/resources/css/loginForm.css" />" rel="stylesheet">
    <script src="https://www.google.com/recaptcha/api.js"></script>
    <%--INCLUDE PAGE DEPENDENCIES--%>
    <jsp:include page="/WEB-INF/jsp/commons/pageDependencies.jsp"/>
</head>

<body>

<%--CONTAINER | START--%>
<div class="container mb-3 mt-3">

    <%--PAGE HEADER | START--%>
    <div class="page-header text-center">
        <h1 class="display-4">User Registration</h1>
    </div>
    <%--PAGE HEADER | END--%>

    <%--REGISTRATION FORM | START--%>
    <div class="login-form">
        <form:form method="POST" action="/stuff/registerUser" commandName="registration">
            <div class="form-group">
                <form:label path="username">User Name</form:label>
                <form:input class="form-control" path="username"/>
                <form:errors class="form-text text-danger" path="username"/>
            </div>
            <div class="form-group">
                <form:label path="email">E-mail</form:label>
                <form:input class="form-control" path="email"/>
                <form:errors class="form-text text-danger" path="email"/>
            </div>
            <div class="form-group">
                <form:label path="password">Password</form:label>
                <form:input class="form-control" path="password"/>
                <form:errors class="form-text text-danger" path="password"/>
            </div>
            <div class="form-group" align="center">
                <div class="g-recaptcha"
                     data-sitekey="${reCaptchaSiteKey}"></div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input class="btn btn-primary btn-block" type="submit" value="Submit"/>
        </form:form>
    </div>
    <%--REGISTRATION FORM | END--%>

</div>
<%--CONTAINER | END--%>

</body>
</html>