package ru.itis.tutorhub.repositories.tutorPrice;

import ru.itis.tutorhub.models.TutorPrice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TutorPriceMapper {

    public static TutorPrice resultSetToTutorPrice(ResultSet resultSet) throws SQLException {
        return new TutorPrice(
                (UUID) resultSet.getObject("id"),
                (UUID) resultSet.getObject("tutor_id"),
                (UUID) resultSet.getObject("subject_id"),
                (UUID) resultSet.getObject("goal_id"),
                resultSet.getInt("price_per_hour")
        );
    }
}