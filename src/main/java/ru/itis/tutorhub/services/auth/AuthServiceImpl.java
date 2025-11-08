package ru.itis.tutorhub.services.auth;

import ru.itis.tutorhub.models.Session;
import ru.itis.tutorhub.models.User;
import ru.itis.tutorhub.services.session.SessionService;
import ru.itis.tutorhub.services.user.UserService;

import java.util.Optional;

public class AuthServiceImpl implements AuthService {

    private final SessionService sessionService;
    private final UserService userService;

    public AuthServiceImpl(SessionService sessionService, UserService userService) {
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @Override
    public Optional<User> getUserBySession(String sessionToken) {
        return sessionService.validateSession(sessionToken)
                .map(Session::getUserId)
                .flatMap(userService::findById);
    }

    @Override
    public void logout(String sessionToken) {
        sessionService.deleteByToken(sessionToken);
    }
}