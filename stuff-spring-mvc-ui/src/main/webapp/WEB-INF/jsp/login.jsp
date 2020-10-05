<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome to Stuff</title>
    <link href="<c:url value="/resources/css/loginForm.css" />" rel="stylesheet">
    <jsp:include page="/WEB-INF/jsp/commons/style.jsp"/>
</head>

<jsp:include page="commons/navbar.jsp"/>

<header class="page-header text-center">
    <h5>Demo credentials:</h5>
    <p><b>Username:</b> demo<br/><b>Password:</b> qwer1234</p>
</header>

<main class="container">
    <div class="login-form">
        <form name='loginForm' action="<c:url value="/login" />" method="post">
            <section about="Form header">
                <h2 class="text-center">Login</h2>
            </section>
            <section about="User name input" class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username" required="required" autofocus>
            </section>
            <section about="User password input" class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" required="required">
            </section>
            <section about="Forgot password">
                <p class="text-right"><a class="badge badge-info mt-2" href="forgotPassword">Forgot password?</a></p>
            </section>
            <section about="Remember me input" class="form-check">
                <input type="checkbox" class="form-check-input" name="remember-me" id="remember-me"/>
                <label for="remember-me" class="form-check-label">remember me</label>
            </section>
            <section about="Submit form button" class="form-group">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-dark btn-block">Login</button>
            </section>
            <section about="Link to registration page">
                <p class="text-center">
                    <a class="badge badge-light mt-2" href="registration">Create an Account</a>
                </p>
            </section>
        </form>
    </div>
</main>

<jsp:include page="commons/footer.jsp"/>
<jsp:include page="commons/js.jsp"/>

</body>
</html>
