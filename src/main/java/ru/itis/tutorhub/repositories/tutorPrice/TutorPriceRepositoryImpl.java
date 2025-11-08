package ru.itis.tutorhub.repositories.tutorPrice;

import ru.itis.tutorhub.models.TutorPrice;
import ru.itis.tutorhub.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TutorPriceRepositoryImpl implements TutorPriceRepository {

    public TutorPriceRepositoryImpl() {
        initializeTable();
    }

    private void initializeTable() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(TutorPriceQueries.CREATE_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize tutor_prices table", e);
        }
    }

    @Override
    public TutorPrice save(TutorPrice tutorPrice) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(TutorPriceQueries.SAVE)) {

            statement.setObject(1, tutorPrice.getId());
            statement.setObject(2, tutorPrice.getTutorId());
            statement.setObject(3, tutorPrice.getSubjectId());
            statement.setObject(4, tutorPrice.getGoalId());
            statement.setInt(5, tutorPrice.getPricePerHour());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating tutor price failed, no rows affected.");
            }

            return tutorPrice;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save tutor price", e);
        }
    }

    @Override
    public List<TutorPrice> findByTutorId(UUID tutorId) {
        List<TutorPrice> prices = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(TutorPriceQueries.FIND_BY_TUTOR_ID)) {

            statement.setObject(1, tutorId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                TutorPrice price = TutorPriceMapper.resultSetToTutorPrice(resultSet);
                prices.add(price);
            }
            resultSet.close();
            return prices;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find tutor prices by tutor id", e);
        }
    }

    @Override
    public List<TutorPrice> findBySubjectAndGoal(UUID subjectId, UUID goalId) {
        List<TutorPrice> prices = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(TutorPriceQueries.FIND_BY_SUBJECT_AND_GOAL)) {

            statement.setObject(1, subjectId);
            statement.setObject(2, goalId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                TutorPrice price = TutorPriceMapper.resultSetToTutorPrice(resultSet);
                prices.add(price);
            }
            resultSet.close();
            return prices;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find tutor prices by subject and goal", e);
        }
    }

    @Override
    public Optional<TutorPrice> findByTutorSubjectGoal(UUID tutorId, UUID subjectId, UUID goalId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(TutorPriceQueries.FIND_BY_TUTOR_SUBJECT_GOAL)) {

            statement.setObject(1, tutorId);
            statement.setObject(2, subjectId);
            statement.setObject(3, goalId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                TutorPrice price = TutorPriceMapper.resultSetToTutorPrice(resultSet);
                resultSet.close();
                return Optional.of(price);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find tutor price by tutor, subject and goal", e);
        }
    }

    @Override
    public void deleteById(UUID id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(TutorPriceQueries.DELETE_BY_ID)) {

            statement.setObject(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting tutor price failed, price not found.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete tutor price", e);
        }
    }

    @Override
    public void deleteByTutorId(UUID tutorId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(TutorPriceQueries.DELETE_BY_TUTOR_ID)) {

            statement.setObject(1, tutorId);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete tutor prices by tutor id", e);
        }
    }
}