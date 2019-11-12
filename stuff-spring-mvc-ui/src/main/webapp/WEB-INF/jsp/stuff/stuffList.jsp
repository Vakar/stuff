<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>
    <title>Stuff List</title>
</head>
<body>

<sec:authorize access="isAuthenticated()">
    Welcome to your stuff <sec:authentication property="principal"/>!
    <div>
        <a href="<c:url value="/logout" />">Logout</a>
    </div>
</sec:authorize>

<div>
    <c:if test="${not empty stuffDtoList}">
        <table>
            <caption>Stuff Table</caption>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Cost</th>
                <th scope="col">Delete</th>
                <th scope="col">Edit</th>
            </tr>
            <c:forEach var="stuffDto" items="${stuffDtoList}">
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
        </table>
    </c:if>
    <a href="<c:url value="/stuff/addView" />">Add new Stuff</a>
</div>

</body>
</html>
