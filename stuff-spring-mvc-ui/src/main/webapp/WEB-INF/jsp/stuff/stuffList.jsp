<%@ page contentType="text/html;charset=UTF-8"%>
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

<div id="welcomeContent" class="text-center">
    <h5>Welcome to your stuff <sec:authentication property="principal"/>!</h5>
</div>

<div>
    <c:if test="${not empty stuffListModel.stuffDtoList}">
        <table>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Cost</th>
                <th scope="col">Delete</th>
                <th scope="col">Edit</th>
            </tr>
            <c:forEach var="stuffDto" items="${stuffListModel.stuffDtoList}">
                <tr>
                    <td>${stuffDto.name}</td>
                    <td>${stuffDto.cost}</td>
                    <td>
                        <form action="<c:url value="/stuff/delete" />" method="post">
                            <input type="hidden" name="id" value="${stuffDto.id}">
                            <input type="submit" value="D">
                        </form>
                    </td>
                    <td>
                        <form action="<c:url value="/stuff/editView"/>" method="post">
                            <input type="hidden" name="id" value="${stuffDto.id}">
                            <input type="submit" value="E">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td>Total sum:</td>
                <td>${stuffListModel.totalSum}</td>
                <td></td>
                <td></td>
            </tr>
        </table>
    </c:if>
    <a href="<c:url value="/stuff/addView" />">Add new Stuff</a>
</div>

</body>
</html>
