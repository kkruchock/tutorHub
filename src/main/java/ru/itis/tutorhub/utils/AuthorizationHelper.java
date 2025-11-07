package ru.itis.tutorhub.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.tutorhub.models.Session;
import ru.itis.tutorhub.models.User;
import ru.itis.tutorhub.services.session.SessionService;

public class AuthorizationHelper {

    public static void createSessionAndCookie(User user, SessionService sessionService, HttpServletResponse response) {

        Session session = sessionService.createSession(user.getId());

        Cookie sessionCookie = new Cookie("SESSION_TOKEN", session.getSessionToken());
        sessionCookie.setMaxAge(30 * 60);
        sessionCookie.setPath("/");
        sessionCookie.setHttpOnly(true);
        response.addCookie(sessionCookie);
    }
}