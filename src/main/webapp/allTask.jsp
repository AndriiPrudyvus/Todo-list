<%--
  Created by IntelliJ IDEA.
  User: prudy
  Date: 19.11.2021
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AllTask</title>
</head>
<body>
<table>
    <c:if test="${ not empty taskList}">
        <c:forEach items="${taskList}" var="item">
            <tr>
                <td><c:out value="${item.title}"/></td>
                <form action="GetTaskByIdServlet" method="get">
                    <td><input type="submit" value="View" name="action"></td>
                    <input type="hidden" name="taskId" value="${item.id}">
                    <input type="hidden" name="title" value="${item.title}">
                    <input type="hidden" name="description" value="${item.description}">
                </form>
                <form action="EditTaskServlet"  method="post">
                    <td><input type="submit" value="Edit" name="action"></td>
                    <input type="hidden" name="taskId" value="${item.id}">
                    <input type="hidden" name="title" value="${item.title}">
                    <input type="hidden" name="description" value="${item.description}">
                </form>
                <form action="DeleteTaskServlet" method="post">
                    <td><input type="submit" value="Delete" name="action"></td>
                    <input type="hidden" name="taskId" value="${item.id}">
                    <input type="hidden" name="title" value="${item.title}">
                    <input type="hidden" name="description" value="${item.description}">
                </form>
            </tr>
        </c:forEach>
    </c:if>
    <tr>
        <form action="CreateTaskServlet" method="post">
            <td><input type="text" placeholder="Title" name="title"/></td>
            <td><input type="text" placeholder="Description" name="description"/></td>
            <td><input type="submit" name="action" value="Create"></td>
        </form>
    </tr>
</table>
<form action="LogoutServlet" method="post">
    <input type="Submit" value="Logout"/>
</form>
<p style=" color: red">
    ${status}
</p>
</body>
</html>
