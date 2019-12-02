<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html lang="en">
<head>
    <title>Welcome to Stuff</title>
    <link href="<c:url value="/resources/css/loginForm.css" />" rel="stylesheet">
    <%--INCLUDE PAGE DEPENDENCIES--%>
    <jsp:include page="/WEB-INF/jsp/commons/pageDependencies.jsp"/>
</head>
<body onload='document.loginForm.username.focus();'>

<%--CONTAINER START--%>
<div class="container mb-3 mt-3">

    <%--PAGE HEADER START--%>
    <div class="page-header text-center">
        <h1 class="display-4">Welcome to Stuff!</h1>
    </div>
    <%--PAGE HEADER END--%>

    <%--AUTHORIZATION SECTION START--%>
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
            </div>
            <div class="form-group">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary btn-block">Log in</button>
            </div>
        </form>
        <p class="text-center"><a href="registration">Create an Account</a></p>
    </div>
    <%--AUTHORIZATION SECTION END--%>

</div>
<%--CONTAINER END--%>

</body>
</html>
