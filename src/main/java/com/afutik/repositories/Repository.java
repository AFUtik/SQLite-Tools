package com.afutik.repositories;

import com.afutik.SqliteDatabase;
import com.afutik.entities.Entity;
import com.afutik.entities.EntityRowMapper;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class Repository<T extends Entity> {
    private final Class<T> repoEntity;
    private final String table;

    public void create(T entity) throws SQLException {
        String query = "INSERT INTO " + this.table + " VALUES(" +
                EntityRowMapper.entityToRow(entity)
                        .stream()
                        .map(value -> value instanceof Number || value == null ? String.valueOf(value) : "'" + value + "'")
                        .collect(Collectors.joining(", ")) + ")";
        SqliteDatabase.statement.executeUpdate(query);
    }

    public void update(T entity) throws SQLException {
        String query = "UPDATE " + this.table + " SET " +
                entity.getValuesName()
                          .stream()
                          .map(value -> value + " = ?")
                          .collect(Collectors.joining(", ")) + "WHERE " +
                entity.getKeysName()
                        .stream()
                        .map(key -> key + " = ?")
                        .collect(Collectors.joining(", "));
        SqliteDatabase.prepareStatement(query, Stream.concat(Arrays.stream(entity.getValues()), Arrays.stream(entity.getKeys()))
                                                     .toArray(Object[]::new));
    }

    public T getById(Object... args) {
        try {
            String query = "SELECT * FROM " + this.table +
                           " WHERE " + Entity.getKeysName(repoEntity)
                                                  .stream()
                                                  .map(key -> key + " = ?")
                                                  .collect(Collectors.joining(" and "));
            return EntityRowMapper.rowToEntity(SqliteDatabase.prepareStatement(query, args), repoEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Object... args) {
        try {
            String query = "DELETE FROM " + this.table +
                           "WHERE " + Entity.getKeysName(repoEntity)
                                                .stream()
                                                .map(key -> key + " = ?")
                                                .collect(Collectors.joining(" and "));
            SqliteDatabase.prepareStatement(query, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
