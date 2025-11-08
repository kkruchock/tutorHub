package ru.itis.tutorhub.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Session {

    private final UUID id;
    private UUID userId;
    private String sessionToken;
    private LocalDateTime expireAt;

    //для создания
    public Session(UUID userId, LocalDateTime expireAt) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.sessionToken = UUID.randomUUID().toString();
        this.expireAt = expireAt;
    }

    //для получения с БД
    public Session(UUID id, UUID userId, String sessionToken, LocalDateTime expireAt) {
        this.id = id;
        this.userId = userId;
        this.sessionToken = sessionToken;
        this.expireAt = expireAt;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
    }
}