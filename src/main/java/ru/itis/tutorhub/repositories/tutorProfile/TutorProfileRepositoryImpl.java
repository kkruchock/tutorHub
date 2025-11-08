package ru.itis.tutorhub.repositories.tutorProfile;

import ru.itis.tutorhub.models.TutorProfile;
import ru.itis.tutorhub.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TutorProfileRepositoryImpl implements TutorProfileRepository {

    public TutorProfileRepositoryImpl() {
        initializeTable();
    }

    private void initializeTable() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(TutorProfileQueries.CREATE_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize tutor_profiles table", e);
        }
    }

    @Override
    public TutorProfile save(TutorProfile profile) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(TutorProfileQueries.SAVE)) {

            statement.setObject(1, profile.getId());
            statement.setObject(2, profile.getUserId());
            statement.setString(3, profile.getName());
            statement.setString(4, profile.getDescription());
            statement.setString(5, profile.getEducation());
            statement.setString(6, profile.getImagePath());
            statement.setDouble(7, profile.getAverageRating());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating tutor profile failed, no rows affected.");
            }

            return profile;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save tutor profile", e);
        }
    }

    @Override
    public Optional<TutorProfile> findById(UUID id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(TutorProfileQueries.FIND_BY_ID)) {

            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                TutorProfile profile = TutorProfileMapper.resultSetToTutorProfile(resultSet);
                resultSet.close();
                return Optional.of(profile);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find tutor profile by id", e);
        }
    }

    @Override
    public List<TutorProfile> findAll() {
        List<TutorProfile> profiles = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(TutorProfileQueries.FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                TutorProfile profile = TutorProfileMapper.resultSetToTutorProfile(resultSet);
                profiles.add(profile);
            }
            return profiles;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find all tutor profiles", e);
        }
    }

    @Override
    public void deleteById(UUID id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(TutorProfileQueries.DELETE_BY_ID)) {

            statement.setObject(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting tutor profile failed, profile not found.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete tutor profile", e);
        }
    }

    @Override
    public Optional<TutorProfile> findByUserId(UUID userId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(TutorProfileQueries.FIND_BY_USER_ID)) {

            statement.setObject(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                TutorProfile profile = TutorProfileMapper.resultSetToTutorProfile(resultSet);
                resultSet.close();
                return Optional.of(profile);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find tutor profile by user id", e);
        }
    }

    @Override
    public List<TutorProfile> findBySubjectAndGoal(UUID subjectId, UUID goalId) {
        List<TutorProfile> profiles = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(TutorProfileQueries.FIND_BY_SUBJECT_AND_GOAL)) {

            statement.setObject(1, subjectId);
            statement.setObject(2, goalId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                TutorProfile profile = TutorProfileMapper.resultSetToTutorProfile(resultSet);
                profiles.add(profile);
            }
            resultSet.close();
            return profiles;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find tutor profiles by subject and goal", e);
        }
    }
}