<!DOCTYPE html>
<html>
<head>
    <title>Information Page</title>
    <%--INCLUDE PAGE DEPENDENCIES--%>
    <jsp:include page="/WEB-INF/jsp/commons/style.jsp"/>
</head>
<body>

<%--INCLUDE TOP NAVIGATION BAR--%>
<jsp:include page="commons/navbar.jsp"/>

<%--CONTAINER | START--%>
<div class="container mb-3 mt-3">
    <div class="d-flex p-2">
        <div class="jumbotron">
            <h3>${title}</h3>
            <p>${message}</p>
        </div>
    </div>
</div>
<%--CONTAINER | END--%>

<%--INCLUDE FOOTER--%>
<jsp:include page="commons/footer.jsp"/>

<%--IMPORT JS FILES | START--%>
<jsp:include page="commons/js.jsp"/>
<%--IMPORT JS FILES | END--%>

</body>
</html>
