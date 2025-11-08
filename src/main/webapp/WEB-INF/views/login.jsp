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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h2>Вход</h2>

<c:if test="${not empty error}">
    <div style="color: red;">${error}</div>
</c:if>

<form action="${pageContext.request.contextPath}/login" method="post">
    <div>
        <label>
            Telegram ник:
            <input type="text" name="telegramUsername" placeholder="@username" required>
        </label>
    </div>

    <div>
        <label>
            Пароль:
            <input type="password" name="password" required>
        </label>
    </div>
    <button type="submit">Войти</button>
</form>

<p>Нет аккаунта? <a href="${pageContext.request.contextPath}/register">Зарегистрироваться</a></p>
</body>
</html>