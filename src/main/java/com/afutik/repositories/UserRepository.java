package com.afutik.repositories;

import com.afutik.entities.UserEntity;

public class UserRepository extends Repository<UserEntity> {
    public UserRepository() {
        super(UserEntity.class, "users");
    }
}
