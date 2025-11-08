package ru.itis.tutorhub.repositories.dictionaries.goal;

import ru.itis.tutorhub.models.dictionaries.Goal;
import ru.itis.tutorhub.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GoalRepositoryImpl implements GoalRepository {

    public GoalRepositoryImpl() {
        initializeTable();
    }

    private void initializeTable() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(GoalQueries.CREATE_TABLE);
            statement.executeUpdate(GoalQueries.INSERT_DEFAULT_DATA);

        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize goals table", e);
        }
    }

    @Override
    public List<Goal> findAll() {
        List<Goal> goals = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(GoalQueries.FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Goal goal = GoalMapper.resultSetToGoal(resultSet);
                goals.add(goal);
            }
            return goals;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find all goals", e);
        }
    }

    @Override
    public Optional<Goal> findById(UUID id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(GoalQueries.FIND_BY_ID)) {

            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Goal goal = GoalMapper.resultSetToGoal(resultSet);
                resultSet.close();
                return Optional.of(goal);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find goal by id", e);
        }
    }

    @Override
    public Optional<Goal> findByName(String name) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(GoalQueries.FIND_BY_NAME)) {

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Goal goal = GoalMapper.resultSetToGoal(resultSet);
                resultSet.close();
                return Optional.of(goal);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find goal by name", e);
        }
    }
}