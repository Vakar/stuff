<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit Stuff</title>
    <jsp:include page="../commons/style.jsp"/>
</head>
<body>

<jsp:include page="../commons/navbar.jsp"/>

<header class="page-header text-center">
    <h1 class="display-5">Stuff form</h1>
</header>

<main class="container mb-3 mt-3">
    <article about="contains form for stuff">
        <section about="stuff image preview">
            <c:choose>
                <c:when test="${isPictureExists}">
                    <img src="<c:url value="/stuff/picture/${stuffDto.id}"/>"
                         class="rounded mx-auto mb-3 d-block"
                         style="max-width: 200px" alt="stuff picture" id="output">
                </c:when>
                <c:otherwise>
                    <img src="<c:url value="/resources/img/no-image-available.png" />"
                         class="rounded mx-auto mb-3 d-block"
                         style="max-width: 200px" alt="no picture" id="output">
                </c:otherwise>
            </c:choose>
        </section>
        <section about="stuff form">
            <form:form id="stuffForm" method="POST" action="/stuff/save" commandName="stuffDto"
                       enctype="multipart/form-data">
                <div>
                    <form:hidden path="id"/>
                </div>

                <div class="input-group mb-3">
                    <span id="pictureErrors" class="text-error"></span>
                    <div class="input-group-prepend">
                        <span class="input-group-text">Image</span>
                    </div>
                    <div class="custom-file">
                        <input type="file" class="custom-file-input" name="picture" id="picture"
                               accept="image/*">
                        <label id="imgName" class="custom-file-label" for="picture">Choose image
                            file</label>
                    </div>
                    <div class="input-group-append">
                        <button id="resetImg" class="btn btn-outline-secondary" type="button">Reset
                            image
                        </button>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="nameLabel">Name</span>
                    </div>
                    <form:input path="name" cssClass="form-control"/>
                    <form:errors path="name" cssClass="text-error"/>
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="brandLabel">Brand</span>
                    </div>
                    <form:input path="brand" cssClass="form-control"/>
                    <form:errors path="brand" cssClass="text-error"/>
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="costLabel">Costs</span>
                    </div>
                    <form:input path="cost" cssClass="form-control" type="number" step="0.01"/>
                    <form:errors path="cost" cssClass="text-error"/>
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="descriptionLabel">Notes</span>
                    </div>
                    <form:textarea path="description" cssClass="form-control"/>
                    <form:errors path="description" cssClass="text-error"/>
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text"
                              id="commissionDateLabel">Commission Date</span>
                    </div>
                    <form:input type="date" path="commissionDate" value="${stuffDto.commissionDate}"
                                cssClass="form-control"/>
                    <form:errors path="commissionDate" cssClass="text-error"/>
                </div>
                <div class="float-right">
                    <button class="btn btn-info" type="submit">Save stuff</button>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form:form>
        </section>
    </article>
</main>

<jsp:include page="../commons/footer.jsp"/>
<jsp:include page="../commons/js.jsp"/>
<script src="<c:url value="/resources/js/validateImgSize.js" />"></script>

</body>
</html>
