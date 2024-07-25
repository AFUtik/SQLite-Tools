package com.afutik.services;

import com.afutik.entities.UserEntity;
import com.afutik.repositories.UserRepository;

import java.sql.SQLException;

public class UserService {
    private static final UserRepository userRepo = new UserRepository();

    public static UserEntity create(UserEntity entity) throws SQLException {
        userRepo.create(entity);
        return entity;
    }

    public static UserEntity update(UserEntity entity) throws SQLException {
        userRepo.update(entity);
        return entity;
    }

    public static UserEntity getBy(String field, Object value) {
        return userRepo.getBy(field, value);
    }

    public static UserEntity getById(int userId) {
        return userRepo.getById(userId);
    }

    public static void delete(int userId) {
        userRepo.delete(userId);
    }
}
