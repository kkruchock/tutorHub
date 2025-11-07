package ru.itis.tutorhub.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.tutorhub.exceptions.UserAlreadyExistsException;
import ru.itis.tutorhub.models.Session;
import ru.itis.tutorhub.models.User;
import ru.itis.tutorhub.repositories.user.UserRepositoryImpl;
import ru.itis.tutorhub.services.session.SessionService;
import ru.itis.tutorhub.services.user.UserService;
import ru.itis.tutorhub.services.user.UserServiceImpl;
import ru.itis.tutorhub.utils.AuthorizationHelper;

import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private UserService userService;
    private SessionService sessionService;

    @Override
    public void init(){
        ServletContext context = getServletContext();
        this.userService = (UserService) context.getAttribute("userService");
        this.sessionService = (SessionService) context.getAttribute("sessionService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //форма регистрации
        request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String telegramUsername = request.getParameter("telegramUsername");
        String password = request.getParameter("password");

        //проверяем данные, если нет, регаем заново
        //todo нормальная валидация + нормальные ошибки
        if (name == null || name.trim().isEmpty() ||
                telegramUsername == null || telegramUsername.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {

            request.setAttribute("error", "Все поля обязательны для заполнения");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            return;
        }

        name = name.trim();
        telegramUsername = telegramUsername.trim();
        password = password.trim();

        try {
            User user = userService.register(name, telegramUsername, password);

            AuthorizationHelper.createSessionAndCookie(user, sessionService, response);

            response.sendRedirect(request.getContextPath() + "/profile");

        } catch (UserAlreadyExistsException e) {

            request.setAttribute("error", e.getMessage());
            request.setAttribute("name", name);
            request.setAttribute("telegramUsername", telegramUsername);
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);

        } catch (Exception e) {

            request.setAttribute("error", "Произошла ошибка при регистрации: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
        }
    }
}