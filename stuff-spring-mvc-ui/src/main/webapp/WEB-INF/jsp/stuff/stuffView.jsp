<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Stuff view</title>
    <jsp:include page="../commons/style.jsp"/>
</head>
<body>

<jsp:include page="../commons/navbar.jsp"/>

<header class="page-header text-center">
    <h1 class="display-5">Stuff details</h1>
</header>

<main class="container mb-3 mt-3">
    <article about="stuff details">
        <section about="stuff image">
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
        </section>
        <section about="stuff details table">
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
        </section>
        <section about="stuff edit button">
            <div class="float-right">
                <form action="<c:url value="/stuff/editView"/>" method="post">
                    <input type="hidden" name="id" value="${stuff.id}">
                    <input type="submit" class="btn btn-info" value="Edit">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </div>
        </section>
    </article>
</main>

<jsp:include page="../commons/footer.jsp"/>
<jsp:include page="../commons/js.jsp"/>

</body>
</html>