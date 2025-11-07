package ru.itis.tutorhub.models.dictionaries;

import java.util.UUID;

public class Subject {

    private final UUID id;
    private String name;

    //только получение с БД (справочная таблица)
    public Subject(UUID id, String name) {
        this.id = id;
        this.name = name;
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
}