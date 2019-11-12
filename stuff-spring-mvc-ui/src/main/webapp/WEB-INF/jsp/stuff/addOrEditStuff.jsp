<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <form:form method="POST" action="/stuff/save" commandName="stuffDto">
        <div>
            <form:hidden path="id"/>
        </div>
        <div class="form-group">
            <form:label path="name">Name</form:label>
            <form:input path="name"/>
            <form:errors path="name"/>
        </div>
        <div class="form-group">
            <form:label path="cost">Cost</form:label>
            <form:input path="cost"/>
            <form:errors path="cost"/>
        </div>
        <input class="btn btn-primary btn-block" type="submit" value="Submit"/>
    </form:form>
</div>
</body>
</html>
