package ru.itis.tutorhub.models;

import ru.itis.tutorhub.models.enums.ApplicationStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class Application {

    private final UUID id;
    private UUID tutorProfileId;
    private UUID studentId; //fk на userId
    private UUID subjectId;
    private UUID goalId;
    private int fixedPrice; //цена на момент создания, не должна зависеть от изменений в анкете репетитора
    private String message;
    private ApplicationStatus status;
    private LocalDateTime createdAT;

    //для создания
    public Application(
            UUID tutorProfileId,
            UUID studentId,
            UUID subjectId,
            UUID goalId,
            int fixedPrice,
            String message
    ) {
        this.id = UUID.randomUUID();
        this.tutorProfileId = tutorProfileId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.goalId = goalId;
        this.fixedPrice = fixedPrice;
        this.message = message;
        this.status = ApplicationStatus.PENDING;
        this.createdAT = LocalDateTime.now();
    }

    //для получения с БД
    public Application(
            UUID id,
            UUID tutorProfileId,
            UUID studentId,
            UUID subjectId,
            UUID goalId,
            int fixedPrice,
            String message,
            ApplicationStatus status,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.tutorProfileId = tutorProfileId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.goalId = goalId;
        this.fixedPrice = fixedPrice;
        this.message = message;
        this.status = status;
        this.createdAT = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getTutorProfileId() {
        return tutorProfileId;
    }

    public void setTutorProfileId(UUID tutorProfileId) {
        this.tutorProfileId = tutorProfileId;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public UUID getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(UUID subjectId) {
        this.subjectId = subjectId;
    }

    public UUID getGoalId() {
        return goalId;
    }

    public void setGoalId(UUID goalId) {
        this.goalId = goalId;
    }

    public int getFixedPrice() {
        return fixedPrice;
    }

    public void setFixedPrice(int fixedPrice) {
        this.fixedPrice = fixedPrice;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAT() {
        return createdAT;
    }

    public void setCreatedAT(LocalDateTime createdAT) {
        this.createdAT = createdAT;
    }
}