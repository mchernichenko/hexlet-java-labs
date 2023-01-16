<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Удаление пользователя</title>
</head>
<body>
<h3>Удалить пользователя ${user.get("firstName")} ${user.get("lastName")}?</h3>
<form action="/users/delete?id=${user.get("id")}" method="post">
    <button type="submit" class="btn btn-danger">Удалить</button>
</form>
</body>
</html>
<!-- END -->
