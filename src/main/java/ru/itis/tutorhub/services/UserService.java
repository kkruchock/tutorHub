package ru.itis.tutorhub.services;

import ru.itis.tutorhub.models.User;

public interface UserService {

    User register(String name, String telegramUsername, String password);

    User login(String telegramUsername, String password);
}