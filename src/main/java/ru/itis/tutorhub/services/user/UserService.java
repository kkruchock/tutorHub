package ru.itis.tutorhub.services.user;

import ru.itis.tutorhub.models.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    User register(String name, String telegramUsername, String password);

    User login(String telegramUsername, String password);

    Optional<User> findById(UUID id);
}