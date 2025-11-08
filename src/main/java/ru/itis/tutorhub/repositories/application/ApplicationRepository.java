package ru.itis.tutorhub.repositories.application;

import ru.itis.tutorhub.models.Application;
import ru.itis.tutorhub.models.enums.ApplicationStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ApplicationRepository {

    //CRUD
    Application save(Application application);

    Optional<Application> findById(UUID id);

    void updateStatus(UUID applicationId, ApplicationStatus status);

    void deleteById(UUID id);

    //для студента
    List<Application> findByStudentId(UUID studentId);

    //для репетитора
    List<Application> findByTutorId(UUID tutorId);
    List<Application> findByTutorIdAndStatus(UUID tutorId, ApplicationStatus status); // ⭐ НОВЫЙ

    //бизнес логика для отзывов
    boolean existsPendingByStudentAndTutor(UUID studentId, UUID tutorId);
}
