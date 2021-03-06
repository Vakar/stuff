<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Stuff List</title>
    <jsp:include page="../commons/style.jsp"/>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

<jsp:include page="../commons/navbar.jsp"/>

<header class="page-header text-center">
    <h1 class="display-5">Stuff list</h1>
</header>

<main class="container mb-3 mt-3">
    <article about="stuff list">
        <section about="stuff table">
            <c:if test="${not empty stuffList.stuff}">
                <table class="table table-bordered table-dark">
                    <caption>List of your saved stuff</caption>
                    <thead>
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
                                <form class="m-0" action="<c:url value="/stuff/seeView"/>"
                                      method="post">
                                    <input type="hidden" name="id" value="${stuff.id}">
                                    <input type="hidden" name="${_csrf.parameterName}"
                                           value="${_csrf.token}"/>
                                    <a onclick="this.closest('form').submit();return false;">
                                        <i class="fa fa-eye text-info"></i>
                                    </a>
                                </form>
                            </td>
                            <td class="text-center">
                                <form class="m-0" action="<c:url value="/stuff/editView"/>"
                                      method="post">
                                    <input type="hidden" name="id" value="${stuff.id}">
                                    <input type="hidden" name="${_csrf.parameterName}"
                                           value="${_csrf.token}"/>
                                    <a onclick="this.closest('form').submit();return false;">
                                        <i class="fa fa-pencil text-white"></i>
                                    </a>
                                </form>
                            </td>
                            <td class="text-center">
                                <form class="m-0" action="<c:url value="/stuff/delete" />"
                                      method="post">
                                    <input type="hidden" name="id" value="${stuff.id}">
                                    <input type="hidden" name="${_csrf.parameterName}"
                                           value="${_csrf.token}"/>
                                    <a onclick="this.closest('form').submit();return false;">
                                        <i class="fa fa-remove text-danger"></i>
                                    </a>
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
        </section>
        <section about="add new stuff button">
            <div class="float-right">
                <a class="btn btn-info" role="button" href="<c:url value="/stuff/addView" />"
                   title="Add new stuff to list.">Add
                    new Stuff</a>
            </div>
        </section>
    </article>
</main>

<jsp:include page="../commons/footer.jsp"/>
<jsp:include page="../commons/js.jsp"/>

</body>
</html>
