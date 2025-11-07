package ru.itis.tutorhub.services.session;

import ru.itis.tutorhub.models.Session;
import ru.itis.tutorhub.repositories.session.SessionRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class SessionServiceImpl implements SessionService{

    private final SessionRepository sessionRepository;

    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Session createSession(UUID userId) {
        return sessionRepository.save(new Session(userId, LocalDateTime.now().plusMinutes(30)));
    }

    @Override
    public Optional<Session> validateSession(String token) {
        return sessionRepository.findByToken(token)
                .filter(session -> !session.getExpireAt().isBefore(LocalDateTime.now()));
    }

    @Override
    public void deleteByToken(String token) {
        sessionRepository.deleteByToken(token);
    }
}