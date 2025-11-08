package ru.itis.tutorhub.repositories.dictionaries.subject;

public class SubjectQueries {

    public static final String CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS subjects (
            id UUID PRIMARY KEY,
            name VARCHAR(100) UNIQUE NOT NULL
        );
        """;

    public static final String FIND_ALL = """
        SELECT * FROM subjects ORDER BY name;
        """;

    public static final String FIND_BY_ID = """
        SELECT * FROM subjects WHERE id = ?;
        """;

    public static final String FIND_BY_NAME = """
        SELECT * FROM subjects WHERE name = ?;
        """;

    public static final String INSERT_DEFAULT_DATA = """
        INSERT INTO subjects (id, name) VALUES 
        ('55555555-5555-5555-5555-555555555555', 'Математика'),
        ('66666666-6666-6666-6666-666666666666', 'Физика'),
        ('77777777-7777-7777-7777-777777777777', 'Химия'),
        ('88888888-8888-8888-8888-888888888888', 'Информатика'),
        ('99999999-9999-9999-9999-999999999999', 'Русский язык'),
        ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Английский язык'),
        ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Обществознание'),
        ('cccccccc-cccc-cccc-cccc-cccccccccccc', 'История'),
        ('dddddddd-dddd-dddd-dddd-dddddddddddd', 'Биология')
        ON CONFLICT (id) DO NOTHING;
        """;
}