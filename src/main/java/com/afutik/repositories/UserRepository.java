package com.afutik.repositories;

import com.afutik.annotations.EntityRepository;
import com.afutik.entities.UserEntity;

@EntityRepository(entityClass = UserEntity.class, table = "users")
public class UserRepository extends Repository<UserEntity> {}
