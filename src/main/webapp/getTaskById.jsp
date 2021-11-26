<%--
  Created by IntelliJ IDEA.
  User: prudy
  Date: 24.11.2021
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task by id</title>
</head>
<body>
    <p>${task.title}</p>
    <p>${task.description}</p>
<form action="AllTaskServlet" method="post">
    <input type="Submit" value="back"/>
</form>
</body>
</html>
