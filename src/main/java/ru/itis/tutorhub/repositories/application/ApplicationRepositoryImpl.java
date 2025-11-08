package ru.itis.tutorhub.repositories.application;

import ru.itis.tutorhub.models.Application;
import ru.itis.tutorhub.models.enums.ApplicationStatus;
import ru.itis.tutorhub.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ApplicationRepositoryImpl implements ApplicationRepository {

    public ApplicationRepositoryImpl() {
        initializeTable();
    }

    private void initializeTable() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(ApplicationQueries.CREATE_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize applications table", e);
        }
    }

    @Override
    public Application save(Application application) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ApplicationQueries.SAVE)) {

            statement.setObject(1, application.getId());
            statement.setObject(2, application.getTutorProfileId());
            statement.setObject(3, application.getStudentId());
            statement.setObject(4, application.getSubjectId());
            statement.setObject(5, application.getGoalId());
            statement.setInt(6, application.getFixedPrice());
            statement.setString(7, application.getMessage());
            statement.setString(8, application.getStatus().name());
            statement.setObject(9, application.getCreatedAT());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating application failed, no rows affected.");
            }

            return application;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save application", e);
        }
    }

    @Override
    public Optional<Application> findById(UUID id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ApplicationQueries.FIND_BY_ID)) {

            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Application application = ApplicationMapper.resultSetToApplication(resultSet);
                resultSet.close();
                return Optional.of(application);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find application by id", e);
        }
    }

    @Override
    public void updateStatus(UUID applicationId, ApplicationStatus status) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ApplicationQueries.UPDATE_STATUS)) {

            statement.setString(1, status.name());
            statement.setObject(2, applicationId);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating application status failed, application not found.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update application status", e);
        }
    }

    @Override
    public void deleteById(UUID id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ApplicationQueries.DELETE_BY_ID)) {

            statement.setObject(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting application failed, application not found.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete application", e);
        }
    }

    @Override
    public List<Application> findByStudentId(UUID studentId) {
        List<Application> applications = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ApplicationQueries.FIND_BY_STUDENT_ID)) {

            statement.setObject(1, studentId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Application application = ApplicationMapper.resultSetToApplication(resultSet);
                applications.add(application);
            }
            resultSet.close();
            return applications;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find applications by student id", e);
        }
    }

    @Override
    public List<Application> findByTutorId(UUID tutorId) {
        List<Application> applications = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ApplicationQueries.FIND_BY_TUTOR_ID)) {

            statement.setObject(1, tutorId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Application application = ApplicationMapper.resultSetToApplication(resultSet);
                applications.add(application);
            }
            resultSet.close();
            return applications;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find applications by tutor id", e);
        }
    }

    @Override
    public List<Application> findByTutorIdAndStatus(UUID tutorId, ApplicationStatus status) {
        List<Application> applications = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ApplicationQueries.FIND_BY_TUTOR_ID_AND_STATUS)) {

            statement.setObject(1, tutorId);
            statement.setString(2, status.name());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Application application = ApplicationMapper.resultSetToApplication(resultSet);
                applications.add(application);
            }
            resultSet.close();
            return applications;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find applications by tutor id and status", e);
        }
    }

    @Override
    public boolean existsPendingByStudentAndTutor(UUID studentId, UUID tutorId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ApplicationQueries.EXISTS_PENDING_BY_STUDENT_AND_TUTOR)) {

            statement.setObject(1, studentId);
            statement.setObject(2, tutorId);
            ResultSet resultSet = statement.executeQuery();

            boolean exists = resultSet.next();
            resultSet.close();
            return exists;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to check pending application existence", e);
        }
    }
}