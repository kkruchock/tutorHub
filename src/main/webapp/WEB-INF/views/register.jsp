<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 07.11.2025
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация - TutorHub</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h2>Регистрация</h2>

<c:if test="${not empty error}">
    <div style="color: red;">${error}</div>
</c:if>

<form action="${pageContext.request.contextPath}/register" method="post">
    <div>
        <label>
            Имя:
            <input type="text" name="name" value="${name}" required>
        </label>
    </div>

    <div>
        <label>
            Telegram username:
            <input type="text" name="telegramUsername" value="${telegramUsername}"
                   placeholder="@username" required>
        </label>
    </div>

    <div>
        <label>
            Пароль:
            <input type="password" name="password" required>
        </label>
    </div>

    <button type="submit">Зарегистрироваться</button>
</form>

<p>Уже есть аккаунт? <a href="${pageContext.request.contextPath}/login">Войти</a></p>
</body>
</html>