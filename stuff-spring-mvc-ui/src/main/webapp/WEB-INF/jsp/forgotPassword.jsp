<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Forgot Password</title>
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
        <h1 class="display-4">Reset Password Request</h1>
    </div>
    <%--PAGE HEADER | END--%>

    <%--REGISTRATION FORM | START--%>
    <div class="login-form">
        <form:form method="POST" action="generatePasswordResetToken" commandName="resetPasswordRequestForm">
            <div class="form-group">
                <form:label path="email">E-mail</form:label>
                <form:input class="form-control" path="email"/>
                <form:errors class="form-text text-danger" path="email"/>
            </div>
            <div class="form-group" align="center">
                <div class="g-recaptcha"
                     data-sitekey="${reCaptchaSiteKey}"></div>
            </div>
            <input class="btn btn-primary btn-block" type="submit" value="Send"/>
        </form:form>
    </div>
    <%--REGISTRATION FORM | END--%>

</div>
<%--CONTAINER | START--%>

<%--INCLUDE FOOTER--%>
<jsp:include page="commons/footer.jsp"/>

<%--IMPORT JS FILES | START--%>
<jsp:include page="commons/js.jsp"/>
<%--IMPORT JS FILES | END--%>

</body>
</html>