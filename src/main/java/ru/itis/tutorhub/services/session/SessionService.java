package ru.itis.tutorhub.services.session;

import ru.itis.tutorhub.models.Session;

import java.util.Optional;
import java.util.UUID;

public interface SessionService {

    Session createSession(UUID userId);

    Optional<Session> validateSession(String token);

    void deleteByToken(String token);
}
