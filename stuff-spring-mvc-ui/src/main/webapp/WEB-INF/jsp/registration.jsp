<%@taglib uri="http://www.springframework.org/tags/form" prefix = "form"%>
<html>
<head>
    <title>Registration</title>
</head>

<body>
<h2>User Registration</h2>
<form:form method = "POST" action = "/registerUser" commandName = "registration">
    <table>
        <tr>
            <td><form:label path = "userName">User Name</form:label></td>
            <td><form:input path = "userName" /></td>
            <td><form:errors path = "userName"/></td>
        </tr>
        <tr>
            <td><form:label path = "eMail">E-mail</form:label></td>
            <td><form:input path = "eMail" /></td>
            <td><form:errors path = "eMail"/></td>
        </tr>
        <tr>
            <td><form:label path = "password">Password</form:label></td>
            <td><form:input path = "password"/></td>
            <td><form:errors path = "password"/></td>
        </tr>
        <tr>
            <td colspan = "2">
                <input type = "submit" value = "Submit"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>