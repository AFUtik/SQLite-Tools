package com.afutik.repository;

import com.afutik.SQLiteDatabase;
import com.afutik.annotation.Repo;
import com.afutik.entity.Entity;
import com.afutik.entity.utils.EntityMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Repository<T extends Entity> {
    private final Repo annotation = this.getClass().getAnnotation(Repo.class);

    private final Class<? extends T> repoEntity = (Class<? extends T>) annotation.entityClass();
    private final String table = annotation.table();

    private final String CREATE = "INSERT INTO " + this.table + " VALUES(" +
            String.join(", ", Collections.nCopies(repoEntity.getDeclaredFields().length, "?")) + ")";

    private final String UPDATE = "UPDATE " + this.table + " SET " +
            Entity.getValuesName(repoEntity)
                    .stream()
                    .map(value -> value + " = ?")
                    .collect(Collectors.joining(",")) + " WHERE " +
            Entity.getKeysName(repoEntity)
                    .stream()
                    .map(key -> key + " = ?")
                    .collect(Collectors.joining(" and "));

    private final String GET = "SELECT * FROM " + this.table + " WHERE " +
            Entity.getKeysName(repoEntity)
                    .stream()
                    .map(key -> key + " = ?")
                    .collect(Collectors.joining(" and "));

    private final String DELETE = "DELETE FROM " + this.table + " WHERE " +
            Entity.getKeysName(repoEntity)
                    .stream()
                    .map(key -> key + " = ?")
                    .collect(Collectors.joining(" and "));

    public void create(T entity) {
        SQLiteDatabase.prepareStatement(CREATE, entity.toRow());
    }

    public void update(T entity) {
        SQLiteDatabase.prepareStatement(UPDATE, Stream.concat(Arrays.stream(entity.getValues()), Arrays.stream(entity.getKeys()))
                                                     .toArray(Object[]::new));
    }

    public T getById(Object... args) {
        try {
            return EntityMapper.rsToEntity(SQLiteDatabase.prepareStatement(GET, args), repoEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<T> getManyById(Object... args) {
        try {
            return (List<T>) EntityMapper.rsToEntities(SQLiteDatabase.prepareStatement(GET, args), repoEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Object... args) {
        try {
            SQLiteDatabase.prepareStatement(DELETE, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
