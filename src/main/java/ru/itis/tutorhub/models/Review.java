package ru.itis.tutorhub.models;

import ru.itis.tutorhub.models.enums.Rating;

import java.time.LocalDateTime;
import java.util.UUID;

public class Review {

    private final UUID id;
    private UUID applicationId;
    private Rating rating;
    private String comment;
    private LocalDateTime createdAt;

    //для создания
    public Review(
            UUID applicationId,
            Rating rating,
            String comment
    ) {
        this.id = UUID.randomUUID();
        this.applicationId = applicationId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = LocalDateTime.now();
    }

    //для получения с БД
    public Review(
            UUID id,
            UUID applicationId,
            Rating rating,
            String comment,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.applicationId = applicationId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(UUID applicationId) {
        this.applicationId = applicationId;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}