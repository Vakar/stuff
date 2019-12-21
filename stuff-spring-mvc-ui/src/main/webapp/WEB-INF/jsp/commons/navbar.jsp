<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="<c:url value="/" />">Stuff</a>
    <sec:authorize access="isAuthenticated()">
        <a class="navbar-brand" href="<c:url value="/stuff/list" />">List</a>
    </sec:authorize>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
            aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto"></ul>
        <sec:authorize access="isAuthenticated()">
            <form class="form-inline my-2 my-lg-0" action="<c:url value="/logout" />" method="post">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Logout</button>
            </form>
        </sec:authorize>
    </div>
</nav>