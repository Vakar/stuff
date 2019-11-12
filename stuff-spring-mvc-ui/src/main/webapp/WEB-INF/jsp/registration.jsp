<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <link href="<c:url value="/resources/css/loginForm.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery.slim.min.js" />"></script>
    <script src="<c:url value="/resources/js/popper.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <script src="https://www.google.com/recaptcha/api.js"></script>
</head>

<body>

<div>
    <h3 class="text-center">User Registration</h3>
</div>

<div class="login-form">
    <form:form method="POST" action="/stuff/registerUser" commandName="registration">
        <div class="form-group">
            <form:label path="userName">User Name</form:label>
            <form:input class="form-control" path="userName"/>
            <form:errors class="form-text text-danger" path="userName"/>
        </div>

        <div class="form-group">
            <form:label path="eMail">E-mail</form:label>
            <form:input class="form-control" path="eMail"/>
            <form:errors class="form-text text-danger" path="eMail"/>
        </div>

        <div class="form-group">
            <form:label path="password">Password</form:label>
            <form:input class="form-control" path="password"/>
            <form:errors class="form-text text-danger" path="password"/>
        </div>

        <div class="form-group" align="center">
            <div class="g-recaptcha" data-sitekey="6LegrloUAAAAAJQLfyqBisoeNO0PNC10xbP2dYmR"></div>
        </div>

        <input class="btn btn-primary btn-block" type="submit" value="Submit"/>

    </form:form>
</div>

</body>
</html>