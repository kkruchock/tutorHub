package ru.itis.tutorhub.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.tutorhub.exceptions.InvalidPasswordException;
import ru.itis.tutorhub.exceptions.UserNotFoundException;
import ru.itis.tutorhub.models.Session;
import ru.itis.tutorhub.models.User;
import ru.itis.tutorhub.services.auth.AuthService;
import ru.itis.tutorhub.services.session.SessionService;
import ru.itis.tutorhub.services.user.UserService;
import ru.itis.tutorhub.utils.AuthorizationHelper;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private AuthService authService;
    private UserService userService;
    private SessionService sessionService;

    @Override
    public void init() {
        ServletContext context = getServletContext();
        this.authService = (AuthService) context.getAttribute("authService");
        this.userService = (UserService) context.getAttribute("userService");
        this.sessionService = (SessionService) context.getAttribute("sessionService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String success = request.getParameter("success");
        if (success != null) {
            request.setAttribute("success", success);
        }

        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String telegramUsername = request.getParameter("telegramUsername");
        String password = request.getParameter("password");

        try {
            User user = userService.login(telegramUsername, password);

            AuthorizationHelper.createSessionAndCookie(user, sessionService, response);

            response.sendRedirect(request.getContextPath() + "/profile");

        } catch (UserNotFoundException e) {

            request.setAttribute("error", "Пользователь с таким Telegram не найден");
            request.setAttribute("telegramUsername", telegramUsername);
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);

        } catch (InvalidPasswordException e) {

            request.setAttribute("error", "Неверный пароль");
            request.setAttribute("telegramUsername", telegramUsername); // Сохраняем username
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);

        } catch (Exception e) {

            request.setAttribute("error", "Произошла ошибка при входе в систему");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }
}
