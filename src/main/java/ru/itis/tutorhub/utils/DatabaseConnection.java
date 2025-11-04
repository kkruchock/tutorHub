package ru.itis.tutorhub.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//класс для подключения к БД
public class DatabaseConnection {

    private static final Properties properties = new Properties();

    public static Connection getConnection() throws SQLException {

        //подгружаем драйвер
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //читаем и погружаем секреты
        try (InputStream inputStream = DatabaseConnection.class
                .getResourceAsStream("/application.properties")) {

            properties.load(inputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return DriverManager.getConnection(
                properties.getProperty("db.url"),
                properties.getProperty("db.username"),
                properties.getProperty("db.password")
        );
    }
}