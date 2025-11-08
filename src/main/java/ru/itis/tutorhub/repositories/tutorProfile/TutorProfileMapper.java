package ru.itis.tutorhub.repositories.tutorProfile;

import ru.itis.tutorhub.models.TutorProfile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TutorProfileMapper {

    public static TutorProfile resultSetToTutorProfile(ResultSet resultSet) throws SQLException {
        return new TutorProfile(
                (UUID) resultSet.getObject("id"),
                (UUID) resultSet.getObject("user_id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getString("education"),
                resultSet.getString("image_path"),
                resultSet.getDouble("avg_rating")
        );
    }
}