<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 07.11.2025
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Профиль - TutorHub</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1>👤 Ваш профиль</h1>
<h2>Добро пожаловать, ${user.name}!</h2>
<p>Telegram: ${user.telegramUsername}</p>



<p style="margin-top: 30px;">
    <a href="${pageContext.request.contextPath}/">На главную</a>
</p>
</body>
</html>