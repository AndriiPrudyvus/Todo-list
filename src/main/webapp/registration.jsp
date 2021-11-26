<%--
  Created by IntelliJ IDEA.
  User: prudy
  Date: 19.11.2021
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h2>User Register Form</h2>
<form action="RegistrationServlet" method="post">
    <table>
        <tr>
            <td>User Name:</td>
            <td><input type="text" name="login"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="register"></td>
        </tr>
    </table>

</form>
<form action="login.jsp"  >
    <input type="submit" value="back">
</form>
<p style=" color: red">
    ${status}
</p>
</body>
</html>

