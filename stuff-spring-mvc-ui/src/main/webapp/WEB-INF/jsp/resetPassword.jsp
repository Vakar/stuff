<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Reset Password</title>
    <link href="<c:url value="/resources/css/loginForm.css" />" rel="stylesheet">
    <%--INCLUDE PAGE DEPENDENCIES--%>
    <jsp:include page="/WEB-INF/jsp/commons/style.jsp"/>
</head>
<body>

<%--INCLUDE TOP NAVIGATION BAR--%>
<jsp:include page="commons/navbar.jsp"/>

<%--CONTAINER | START--%>
<div class="container mb-3 mt-3">

    <%--RESET PASSWORD FORM | START--%>
    <div class="login-form">
        <form:form method="POST" action="/stuff/resetPassword" commandName="resetPasswordForm">
            <div class="form-group">
                <form:hidden class="form-control" path="username"/>
            </div>
            <div class="form-group">
                <form:label path="password">New Password</form:label>
                <form:password class="form-control" path="password"/>
                <form:errors class="form-text text-danger" path="password"/>
            </div>
            <div class="form-group">
                <form:label path="passwordConfirm">Confirm Password</form:label>
                <form:password class="form-control" path="passwordConfirm"/>
                <form:errors class="form-text text-danger" path="passwordConfirm"/>
            </div>
            <input class="btn btn-primary btn-block" type="submit" value="Submit"/>
        </form:form>
    </div>
    <%--RESET PASSWORD FORM | END--%>

</div>
<%--CONTAINER | END--%>

<%--INCLUDE FOOTER--%>
<jsp:include page="commons/footer.jsp"/>

<%--IMPORT JS FILES | START--%>
<jsp:include page="commons/js.jsp"/>
<%--IMPORT JS FILES | END--%>

</body>
</html>
