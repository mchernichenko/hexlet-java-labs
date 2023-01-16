<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Map" %>

<!-- BEGIN -->
<html>
<head>
    <title>Данные пользователя</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
          crossorigin="anonymous">
</head>
<body>
<h3>User info</h3>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>id</th>
        <th>firstName</th>
        <th>lastName</th>
        <th>email</th>
    </tr>
    <tbody>
        <tr>
            <td>${user.get("id")}</td>
            <td>${user.get("firstName")}</td>
            <td>${user.get("lastName")}</td>
            <td>${user.get("email")}</td>
        </tr>
    </tbody>
</table>
</body>
</br>
<a href="/users/delete?id=${user.get("id")}" class="btn btn-danger">Удалить</a>

<%--<form action="/users/delete" method="get">
    <button type="submit" name="id" value="${user.get("id")}">Удалить</button>
</form>--%>
</html>
<!-- END -->
