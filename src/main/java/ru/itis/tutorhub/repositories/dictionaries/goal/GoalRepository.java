package ru.itis.tutorhub.repositories.dictionaries.goal;

import ru.itis.tutorhub.models.dictionaries.Goal;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GoalRepository {

    List<Goal> findAll();

    Optional<Goal> findById(UUID id);

    Optional<Goal> findByName(String name);
}