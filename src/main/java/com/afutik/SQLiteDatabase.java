package com.afutik;

import com.afutik.config.ConfigLoader;
import com.afutik.config.YamlConfig;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.logging.Logger;

public class SQLiteDatabase {
    private static String configPath = "sqlite.yml";

    private static String DATABASE_PATH;
    private static String SCHEMA;

    public static Connection connection;
    public static Statement statement;

    public static Logger logger;

    public static void setConfigPath(String path) {
        configPath = path;
    }

    private static void loadConfig() {
        YamlConfig config = ConfigLoader.loadYamlConfig(configPath);

        Path db_path = Paths.get(config.getString("database-path"));
        File db = new File(db_path.getParent().toString());
        if(!db.exists()) db.mkdirs();
        DATABASE_PATH = db_path.toAbsolutePath().toString();

        SCHEMA = ConfigLoader.loadString(config.getString("schema-path"));
    }

    public static void connect() {
        connect(null);
    }

    public static void connect(Logger logger) {
        try {
            loadConfig();

            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:" + DATABASE_PATH
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

    public static ResultSet prepareStatement(String query, Object... args) {
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
