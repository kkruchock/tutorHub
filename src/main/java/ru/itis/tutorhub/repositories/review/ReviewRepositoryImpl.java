package ru.itis.tutorhub.repositories.review;

import ru.itis.tutorhub.models.Review;
import ru.itis.tutorhub.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ReviewRepositoryImpl implements ReviewRepository {

    public ReviewRepositoryImpl() {
        initializeTable();
    }

    private void initializeTable() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(ReviewQueries.CREATE_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize reviews table", e);
        }
    }

    @Override
    public Review save(Review review) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ReviewQueries.SAVE)) {

            statement.setObject(1, review.getId());
            statement.setObject(2, review.getApplicationId());
            statement.setInt(3, review.getRating().getValue());
            statement.setString(4, review.getComment());
            statement.setObject(5, review.getCreatedAt());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating review failed, no rows affected.");
            }

            return review;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save review", e);
        }
    }

    @Override
    public List<Review> findByTutorId(UUID tutorId) {
        List<Review> reviews = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ReviewQueries.FIND_BY_TUTOR_ID)) {

            statement.setObject(1, tutorId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Review review = ReviewMapper.resultSetToReview(resultSet);
                reviews.add(review);
            }
            resultSet.close();
            return reviews;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find reviews by tutor id", e);
        }
    }

    @Override
    public Optional<Review> findByApplicationId(UUID applicationId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ReviewQueries.FIND_BY_APPLICATION_ID)) {

            statement.setObject(1, applicationId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Review review = ReviewMapper.resultSetToReview(resultSet);
                resultSet.close();
                return Optional.of(review);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find review by application id", e);
        }
    }

    @Override
    public void updateTutorRating(UUID tutorId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ReviewQueries.UPDATE_TUTOR_RATING)) {

            statement.setObject(1, tutorId);
            statement.setObject(2, tutorId);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update tutor rating", e);
        }
    }
}