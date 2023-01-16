<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>

<!-- BEGIN -->
<html>
<head>
    <%--<style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 5px;
        }
        table#alter tr:nth-child(even) {
            background-color: #eee;
        }
        table#alter tr:nth-child(odd) {
            background-color: #fff;
        }
        table#alter th {
            color: white;
            background-color: gray;
        }
    </style>--%>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <title>Список пользователей</title>
</head>
<body>
<h3>List users</h3>
<%--<table id="alter">--%>
<table class="table table-striped">
    <thead>
    <tr>
        <th>п/п</th>
        <th>id</th>
        <th>firstName</th>
        <th>lastName</th>
        <th>email</th>
    </tr>
    <tbody>
    <%
        List<Map<String,String>> users = (List<Map<String,String>>) request.getAttribute("users");
        for (int i = 0; i < users.size(); i++) {
            request.setAttribute("userId", users.get(i).get("id"));
    %>
        <tr>
            <td><%=i+1%></td>
            <%--столбец с id делаем ссылкой --%>
            <td><a href="<c:url value='users/show?id=${userId}'/>"> ${userId} </a> </td>
           <%-- <td><%=users.get(i).get("id")%></td>--%>
            <td><%=users.get(i).get("firstName")%></td>
            <td><%=users.get(i).get("lastName")%></td>
            <td><%=users.get(i).get("email")%></td>
        </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>
<!-- END -->
