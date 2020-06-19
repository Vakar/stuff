<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html lang="en">
<head>
    <title>Edit Stuff</title>
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
        <h1 class="display-5">Stuff form</h1>
    </div>
    <%--PAGE HEADER | END--%>

    <%--STUFF IMG | START--%>
    <c:choose>
        <c:when test="${isPictureExists}">
            <div>
                <img src="<c:url value="/stuff/picture/${stuffDto.id}"/>"
                     class="rounded mx-auto mb-3 d-block"
                     style="max-width: 200px" alt="stuff picture" id="output">
            </div>
        </c:when>
        <c:otherwise>
            <div>
                <img src="<c:url value="/resources/img/no-image-available.png" />"
                     class="rounded mx-auto mb-3 d-block"
                     style="max-width: 200px" alt="no picture" id="output">
            </div>
        </c:otherwise>
    </c:choose>
    <%--STUFF IMG | END--%>

    <%--ADD NEW STUFF FORM | START--%>
    <div>
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
                    <input type="file" class="custom-file-input" name="picture" id="picture" accept="image/*">
                    <label id="imgName" class="custom-file-label" for="picture">Choose image file</label>
                </div>
                <div class="input-group-append">
                    <button id="resetImg" class="btn btn-outline-secondary" type="button">Reset image</button>
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
                    <span class="input-group-text" id="commissionDateLabel">Commission Date</span>
                </div>
                <form:input type="date"  path="commissionDate" value="${stuffDto.commissionDate}" cssClass="form-control"/>
                <form:errors path="commissionDate" cssClass="text-error"/>
            </div>
            <div class="float-right">
                <button class="btn btn-info" type="submit">Save stuff</button>
            </div>
        </form:form>
    </div>
    <%--ADD NEW STUFF FORM | END--%>

</div>
<%--CONTAINER | END--%>

<%--INCLUDE FOOTER--%>
<jsp:include page="../commons/footer.jsp"/>

<%--IMPORT JS FILES | START--%>
<jsp:include page="../commons/js.jsp"/>
<script src="<c:url value="/resources/js/validateImgSize.js" />"></script>
<%--IMPORT JS FILES | END--%>

</body>
</html>
