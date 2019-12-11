<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Stuff view</title>
    <%--INCLUDE PAGE DEPENDENCIES--%>
    <jsp:include page="../commons/pageDependencies.jsp"/>
</head>
<body>

<%--INCLUDE TOP NAVIGATION BAR--%>
<jsp:include page="../commons/navbar.jsp"/>

<%--CONTAINER | START--%>
<div class="container mb-3 mt-3">

    <%--PAGE HEADER | START--%>
    <div class="page-header text-center">
        <h1 class="display-4">Stuff information</h1>
    </div>
    <%--PAGE HEADER | END--%>

    <%--STUFF IMG | START--%>
    <div>
        <img src="<c:url value="/stuff/picture/${stuff.id}"/>" class="rounded mx-auto d-block"
             style="max-width: 200px" alt="no picture">
    </div>
    <%--STUFF IMG | END--%>

    <%--STUFF TABLE | START--%>
    <div>
        <table class="table table-bordered">
            <thead class="thead-light">
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
                <td>Description</td>
                <td>${stuff.description}</td>
            </tr>
            <tr>
                <td>Cost</td>
                <td>${stuff.cost}</td>
            </tr>
            </tbody>
        </table>
    </div>
    <%--STUFF TABLE | END--%>

</div>
<%--CONTAINER | START--%>

<%--INCLUDE FOOTER--%>
<jsp:include page="../commons/footer.jsp"/>

</body>
</html>
