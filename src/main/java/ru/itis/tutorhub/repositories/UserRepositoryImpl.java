package ru.itis.tutorhub.repositories;

import ru.itis.tutorhub.models.User;
import ru.itis.tutorhub.utils.DatabaseConnection;

import java.sql.*;
import java.util.Optional;
import java.util.UUID;

import static ru.itis.tutorhub.repositories.UserMapper.resultSetToUser;

public class UserRepositoryImpl implements UserRepository{

    public UserRepositoryImpl() {
        initializeTable();
    }

    private void initializeTable() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(UserQueries.CREATE_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException("failed to initialize users table", e);
        }
    }

    @Override
    public User save(User user) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UserQueries.SAVE)) {

            statement.setObject(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getTelegramUsername());
            statement.setString(4, user.getPassword());
            statement.setBoolean(5, user.isTutor());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("failed to creating user");
            }

            return user;

        } catch (SQLException e) {
            throw new RuntimeException("failed to save user", e);
        }
    }

    @Override
    public Optional<User> findById(UUID id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UserQueries.FIND_BY_ID)) {

            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = resultSetToUser(resultSet);
                resultSet.close();
                return Optional.of(user);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("failed to find user by id", e);
        }
    }


    @Override
    public Optional<User> findByTelegramUsername(String telegramUsername) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UserQueries.FIND_BY_TELEGRAM_USERNAME)) {

            statement.setString(1, telegramUsername);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = resultSetToUser(resultSet);
                resultSet.close();
                return Optional.of(user);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("failed to find user by telegram username", e);
        }
    }

    @Override
    public void updateTutorStatus(UUID userId, boolean isTutor) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UserQueries.UPDATE_TUTOR_STATUS)) {

            statement.setBoolean(1, isTutor);
            statement.setObject(2, userId);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("failed to update tutor status, user not found.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("failed to update tutor status", e);
        }
    }
}