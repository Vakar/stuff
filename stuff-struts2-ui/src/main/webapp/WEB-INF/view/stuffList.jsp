<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>My Stuff</title>
</head>
<body>

<table>
    <thead>
    <tr>
        <th>Stuff Name</th>
        <th>Cost $</th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="list">
        <tr>
            <td><s:property value="name"/></td>
            <td><s:property value="cost"/></td>
            <td>
                <a href="stuffEditPage.action?id=<s:property value="id"/>">
                    <button class="button-update">Edit</button>
                </a>
                <a href="stuffDelete.action?id=<s:property value="id"/>">
                    <button class="button-delete">Delete</button>
                </a>
            </td>
        </tr>
    </s:iterator>
    <tr>
        <td>Total cost: </td>
        <td><s:property value="totalCost"/></td>
        <td></td>
    </tr>
    </tbody>
</table>

<a href="<s:url action='stuffAddPage'/>">Add New Stuff</a>

</body>
</html>
