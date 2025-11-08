package ru.itis.tutorhub.repositories.dictionaries.goal;

public class GoalQueries {

    public static final String CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS goals (
            id UUID PRIMARY KEY,
            name VARCHAR(100) UNIQUE NOT NULL
        );
        """;

    public static final String FIND_ALL = """
        SELECT * 
        FROM goals ORDER BY name;
        """;

    public static final String FIND_BY_ID = """
        SELECT * 
        FROM goals WHERE id = ?;
        """;

    public static final String FIND_BY_NAME = """
        SELECT * 
        FROM goals WHERE name = ?;
        """;

    public static final String INSERT_DEFAULT_DATA = """
        INSERT INTO goals (id, name) VALUES 
        ('11111111-1111-1111-1111-111111111111', 'ЕГЭ'),
        ('22222222-2222-2222-2222-222222222222', 'ОГЭ'),
        ('33333333-3333-3333-3333-333333333333', 'Олимпиада'),
        ('44444444-4444-4444-4444-444444444444', 'Повышение успеваемости')
        ON CONFLICT (id) DO NOTHING;
        """;
}