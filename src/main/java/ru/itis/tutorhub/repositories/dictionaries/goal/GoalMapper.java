package ru.itis.tutorhub.repositories.dictionaries.goal;

import ru.itis.tutorhub.models.dictionaries.Goal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class GoalMapper {

    public static Goal resultSetToGoal(ResultSet resultSet) throws SQLException {
        return new Goal(
                (UUID) resultSet.getObject("id"),
                resultSet.getString("name")
        );
    }
}