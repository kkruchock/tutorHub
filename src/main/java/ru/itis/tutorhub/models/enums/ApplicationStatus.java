package ru.itis.tutorhub.models.enums;

public enum ApplicationStatus {

    PENDING("ОТПРАВЛЕНА"),
    ACCEPTED("ПРИНЯТА"),
    REJECTED("ОТКЛОНЕНА"),
    CANCELLED("ОТМЕНЕНА");

    private final String tittle;

    ApplicationStatus(String tittle) {
        this.tittle = tittle;
    }

    public String getTittle() {
        return tittle;
    }
}