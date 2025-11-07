package ru.itis.tutorhub.services.auth;

import ru.itis.tutorhub.models.User;

import java.util.Optional;

public interface AuthService {

    Optional<User> getUserBySession(String sessionToken);

    void logout(String sessionToken);
}