<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>
    <title>Stuff List</title>
    <%--INCLUDE PAGE DEPENDENCIES--%>
    <jsp:include page="../commons/style.jsp"/>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

<%--INCLUDE TOP NAVIGATION BAR--%>
<jsp:include page="../commons/navbar.jsp"/>

<%--CONTAINER | START--%>
<div class="container mb-3 mt-3">

    <%--PAGE HEADER | START--%>
    <div class="page-header text-center">
        <h1 class="display-4">Welcome to your stuff <sec:authentication property="principal"/>!</h1>
    </div>
    <%--PAGE HEADER | END--%>

    <%--STUFF TABLE | START--%>
    <div>
        <c:if test="${not empty stuffList.stuff}">
            <table class="table table-bordered">
                <thead class="thead-light">
                <tr>
                    <th class="text-center" scope="col">Name</th>
                    <th class="text-center" scope="col">Cost</th>
                    <th class="text-center" scope="col">View</th>
                    <th class="text-center" scope="col">Edit</th>
                    <th class="text-center" scope="col">Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="stuff" items="${stuffList.stuff}">
                    <tr>
                        <td>${stuff.name}</td>
                        <td class="text-right">${stuff.cost}</td>
                        <td class="text-center">
                            <form action="<c:url value="/stuff/seeView"/>" method="post">
                                <input type="hidden" name="id" value="${stuff.id}">
                                <button type="submit" class="btn btn-link">
                                    <i class="fa fa-eye text-info"></i>
                                </button>
                            </form>
                        </td>
                        <td class="text-center">
                            <form action="<c:url value="/stuff/editView"/>" method="post">
                                <input type="hidden" name="id" value="${stuff.id}">
                                <button type="submit" class="btn btn-link">
                                    <i class="fa fa-pencil text-dark"></i>
                                </button>
                            </form>
                        </td>
                        <td class="text-center">
                            <form action="<c:url value="/stuff/delete" />" method="post">
                                <input type="hidden" name="id" value="${stuff.id}">
                                <button type="submit" class="btn btn-link">
                                    <i class="fa fa-remove text-danger"></i>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <td>Total cost:</td>
                    <td class="text-right">${stuffList.totalSum}</td>
                    <td></td>
                    <td></td>
                </tr>
                </tfoot>
            </table>
        </c:if>
    </div>
    <%--STUFF TABLE | END--%>

    <%--ADD NEW STUFF BUTTON | START--%>
    <div class="float-right">
        <a class="btn btn-info" role="button" href="<c:url value="/stuff/addView" />"
           title="Add new stuff to list.">Add
            new Stuff</a>
    </div>
    <%--ADD NEW STUFF BUTTON | END--%>

</div>
<%--CONTAINER | END--%>

<%--INCLUDE FOOTER--%>
<jsp:include page="../commons/footer.jsp"/>

<%--IMPORT JS FILES | START--%>
<jsp:include page="../commons/js.jsp"/>
<%--IMPORT JS FILES | END--%>

</body>
</html>
