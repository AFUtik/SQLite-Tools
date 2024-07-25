package com.afutik;

import com.afutik.config.ConfigLoader;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;

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

    public static ResultSet prepareStatement(String query, Object[] args) throws SQLException {
        if(query.chars().filter(ch -> ch == '?').count() != args.length) throw new SQLException("Amount of arguments don't equal amount of the query");

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for(int i = 0; i < args.length; i++) preparedStatement.setObject(i+1, args[i]);

        if(query.toLowerCase().startsWith("select")) {
            return preparedStatement.executeQuery();
        } else {
            preparedStatement.executeUpdate();
            return null;
        }
    }
}
