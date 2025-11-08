<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 07.11.2025
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>TutorHub - Главная</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@ include file="header.jsp" %>

<main>
    <section class="hero">
        <h1>Найдите идеального репетитора</h1>
        <p>Подготовка к ЕГЭ, ОГЭ, олимпиадам и повышение успеваемости</p>

        <c:choose>
            <c:when test="${not empty user}">
                <a href="${pageContext.request.contextPath}/tutors" class="btn">Найти репетитора</a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/register" class="btn">Начать сейчас</a>
            </c:otherwise>
        </c:choose>
    </section>
</main>

<%@ include file="footer.jsp" %>
</body>
</html>
