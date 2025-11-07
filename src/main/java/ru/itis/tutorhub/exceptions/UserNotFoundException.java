package ru.itis.tutorhub.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String telegramUsername) {
        super("Пользователь @" + telegramUsername + " не найден");
    }
}