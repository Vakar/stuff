<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Stuff view</title>
    <%--INCLUDE PAGE DEPENDENCIES--%>
    <jsp:include page="../commons/style.jsp"/>
</head>
<body>

<%--INCLUDE TOP NAVIGATION BAR--%>
<jsp:include page="../commons/navbar.jsp"/>

<%--CONTAINER | START--%>
<div class="container mb-3 mt-3">

    <%--PAGE HEADER | START--%>
    <div class="page-header text-center">
        <h1 class="display-5">Stuff details</h1>
    </div>
    <%--PAGE HEADER | END--%>

    <%--STUFF IMG | START--%>
    <c:choose>
        <c:when test="${isPictureExists}">
            <div>
                <img src="<c:url value="/stuff/picture/${stuff.id}"/>"
                     class="rounded mx-auto mb-3 d-block"
                     style="max-width: 200px" alt="stuff picture">
            </div>
        </c:when>
        <c:otherwise>
            <div>
                <img src="<c:url value="/resources/img/no-image-available.png" />"
                     class="rounded mx-auto mb-3 d-block"
                     style="max-width: 200px" alt="no picture">
            </div>
        </c:otherwise>
    </c:choose>
    <%--STUFF IMG | END--%>

    <%--STUFF TABLE | START--%>
    <div>
        <table class="table table-bordered table-dark">
            <caption>${stuff.name} details table</caption>
            <thead>
            <tr>
                <th class="text-center" scope="col">Name</th>
                <th class="text-center" scope="col">Value</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Name</td>
                <td>${stuff.name}</td>
            </tr>
            <tr>
                <td>Brand</td>
                <td>${stuff.brand}</td>
            </tr>
            <tr>
                <td>Notes</td>
                <td>${stuff.description}</td>
            </tr>
            <tr>
                <td>Cost</td>
                <td>${stuff.cost}</td>
            </tr>
            <tr>
                <td>Commission Date</td>
                <td>${stuff.commissionDate}</td>
            </tr>
            </tbody>
        </table>
    </div>
    <%--STUFF TABLE | END--%>

    <%--STUFF EDITE BUTTON | START--%>
    <div class="float-right">
        <form action="<c:url value="/stuff/editView"/>" method="post">
            <input type="hidden" name="id" value="${stuff.id}">
            <input type="submit" class="btn btn-info" value="Edit">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>
    <%--STUFF EDITE BUTTON | END--%>

</div>
<%--CONTAINER | START--%>

<%--INCLUDE FOOTER--%>
<jsp:include page="../commons/footer.jsp"/>

<%--IMPORT JS FILES | START--%>
<jsp:include page="../commons/js.jsp"/>
<%--IMPORT JS FILES | END--%>

</body>
</html>