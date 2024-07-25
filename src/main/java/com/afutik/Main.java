package com.afutik;

import com.afutik.entities.EntityRowMapper;
import com.afutik.entities.UserEntity;
import com.afutik.repositories.UserRepository;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException {
        Class.forName("com.afutik.SqliteDatabase");

        UserEntity entity = new UserEntity(null, "a", "ads", "asd");
        UserRepository userRepo = new UserRepository();

        System.out.println(userRepo.getById(1).getName());
    }
}