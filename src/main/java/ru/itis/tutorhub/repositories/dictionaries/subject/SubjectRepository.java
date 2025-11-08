package ru.itis.tutorhub.repositories.dictionaries.subject;

import ru.itis.tutorhub.models.dictionaries.Subject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubjectRepository {

    List<Subject> findAll();

    Optional<Subject> findById(UUID id);

    Optional<Subject> findByName(String name);
}