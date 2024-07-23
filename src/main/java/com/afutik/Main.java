package com.afutik;

import com.afutik.entities.EntityRowMapper;
import com.afutik.entities.UserEntity;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.afutik.SqliteDatabase");

        UserEntity entity = new UserEntity(2, "a", "ads", "asd");
        System.out.println(EntityRowMapper.entityToRow(entity));
    }
}