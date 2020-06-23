<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-dark fixed-top bg-dark">
    <div>
        <a class="navbar-brand" href="<c:url value="/" />">Stuff</a>
        <sec:authorize access="isAuthenticated()">
            <a class="navbar-brand" href="<c:url value="/stuff/list" />">List</a>
        </sec:authorize>
    </div>
    <sec:authorize access="isAuthenticated()">
        <form class="form-inline my-2 my-lg-0" action="<c:url value="/logout" />" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">LOGOUT</button>
        </form>
    </sec:authorize>
</nav>