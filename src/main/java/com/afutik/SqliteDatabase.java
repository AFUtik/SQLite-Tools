package com.afutik;

import com.afutik.config.ConfigLoader;
import com.afutik.config.YamlConfig;

import java.io.File;
import java.sql.*;
import java.util.logging.Logger;

public class SqliteDatabase {
    private static final YamlConfig config = ConfigLoader.loadYamlConfig("config.yml");

    private static final String DATABASE_NAME = config.getString("database-name");
    private static final String SCHEMA = ConfigLoader.loadResourceAsString("schema.sql");

    public static Connection connection;
    public static Statement statement;

    public static Logger logger;

    public static void connect(String dbPath) {
        connect(dbPath, null);
    }

    public static void connect(String dbPath, Logger logger) {
        try {
            File file = new File(dbPath);
            if(!file.exists()) file.mkdirs();

            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:" + dbPath + DATABASE_NAME
            );
            statement = connection.createStatement();
            statement.executeUpdate(SCHEMA);

            System.out.println("Connected successfully");
        } catch (SQLException | ClassNotFoundException ex) {
            if(logger != null) {
                logger.severe(ex.getMessage());
            } else {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static ResultSet prepareStatement(String query, Object[] args) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for(int i = 0; i < args.length; i++) preparedStatement.setObject(i + 1, args[i]);

            if(query.toLowerCase().startsWith("select")) {
                return preparedStatement.executeQuery();
            } else {
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        } catch (SQLException ex) {
            if(logger != null) {
                logger.severe(ex.getMessage());
            } else {
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }
}
