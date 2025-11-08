package ru.itis.tutorhub.utils;

import ru.itis.tutorhub.models.ValidationResult;

public class ValidationUtils {

    public static ValidationResult validateUserFields(String name, String telegramUsername, String password) {
        ValidationResult result = new ValidationResult();

        if (name == null || name.trim().isEmpty()) {
            result.addError("name", "Имя обязательно для заполнения");
        }

        if (telegramUsername == null || telegramUsername.trim().isEmpty()) {
            result.addError("telegramUsername", "Telegram username обязателен для заполнения");
        }

        if (telegramUsername != null && !(telegramUsername.startsWith("@"))) {
            result.addError("telegramUsername", "Telegram username должен начинаться с @");
        }

        if (password == null || password.trim().isEmpty()) {
            result.addError("password", "Пароль обязателен для заполнения");
        }

        if (password != null && password.trim().length() < 8) {
            result.addError("password", "Пароль должен содержать минимум 8 символов");
        }

        return result;
    }

    public static ValidationResult validateLoginFields(String telegramUsername, String password) {
        ValidationResult result = new ValidationResult();

        if (telegramUsername == null || telegramUsername.trim().isEmpty()) {
            result.addError("telegramUsername", "Telegram username обязателен для заполнения");
        }

        if (telegramUsername != null && !(telegramUsername.startsWith("@"))) {
            result.addError("telegramUsername", "Telegram username должен начинаться с @");
        }

        if (password == null || password.trim().isEmpty()) {
            result.addError("password", "Пароль обязателен для заполнения");
        }

        return result;
    }
}