<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>
    <title>Stuff List</title>
    <%--INCLUDE PAGE DEPENDENCIES--%>
    <jsp:include page="../comons/pageDependencies.jsp"/>
</head>
<body>

<%--INCLUDE TOP NAVIGATION BAR--%>
<jsp:include page="../comons/navbar.jsp"/>

<%--CONTAINER START--%>
<div class="container mb-3 mt-3">

    <%--PAGE HEADER START--%>
    <div class="page-header text-center">
        <h1 class="display-4">Welcome to your stuff <sec:authentication property="principal"/>!</h1>
    </div>
    <%--PAGE HEADER END--%>

    <%--STUFF TABLE START--%>
    <div>
        <c:if test="${not empty stuffListModel.stuffDtoList}">
            <table class="table table-bordered">
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
    </div>
    <%--STUFF TABLE END--%>

    <%--ADD NEW STUFF BUTTON START--%>
    <div class="float-right">
        <a class="btn btn-info" role="button" href="<c:url value="/stuff/addView" />"
           title="Add new stuff to list.">Add
            new Stuff</a>
    </div>
    <%--ADD NEW STUFF BUTTON END--%>

</div>
<%--CONTAINER END--%>

</body>
</html>
