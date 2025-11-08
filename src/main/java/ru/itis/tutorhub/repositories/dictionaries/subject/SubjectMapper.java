package ru.itis.tutorhub.repositories.dictionaries.subject;

import ru.itis.tutorhub.models.dictionaries.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SubjectMapper {

    public static Subject resultSetToSubject(ResultSet resultSet) throws SQLException {
        return new Subject(
                (UUID) resultSet.getObject("id"),
                resultSet.getString("name")
        );
    }
}