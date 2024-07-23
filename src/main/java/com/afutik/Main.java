package com.afutik;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        PreparedStatement preparedStatement = SqliteDatabase.connection.prepareStatement("INSERT INTO users (id) VALUES (?)");
    }
}