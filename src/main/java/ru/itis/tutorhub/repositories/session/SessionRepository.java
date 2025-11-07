package ru.itis.tutorhub.repositories.session;

import ru.itis.tutorhub.models.Session;
import java.util.Optional;
import java.util.UUID;

public interface SessionRepository {

    Session save(Session session);

    Optional<Session> findByToken(String token);

    void deleteByToken(String token);

    void deleteExpiredSessions();

    void  deleteByUserId(UUID userId);
}