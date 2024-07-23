package com.afutik;

import com.afutik.config.ConfigLoader;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SqliteDatabase {
    public static Connection connection;
    public static Statement statement;

    static {
        connect();
    }

    private static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:" + ConfigLoader.get("database", String.class)
            );
            statement = connection.createStatement();
            statement.executeUpdate(new String(Files.readAllBytes(Paths.get("src/main/resources/schema.sql"))));

            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
