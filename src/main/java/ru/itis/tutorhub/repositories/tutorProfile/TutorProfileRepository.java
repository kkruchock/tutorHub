package ru.itis.tutorhub.repositories.tutorProfile;

import ru.itis.tutorhub.models.TutorProfile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TutorProfileRepository {

    TutorProfile save(TutorProfile profile);

    Optional<TutorProfile> findById(UUID id);

    List<TutorProfile> findAll();

    void deleteById(UUID id);

    Optional<TutorProfile> findByUserId(UUID userId); //для просмотра своей

    List<TutorProfile> findBySubjectAndGoal(UUID subjectId, UUID goalId); // для поиска
}