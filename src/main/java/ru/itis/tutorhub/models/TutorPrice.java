package ru.itis.tutorhub.models;

import java.util.UUID;

//модель, содержащая данные репетитор: услуга: стоимость
public class TutorPrice {

    private final UUID id;
    private UUID tutorProfileId;
    private UUID goalId;
    private UUID subjectId;
    private int pricePerHour;

    //для создания
    public TutorPrice(UUID tutorProfileId, UUID goalId, UUID subjectId, int pricePerHour) {
        this.id = UUID.randomUUID();
        this.tutorProfileId = tutorProfileId;
        this.goalId = goalId;
        this.subjectId = subjectId;
        this.pricePerHour = pricePerHour;
    }

    //для получения с БД
    public TutorPrice(UUID id, UUID tutorProfileId, UUID goalId, UUID subjectId, int pricePerHour) {
        this.id = id;
        this.tutorProfileId = tutorProfileId;
        this.goalId = goalId;
        this.subjectId = subjectId;
        this.pricePerHour = pricePerHour;
    }

    public UUID getId() {
        return id;
    }

    public UUID getTutorId() {
        return tutorProfileId;
    }

    public void setTutorId(UUID tutorProfileId) {
        this.tutorProfileId = tutorProfileId;
    }

    public UUID getGoalId() {
        return goalId;
    }

    public void setGoalId(UUID goalId) {
        this.goalId = goalId;
    }

    public UUID getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(UUID subjectId) {
        this.subjectId = subjectId;
    }

    public int getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(int pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
}