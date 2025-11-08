package ru.itis.tutorhub.repositories.review;

public class ReviewQueries {

    public static final String CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS reviews (
            id UUID PRIMARY KEY,
            application_id UUID UNIQUE NOT NULL REFERENCES applications(id),
            rating INTEGER NOT NULL CHECK (rating BETWEEN 1 AND 5),
            comment TEXT,
            created_at TIMESTAMP DEFAULT NOW()
        );
        """;

    public static final String SAVE = """
        INSERT INTO reviews (id, application_id, rating, comment, created_at) 
        VALUES (?, ?, ?, ?, ?);
        """;

    public static final String FIND_BY_TUTOR_ID = """
        SELECT r.* 
        FROM reviews r
        JOIN applications a 
        ON r.application_id = a.id
        WHERE a.tutor_profile_id = ?
        ORDER BY r.created_at DESC;
        """;

    public static final String FIND_BY_APPLICATION_ID = """
        SELECT * 
        FROM reviews 
        WHERE application_id = ?;
        """;

    public static final String UPDATE_TUTOR_RATING = """
        UPDATE tutor_profiles 
        SET avg_rating = (
            SELECT AVG(r.rating) 
            FROM reviews r
            JOIN applications a ON r.application_id = a.id
            WHERE a.tutor_profile_id = ?
        )
        WHERE id = ?;
        """;
}