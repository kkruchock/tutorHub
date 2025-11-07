package ru.itis.tutorhub.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("Неверный пароль");
    }
}