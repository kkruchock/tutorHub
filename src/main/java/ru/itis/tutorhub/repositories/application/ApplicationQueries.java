package ru.itis.tutorhub.repositories.application;

public class ApplicationQueries {

    public static final String CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS applications (
            id UUID PRIMARY KEY,
            tutor_profile_id UUID NOT NULL REFERENCES tutor_profiles(id),
            student_id UUID NOT NULL REFERENCES users(id),
            subject_id UUID NOT NULL REFERENCES subjects(id),
            goal_id UUID NOT NULL REFERENCES goals(id),
            fixed_price INTEGER NOT NULL CHECK (fixed_price > 0),
            message TEXT,
            status VARCHAR(20) NOT NULL CHECK (status IN ('PENDING', 'ACCEPTED', 'REJECTED', 'CANCELLED')),
            created_at TIMESTAMP DEFAULT NOW()
        );
        """;

    public static final String SAVE = """
        INSERT INTO applications (id, tutor_profile_id, student_id, subject_id, goal_id, fixed_price, message, status, created_at) 
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);
        """;

    public static final String FIND_BY_ID = """
        SELECT *
         FROM applications WHERE id = ?;
        """;

    public static final String UPDATE_STATUS = """
        UPDATE applications 
        SET status = ? 
        WHERE id = ?;
        """;

    public static final String DELETE_BY_ID = """
        DELETE FROM applications
        WHERE id = ?;
        """;

    public static final String FIND_BY_STUDENT_ID = """
        SELECT *
        FROM applications 
        WHERE student_id = ? 
        ORDER BY created_at DESC;
        """;

    public static final String FIND_BY_TUTOR_ID = """
        SELECT *
        FROM applications 
        WHERE tutor_profile_id = ? 
        ORDER BY created_at DESC;
        """;

    public static final String FIND_BY_TUTOR_ID_AND_STATUS = """
        SELECT *
        FROM applications 
        WHERE tutor_profile_id = ? AND status = ? 
        ORDER BY created_at DESC;
        """;

    public static final String EXISTS_PENDING_BY_STUDENT_AND_TUTOR = """
        SELECT 1 
        FROM applications 
        WHERE student_id = ? AND tutor_profile_id = ? AND status = 'PENDING' 
        LIMIT 1;
        """;
}