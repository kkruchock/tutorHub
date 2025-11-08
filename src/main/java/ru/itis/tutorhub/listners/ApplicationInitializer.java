package ru.itis.tutorhub.listners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.itis.tutorhub.repositories.session.SessionRepository;
import ru.itis.tutorhub.repositories.session.SessionRepositoryImpl;
import ru.itis.tutorhub.repositories.user.UserRepository;
import ru.itis.tutorhub.repositories.user.UserRepositoryImpl;
import ru.itis.tutorhub.services.auth.AuthService;
import ru.itis.tutorhub.services.auth.AuthServiceImpl;
import ru.itis.tutorhub.services.session.SessionService;
import ru.itis.tutorhub.services.session.SessionServiceImpl;
import ru.itis.tutorhub.services.user.UserService;
import ru.itis.tutorhub.services.user.UserServiceImpl;

@WebListener
public class ApplicationInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        UserRepository userRepository = new UserRepositoryImpl();
        SessionRepository sessionRepository = new SessionRepositoryImpl();

        UserService userService = new UserServiceImpl(userRepository);
        SessionService sessionService = new SessionServiceImpl(sessionRepository);
        AuthService authService = new AuthServiceImpl(sessionService, userService);

        sce.getServletContext().setAttribute("userService", userService);
        sce.getServletContext().setAttribute("sessionService", sessionService);
        sce.getServletContext().setAttribute("authService", authService);
    }
}