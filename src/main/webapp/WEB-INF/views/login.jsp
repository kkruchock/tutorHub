<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 07.11.2025
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход - TutorHub</title>
</head>
<body>
<h2>Вход</h2>

<c:if test="${not empty success}">
    <div style="color: green;">${success}</div>
</c:if>

<form action="${pageContext.request.contextPath}/login" method="post">
    <input type="text" name="telegramUsername" placeholder="@username" required>
    <input type="password" name="password" placeholder="Пароль" required>
    <button type="submit">Войти</button>
</form>

<p>Нет аккаунта? <a href="${pageContext.request.contextPath}/register">Зарегистрироваться</a></p>
</body>
</html>