package ru.itis.tutorhub.repositories.dictionaries.subject;

import ru.itis.tutorhub.models.dictionaries.Subject;
import ru.itis.tutorhub.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SubjectRepositoryImpl implements SubjectRepository {

    public SubjectRepositoryImpl() {
        initializeTable();
    }

    private void initializeTable() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(SubjectQueries.CREATE_TABLE);
            statement.executeUpdate(SubjectQueries.INSERT_DEFAULT_DATA);

        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize subjects table", e);
        }
    }

    @Override
    public List<Subject> findAll() {
        List<Subject> subjects = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SubjectQueries.FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Subject subject = SubjectMapper.resultSetToSubject(resultSet);
                subjects.add(subject);
            }
            return subjects;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find all subjects", e);
        }
    }

    @Override
    public Optional<Subject> findById(UUID id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SubjectQueries.FIND_BY_ID)) {

            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Subject subject = SubjectMapper.resultSetToSubject(resultSet);
                resultSet.close();
                return Optional.of(subject);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find subject by id", e);
        }
    }

    @Override
    public Optional<Subject> findByName(String name) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SubjectQueries.FIND_BY_NAME)) {

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Subject subject = SubjectMapper.resultSetToSubject(resultSet);
                resultSet.close();
                return Optional.of(subject);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find subject by name", e);
        }
    }
}