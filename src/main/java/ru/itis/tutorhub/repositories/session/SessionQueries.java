package ru.itis.tutorhub.repositories.session;

public class SessionQueries {

    public static final String CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS sessions (
            id UUID PRIMARY KEY,
            user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
            session_token VARCHAR(255) UNIQUE NOT NULL,
            expires_at TIMESTAMP NOT NULL
        );
        """;

    public static final String SAVE = """
        INSERT INTO sessions (id, user_id, session_token, expires_at) 
        VALUES (?, ?, ?, ?);
        """;

    public static final String FIND_BY_TOKEN = """
        SELECT * 
        FROM sessions 
        WHERE session_token = ? AND expires_at > NOW();
        """;

    public static final String DELETE_BY_TOKEN = """
        DELETE FROM sessions 
        WHERE session_token = ?;
        """;

    public static final String DELETE_EXPIRED = """
        DELETE FROM sessions
         WHERE expires_at <= NOW();
        """;

    public static final String DELETE_BY_USER = """
        DELETE FROM sessions WHERE user_id = ?;
        """;
}