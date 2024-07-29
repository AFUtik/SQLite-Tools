package com.afutik.repository;

import com.afutik.annotation.Repo;
import com.afutik.entity.impl.SimpleEntity;

@Repo(entityClass = SimpleEntity.class, table = "simple")
public class SimpleRepository extends Repository<SimpleEntity> {
}
