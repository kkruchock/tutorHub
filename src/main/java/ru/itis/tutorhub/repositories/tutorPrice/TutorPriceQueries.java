package ru.itis.tutorhub.repositories.tutorPrice;

public class TutorPriceQueries {

    public static final String CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS tutor_prices (
                id UUID PRIMARY KEY,
                tutor_id UUID NOT NULL REFERENCES tutor_profiles(id) ON DELETE CASCADE,
                subject_id UUID NOT NULL REFERENCES subjects(id),
                goal_id UUID NOT NULL REFERENCES goals(id),
                price_per_hour INTEGER NOT NULL CHECK (price_per_hour > 0),
                UNIQUE(tutor_id, subject_id, goal_id)
            );
            """;

    public static final String SAVE = """
            INSERT INTO tutor_prices (id, tutor_id, subject_id, goal_id, price_per_hour) 
            VALUES (?, ?, ?, ?, ?);
            """;

    public static final String FIND_BY_TUTOR_ID = """
            SELECT * FROM tutor_prices 
            WHERE tutor_id = ? 
            ORDER BY subject_id, goal_id;
            """;

    public static final String FIND_BY_SUBJECT_AND_GOAL = """
            SELECT tp.*
            FROM tutor_prices tp
            JOIN tutor_profiles tpr
            ON tp.tutor_id = tpr.id
            WHERE tp.subject_id = ? AND tp.goal_id = ?
            ORDER BY tp.price_per_hour;
            """;

    public static final String FIND_BY_TUTOR_SUBJECT_GOAL = """
            SELECT 
            * FROM tutor_prices 
            WHERE tutor_id = ? AND subject_id = ? AND goal_id = ?;
            """;

    public static final String DELETE_BY_ID = """
            DELETE FROM tutor_prices
            WHERE id = ?;
            """;

    public static final String DELETE_BY_TUTOR_ID = """
            DELETE FROM tutor_prices
            WHERE tutor_id = ?;
            """;
}