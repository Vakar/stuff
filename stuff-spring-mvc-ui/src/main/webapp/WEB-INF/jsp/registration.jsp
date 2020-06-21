<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <title>Registration</title>
    <link href="<c:url value="/resources/css/loginForm.css" />" rel="stylesheet">
    <script src="https://www.google.com/recaptcha/api.js"></script>
    <%--INCLUDE PAGE DEPENDENCIES--%>
    <jsp:include page="/WEB-INF/jsp/commons/style.jsp"/>
</head>

<body>

<%--INCLUDE TOP NAVIGATION BAR--%>
<jsp:include page="commons/navbar.jsp"/>

<%--CONTAINER | START--%>
<div class="container mb-3 mt-3">

    <%--PAGE HEADER | START--%>
    <div class="page-header text-center">
        <h1 class="display-5">User Registration</h1>
    </div>
    <%--PAGE HEADER | END--%>

    <%--REGISTRATION FORM | START--%>
    <div class="login-form">
        <form:form method="POST" action="/registerUser" commandName="registration">
            <form:hidden path="reCaptchaSiteKey"/>
            <div class="form-group">
                <form:label path="username">User Name</form:label>
                <form:input class="form-control" path="username"/>
                <form:errors class="form-text text-error" path="username"/>
            </div>
            <div class="form-group">
                <form:label path="email">E-mail</form:label>
                <form:input class="form-control" path="email"/>
                <form:errors class="form-text text-error" path="email"/>
            </div>
            <div class="form-group">
                <form:label path="password">Password</form:label>
                <form:input class="form-control" path="password"/>
                <form:errors class="form-text text-error" path="password"/>
            </div>
            <div class="form-group d-flex justify-content-center">
                <div class="g-recaptcha"
                     data-sitekey="${registration.reCaptchaSiteKey}"></div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input class="btn btn-dark btn-block" type="submit" value="Submit"/>
        </form:form>
    </div>
    <%--REGISTRATION FORM | END--%>

</div>
<%--CONTAINER | END--%>

<%--INCLUDE FOOTER--%>
<jsp:include page="commons/footer.jsp"/>

<%--IMPORT JS FILES | START--%>
<jsp:include page="commons/js.jsp"/>
<%--IMPORT JS FILES | END--%>

</body>
</html>