<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add new user</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <a href="/users">Все пользователи</a>
            <!-- BEGIN -->
            <br></br>
            <p><b><span style="color: #ff6600;">${errorMassage}</span></b></p> <%-- Вывод ошибки, если не заполнены обязательные поля --%>
            <form action="/users/new" method="post">
                <div class="mb-3">
                    <label>First Name*</label>
                    <input class="form-control" type="text" name="firstName" value="${user.get("firstName")}">
                </div>
                <div class="mb-3">
                    <label>Last Name*</label>
                    <input class="form-control" type="text" name="lastName" value="${user.get("lastName")}">
                </div>
                <div class="form-group">
                    <label>Email address</label>
                    <input type="email" class="form-control" name="email" placeholder="name@example.com" value="${user.get("email")}">
                </div>
                </br>
                <button class="btn btn-primary" type="submit">Создать</button>
            </form>
            <!-- END -->
        </div>
    </body>
</html>
