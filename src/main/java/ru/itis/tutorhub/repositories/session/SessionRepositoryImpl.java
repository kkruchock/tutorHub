package ru.itis.tutorhub.repositories.session;

import ru.itis.tutorhub.models.Session;
import ru.itis.tutorhub.utils.DatabaseConnection;

import java.sql.*;
import java.util.Optional;
import java.util.UUID;

public class SessionRepositoryImpl implements SessionRepository {

    public SessionRepositoryImpl() {
        initializeTable();
    }

    private void initializeTable() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(SessionQueries.CREATE_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize sessions table", e);
        }
    }

    @Override
    public Session save(Session session) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SessionQueries.SAVE)) {

            statement.setObject(1, session.getId());
            statement.setObject(2, session.getUserId());
            statement.setString(3, session.getSessionToken());
            statement.setObject(4, session.getExpireAt());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating session failed, no rows affected.");
            }

            return session;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save session", e);
        }
    }

    @Override
    public Optional<Session> findByToken(String token) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SessionQueries.FIND_BY_TOKEN)) {

            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Session session = SessionMapper.resultSetToSession(resultSet);
                resultSet.close();
                return Optional.of(session);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find session by token", e);
        }
    }

    @Override
    public void deleteByToken(String token) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SessionQueries.DELETE_BY_TOKEN)) {

            statement.setString(1, token);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete session by token", e);
        }
    }

    @Override
    public void deleteExpiredSessions() {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SessionQueries.DELETE_EXPIRED)) {

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete expired sessions", e);
        }
    }

    @Override
    public void deleteByUserId(UUID userId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SessionQueries.DELETE_BY_USER)) {

            statement.setObject(1, userId);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete sessions by user id", e);
        }
    }
}