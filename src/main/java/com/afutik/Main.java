package com.afutik;

import com.afutik.entities.EntityRowMapper;
import com.afutik.entities.UserEntity;
import com.afutik.repositories.UserRepository;
import com.afutik.services.UserService;

import java.security.Provider;
import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException {
        Class.forName("com.afutik.SqliteDatabase");

        System.out.println(UserService.getBy("username", "ads"));
    }
}