<%--
  Created by IntelliJ IDEA.
  User: prudy
  Date: 19.11.2021
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form action="LoginServlet" method="post" >
    <table>
        <tr><td>User Name: </td><td><input type="text" name="login" value="${NameJsp}"></td></tr>
        <tr><td>Password: </td><td><input type="password" name="password"></td></tr>
        <tr><td></td><td><input type="submit" value="login"></td></tr>
    </table>
</form>
<p style=" color: red">
    ${errorMessage}
</p>
</body>
</html>
