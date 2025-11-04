package ru.itis.tutorhub.models;

import java.util.UUID;

public class User {

    private final UUID id;
    private String name;
    private String telegramUsername;
    private String password;
    private boolean isTutor;

    //для регистрации
    public User(
            String name,
            String telegramUsername,
            String password
    ) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.telegramUsername = telegramUsername;
        this.password = password;
        this.isTutor = false;
    }

    //для получения с БД
    public User(
            UUID id,
            String name,
            String telegramUsername,
            String password,
            boolean isTutor
    ) {
        this.id = id;
        this.name = name;
        this.telegramUsername = telegramUsername;
        this.password = password;
        this.isTutor = isTutor;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelegramUsername() {
        return telegramUsername;
    }

    public void setTelegramUsername(String telegramUsername) {
        this.telegramUsername = telegramUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isTutor() {
        return isTutor;
    }

    public void setTutor(boolean tutor) {
        isTutor = tutor;
    }
}