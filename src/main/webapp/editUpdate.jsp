<%--
  Created by IntelliJ IDEA.
  User: prudy
  Date: 24.11.2021
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EditTask</title>
</head>
<body>
<form action="UpdateTaskServlet" method="post">
    <input type="text" name="title" value="${task.title}"/>
    <input type="text" name="description" value="${task.description}"/>
    <input type="hidden" name="taskId" value="${task.id}"/>
    <input type="submit" name="action" value="Save"/>
</form>
<form action="AllTaskServlet" method="post">
    <input type="Submit" value="back"/>
</form>
</body>
</html>
