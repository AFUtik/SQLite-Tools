package com.afutik;

import com.afutik.config.ConfigLoader;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqliteDatabase {
    public static Connection connection;

    static {
        connect();
    }

    private static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:" + ConfigLoader.get("database", String.class)
            );
            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
