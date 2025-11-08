package ru.itis.tutorhub.repositories.tutorProfile;

public class TutorProfileQueries {

    public static final String CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS tutor_profiles (
            id UUID PRIMARY KEY,
            user_id UUID UNIQUE NOT NULL REFERENCES users(id),
            name VARCHAR(100) NOT NULL,
            description TEXT,
            education VARCHAR(255),
            image_path VARCHAR(500) DEFAULT 'images/default-avatar.png',
            avg_rating DECIMAL(3,2) DEFAULT 0.00
        );
        """;

    public static final String SAVE = """
        INSERT INTO tutor_profiles (id, user_id, name, description, education, image_path, avg_rating) 
        VALUES (?, ?, ?, ?, ?, ?, ?);
        """;

    public static final String FIND_BY_ID = """
        SELECT * 
        FROM tutor_profiles WHERE id = ?;
        """;

    public static final String FIND_ALL = """
        SELECT * 
        FROM tutor_profiles;
        """;

    public static final String DELETE_BY_ID = """
        DELETE FROM tutor_profiles 
        WHERE id = ?;
        """;

    public static final String FIND_BY_USER_ID = """
        SELECT *
        FROM tutor_profiles 
        WHERE user_id = ?;
        """;

    public static final String FIND_BY_SUBJECT_AND_GOAL = """
        SELECT tp.* 
        FROM tutor_profiles tp
        JOIN tutor_prices tpr 
        ON tp.id = tpr.tutor_id
        WHERE tpr.subject_id = ? AND tpr.goal_id = ?;
        """;
}