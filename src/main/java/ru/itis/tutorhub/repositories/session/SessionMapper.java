package ru.itis.tutorhub.repositories.session;

import ru.itis.tutorhub.models.Session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class SessionMapper {

    public static Session resultSetToSession(ResultSet resultSet) throws SQLException {
        return new Session(
                (UUID) resultSet.getObject("id"),
                (UUID) resultSet.getObject("user_id"),
                resultSet.getString("session_token"),
                resultSet.getObject("expires_at", LocalDateTime.class)
        );
    }
}
