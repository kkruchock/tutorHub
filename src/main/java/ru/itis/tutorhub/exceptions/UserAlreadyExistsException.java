package ru.itis.tutorhub.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String telegramUsername) {
        super("Пользователь с Telegram @" + telegramUsername + " уже существует");
    }
}