<%--
  Created by IntelliJ IDEA.
  User: Віталій
  Date: 16.03.2019
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Show All Users</title>
</head>
<body>
<table border=1>
    <thead>
    <tr>
        <th>User Id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>DOB</th>
        <th>Email</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}" /></td>
            <td><c:out value="${user.firstName}" /></td>
            <td><c:out value="${user.lastName}" /></td>
            <td><fmt:formatDate pattern="yyyy-MMM-dd" value="${user.date}" /></td>
            <td><c:out value="${user.email}" /></td>
            <td><a href="user?action=edit&userId=<c:out value="${user.id}"/>">Update</a></td>
            <td><a href="user?action=delete&userId=<c:out value="${user.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="/user?action=insert">Добавить юзера</a></p>
</body>
</html>

