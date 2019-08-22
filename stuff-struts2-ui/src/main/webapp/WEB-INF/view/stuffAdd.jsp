<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Add New Stuff</title>
</head>
<body>

<s:form action="stuffSave" method="post">
    <s:textfield label="Name" name="name" />
    <s:textfield label="Cost" name="cost" />
    <s:submit cssClass="button-register" value="Save stuff" />
</s:form>

</body>
</html>
