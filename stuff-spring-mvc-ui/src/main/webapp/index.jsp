<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html lang="en">
<head>
    <title>Welcome to Stuff</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <link href="<c:url value="/resources/css/loginForm.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery.slim.min.js" />"></script>
    <script src="<c:url value="/resources/js/popper.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body onload='document.loginForm.username.focus();'>

<div>
    <h3 class="text-center">Welcome to Stuff</h3>
</div>

<sec:authorize access="!isAuthenticated()">
    <div class="login-form">
        <form name='loginForm' action="<c:url value="/login" />" method="post">
            <h2 class="text-center">Log in</h2>
            <div class="form-group">
                <input type="text" class="form-control" name="username" placeholder="Username" required="required">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" name="password" placeholder="Password" required="required">
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block">Log in</button>
            </div>
        </form>
        <p class="text-center"><a href="registration">Create an Account</a></p>
    </div>
</sec:authorize>

<sec:authorize access="hasRole('USER')">
    <div class="login-form">
        <a class="btn btn-primary btn-block" href="<c:url value="/stuff/list" />">My Stuff</a>
        <a class="btn btn-primary btn-block" href="<c:url value="/logout" />">Logout</a>
    </div>
</sec:authorize>
</body>
</html>
