package ru.itis.tutorhub.repositories;

public class UserQueries {

    private UserQueries() {} //запрещаем создание

    public static final String CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS users (
            id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
            name VARCHAR(100) NOT NULL,
            telegram_username VARCHAR(100) UNIQUE NOT NULL,
            password VARCHAR(255) NOT NULL,
            is_tutor BOOLEAN DEFAULT FALSE
        );
        """;

    public static final String SAVE = """
        INSERT INTO users (id, name, telegram_username, password, is_tutor) 
        VALUES (?, ?, ?, ?, ?);
        """;

    public static final String FIND_BY_ID = """
        SELECT * FROM users WHERE id = ?;
        """;

    public static final String FIND_BY_TELEGRAM_USERNAME = """
        SELECT * FROM users WHERE telegram_username = ?;
        """;

    public static final String UPDATE_TUTOR_STATUS = """
        UPDATE users SET is_tutor = ? WHERE id = ?;
        """;
}
