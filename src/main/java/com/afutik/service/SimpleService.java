package com.afutik.service;

import com.afutik.entity.impl.SimpleEntity;
import com.afutik.repository.SimpleRepository;

import java.util.List;

public class SimpleService {
    private final static SimpleRepository simpleRepo = new SimpleRepository();

    public static SimpleEntity create(SimpleEntity entity) {
        simpleRepo.create(entity);
        return entity;
    }

    public static SimpleEntity update(SimpleEntity entity) throws Exception {
        if(simpleRepo.getById(entity.getName()) == null) throw new Exception(entity.getName() + " doesn't exists");

        simpleRepo.update(entity);
        return entity;
    }

    public static SimpleEntity getByName(String name) {
        return simpleRepo.getById(name);
    }

    public static List<SimpleEntity> getManyByName(String name) {
        return simpleRepo.getManyById(name);
    }
}
