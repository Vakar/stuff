<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
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

    <%--STUFF IMG | START--%>
    <div>
        <img src="<c:url value="/stuff/picture/${stuffDto.id}"/>" class="rounded mx-auto d-block"
             style="max-width: 200px" alt="no picture" id="output">
    </div>
    <%--STUFF IMG | END--%>

    <%--ADD NEW STUFF FORM | START--%>
    <div>
        <form:form method="POST" action="/stuff/stuff/save" commandName="stuffDto"
                   enctype="multipart/form-data">
            <div>
                <form:hidden path="id"/>
            </div>
            <div class="form-group">
                <input type="file" name="picture" onchange="loadFile(event)">
            </div>
            <div class="form-group">
                <form:label path="name">Stuff Name</form:label>
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="text-danger"/>
            </div>
            <div class="form-group">
                <form:label path="brand">Stuff Brand</form:label>
                <form:input path="brand" cssClass="form-control"/>
                <form:errors path="brand" cssClass="text-danger"/>
            </div>
            <div class="form-group">
                <form:label path="description">Stuff Description</form:label>
                <form:textarea path="description" cssClass="form-control"/>
                <form:errors path="description" cssClass="text-danger"/>
            </div>
            <div class="form-group">
                <form:label path="cost">Stuff Cost</form:label>
                <form:input path="cost" cssClass="form-control"/>
                <form:errors path="cost" cssClass="text-danger"/>
            </div>
            <button class="btn btn-primary" type="submit">Submit</button>
        </form:form>
    </div>
    <%--ADD NEW STUFF FORM | END--%>

</div>
<%--CONTAINER | END--%>

<%--INCLUDE FOOTER--%>
<jsp:include page="../commons/footer.jsp"/>

<%--IMPORT JS FILES | START--%>
<jsp:include page="../commons/js.jsp"/>
<script src="<c:url value="/resources/js/showUploadImg.js" />"></script>
<%--IMPORT JS FILES | END--%>

</body>
</html>
