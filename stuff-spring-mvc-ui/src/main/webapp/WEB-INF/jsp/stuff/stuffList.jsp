<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>
    <title>Stuff List</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/loginForm.css" />">
    <script src="<c:url value="/resources/js/jquery.slim.min.js" />"></script>
    <script src="<c:url value="/resources/js/popper.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>

<%--INCLUDE TOP NAVIGATION BAR--%>
<jsp:include page="../comons/navbar.jsp"/>

<div class="container mb-3 mt-3">

    <div class="page-header text-center">
        <h1 class="display-4">Welcome to your stuff <sec:authentication property="principal"/>!</h1>
    </div>

    <c:if test="${not empty stuffListModel.stuffDtoList}">
        <table class="table table-bordered" style="width: 100%" id="stuffList">
            <thead class="thead-light">
            <tr>
                <th class="text-center" scope="col">Name</th>
                <th class="text-center" scope="col">Cost</th>
                <th class="text-center" scope="col">Delete</th>
                <th class="text-center" scope="col">Edit</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="stuffDto" items="${stuffListModel.stuffDtoList}">
                <tr>
                    <td>${stuffDto.name}</td>
                    <td class="text-right">${stuffDto.cost}</td>
                    <td class="text-center">
                        <form action="<c:url value="/stuff/delete" />" method="post">
                            <input type="hidden" name="id" value="${stuffDto.id}">
                            <input type="submit" value="Delete">
                        </form>
                    </td>
                    <td class="text-center">
                        <form action="<c:url value="/stuff/editView"/>" method="post">
                            <input type="hidden" name="id" value="${stuffDto.id}">
                            <input type="submit" value="Edit">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <td>Total cost:</td>
                <td class="text-right">${stuffListModel.totalSum}</td>
                <td></td>
                <td></td>
            </tr>
            </tfoot>
        </table>
    </c:if>
    <a class="btn btn-info float-right" role="button" href="<c:url value="/stuff/addView" />">Add
        new Stuff</a>
</div>

</body>
</html>
