package ru.itis.tutorhub.repositories.user;

import ru.itis.tutorhub.models.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(UUID id);

    Optional<User> findByTelegramUsername(String telegramUsername);

    void updateTutorStatus(UUID userId, boolean isTutor);
}