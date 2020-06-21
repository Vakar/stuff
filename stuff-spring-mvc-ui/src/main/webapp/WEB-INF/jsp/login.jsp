<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html lang="en">
<head>
    <title>Welcome to Stuff</title>
    <link href="<c:url value="/resources/css/loginForm.css" />" rel="stylesheet">
    <%--INCLUDE PAGE DEPENDENCIES--%>
    <jsp:include page="/WEB-INF/jsp/commons/style.jsp"/>
</head>
<body onload='document.loginForm.username.focus();'>

<%--CONTAINER | START--%>
<div class="container">

    <%--INCLUDE TOP NAVIGATION BAR--%>
    <jsp:include page="commons/navbar.jsp"/>

    <%--PAGE HEADER | START--%>
    <div class="page-header text-center">
        <h1 class="display-5">Welcome to Stuff</h1>
    </div>
    <%--PAGE HEADER | END--%>

    <%--AUTHORIZATION SECTION | START--%>
    <div class="login-form">
        <form name='loginForm' action="<c:url value="/login" />" method="post">
            <h2 class="text-center">Log in</h2>
            <div class="form-group">
                <input type="text" class="form-control" name="username" placeholder="Username"
                       required="required">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" name="password"
                       placeholder="Password" required="required">
                <p class="text-right"><a class="badge badge-info mt-2" href="forgotPassword">Forgot password?</a></p>
            </div>
            <div class="form-check">
                <input type="checkbox" class="form-check-input" name="remember-me" id="remember-me"/>
                <label for="remember-me" class="form-check-label">remember me</label>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-dark btn-block">Log in</button>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <p class="text-center"><a class="badge badge-light mt-2" href="registration">Create an Account</a></p>
    </div>
    <%--AUTHORIZATION SECTION | END--%>

</div>
<%--CONTAINER | END--%>

<%--INCLUDE FOOTER--%>
<jsp:include page="commons/footer.jsp"/>

<%--IMPORT JS FILES | START--%>
<jsp:include page="commons/js.jsp"/>
<%--IMPORT JS FILES | END--%>

</body>
</html>
