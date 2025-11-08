<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 07.11.2025
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <nav>
        <div class="logo">
            <a href="${pageContext.request.contextPath}/">TutorHub</a>
        </div>
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/">Главная</a></li>
            <c:choose>
                <c:when test="${not empty user}">
                    <li><a href="${pageContext.request.contextPath}/profile">Профиль</a></li>
                    <li><a href="${pageContext.request.contextPath}/applications">Заявки</a></li>
                    <li><a href="${pageContext.request.contextPath}/logout">Выйти</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageContext.request.contextPath}/login">Войти</a></li>
                    <li><a href="${pageContext.request.contextPath}/register">Регистрация</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
</header>
