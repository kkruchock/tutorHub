package ru.itis.tutorhub.repositories.tutorPrice;

import ru.itis.tutorhub.models.TutorPrice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TutorPriceRepository {

    TutorPrice save(TutorPrice tutorPrice);

    List<TutorPrice> findByTutorId(UUID tutorId);

    List<TutorPrice> findBySubjectAndGoal(UUID subjectId, UUID goalId);

    Optional<TutorPrice> findByTutorSubjectGoal(UUID tutorId, UUID subjectId, UUID goalId);

    void deleteById(UUID id);

    void deleteByTutorId(UUID tutorId);
}