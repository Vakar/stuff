<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Edit Stuff</title>
</head>
<body>

<s:form action="stuffUpdate" method="post">
    <s:hidden name="id" value="%{id}"/>
    <s:textfield label="Name" name="name" />
    <s:textfield label="Cost" name="cost" />
    <s:submit cssClass="button-register" value="Save stuff" />
</s:form>

</body>
</html>
