package ru.itis.tutorhub.models;

import java.util.UUID;

public class TutorProfile {

    private final UUID id;
    private final UUID userId;
    private String name;
    private String description;
    private String education;
    private String imagePath;
    private Double averageRating;

    //для создания анкеты
    public TutorProfile(
            UUID userId,
            String name,
            String description,
            String education,
            String imagePath
    ) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.education = education;
        //изображение по умолчанию
        this.imagePath = imagePath != null ? imagePath : "images/default-avatar.png";
        this.averageRating = 0.0;
    }

    //для получения с БД
    public TutorProfile(
            UUID id,
            UUID userId,
            String name,
            String description,
            String education,
            String imagePath,
            Double averageRating
    ) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.education = education;
        this.imagePath = imagePath;
        this.averageRating = averageRating;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}