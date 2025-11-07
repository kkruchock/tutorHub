package ru.itis.tutorhub.repositories.user;

import ru.itis.tutorhub.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserMapper {

    public static User resultSetToUser(ResultSet resultSet) throws SQLException {
        return new User(
                (UUID) resultSet.getObject("id"),
                resultSet.getString("name"),
                resultSet.getString("telegram_username"),
                resultSet.getString("password"),
                resultSet.getBoolean("is_tutor")
        );
    }
}