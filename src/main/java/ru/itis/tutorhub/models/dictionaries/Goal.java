package ru.itis.tutorhub.models.dictionaries;

import java.util.UUID;

//цели подготовки (напр: ОГЭ, ЕГЭ и так далее)
public class Goal {

    private final UUID id;
    private String name;

    //только получение с БД (справочная таблица)
    public Goal(UUID id, String name) {
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