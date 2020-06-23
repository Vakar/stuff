<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Forgot Password</title>
    <link href="<c:url value="/resources/css/loginForm.css" />" rel="stylesheet">
    <script src="https://www.google.com/recaptcha/api.js"></script>
    <jsp:include page="/WEB-INF/jsp/commons/style.jsp"/>
</head>
<body>

<jsp:include page="commons/navbar.jsp"/>

<header class="page-header text-center">
    <h1 class="display-5">Reset Password Request</h1>
</header>

<main class="container mb-3 mt-3">
    <section about="reset password request form">
        <div class="login-form">
            <form:form method="POST" action="generatePasswordResetToken"
                       commandName="resetPasswordRequestForm">
                <form:hidden path="reCaptchaSiteKey"/>
                <div class="form-group">
                    <form:label path="email">E-mail</form:label>
                    <form:input class="form-control" path="email"/>
                    <form:errors class="form-text text-error" path="email"/>
                </div>
                <div class="form-group d-flex justify-content-center">
                    <div class="g-recaptcha mx-auto"
                         data-sitekey="${resetPasswordRequestForm.reCaptchaSiteKey}"></div>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input class="btn btn-dark btn-block" type="submit" value="Send"/>
            </form:form>
        </div>
    </section>
</main>

<jsp:include page="commons/footer.jsp"/>
<jsp:include page="commons/js.jsp"/>

</body>
</html>
