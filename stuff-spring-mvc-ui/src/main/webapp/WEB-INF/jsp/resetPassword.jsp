<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Reset Password</title>
    <link href="<c:url value="/resources/css/loginForm.css" />" rel="stylesheet">
    <jsp:include page="/WEB-INF/jsp/commons/style.jsp"/>
</head>
<body>

<jsp:include page="commons/navbar.jsp"/>

<header class="page-header text-center">
    <h1 class="display-5">Reset Password</h1>
</header>

<main class="container mb-3 mt-3">
    <section about="reset password form">
        <div class="login-form">
            <form:form method="POST" action="resetPassword" commandName="resetPasswordForm">
                <div class="form-group">
                    <form:hidden class="form-control" path="username"/>
                </div>
                <div class="form-group">
                    <form:label path="password">New Password</form:label>
                    <form:password class="form-control" path="password"/>
                    <form:errors class="form-text text-error" path="password"/>
                </div>
                <div class="form-group">
                    <form:label path="passwordConfirm">Confirm Password</form:label>
                    <form:password class="form-control" path="passwordConfirm"/>
                    <form:errors class="form-text text-error" path="passwordConfirm"/>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input class="btn btn-dark btn-block" type="submit" value="Submit"/>
            </form:form>
        </div>
    </section>
</main>

<jsp:include page="commons/footer.jsp"/>
<jsp:include page="commons/js.jsp"/>

</body>
</html>
