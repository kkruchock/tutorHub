package ru.itis.tutorhub.repositories.application;

import ru.itis.tutorhub.models.Application;
import ru.itis.tutorhub.models.enums.ApplicationStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class ApplicationMapper {

    public static Application resultSetToApplication(ResultSet resultSet) throws SQLException {
        return new Application(
                (UUID) resultSet.getObject("id"),
                (UUID) resultSet.getObject("tutor_profile_id"),
                (UUID) resultSet.getObject("student_id"),
                (UUID) resultSet.getObject("subject_id"),
                (UUID) resultSet.getObject("goal_id"),
                resultSet.getInt("fixed_price"),
                resultSet.getString("message"),
                ApplicationStatus.valueOf(resultSet.getString("status")),
                resultSet.getObject("created_at", LocalDateTime.class)
        );
    }
}