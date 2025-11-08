package ru.itis.tutorhub.repositories.review;

import ru.itis.tutorhub.models.Review;
import ru.itis.tutorhub.models.enums.Rating;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class ReviewMapper {

    public static Review resultSetToReview(ResultSet resultSet) throws SQLException {
        return new Review(
                (UUID) resultSet.getObject("id"),
                (UUID) resultSet.getObject("application_id"),
                Rating.fromValue(resultSet.getInt("rating")),
                resultSet.getString("comment"),
                resultSet.getObject("created_at", LocalDateTime.class)
        );
    }
}