package com.afutik.entity.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/*
    This class converts ResultSet to Entity or Entities.
 */
public class EntityMapper {
    public static <T> List<T> rsToEntities(ResultSet rs, Class<T> classEntity) {
        try {
            List<T> entities = new ArrayList<>();
            while(rs.next()) entities.add(rsToEntity(rs, classEntity, true));
            return entities;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T rsToEntity(ResultSet rs, Class<T> classEntity, boolean loop) {
        try {
            if(!loop && !rs.next()) return null;

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            Field[] fields = classEntity.getDeclaredFields();
            if(columnCount != fields.length) throw new RuntimeException("columns != fields");
            Object[] values = new Object[fields.length];
            Class<?>[] types = new Class[fields.length];
            for(int i = 0; i < fields.length; i++) {
                Object columnValue = rs.getObject(i+1);
                Field field = fields[i];

                field.setAccessible(true);
                types[i] = field.getType();
                if(field.getType().equals(Timestamp.class)) {
                    values[i] = new Timestamp((Long) columnValue);
                } else {
                    values[i] = columnValue;
                }
            }
            return classEntity.getDeclaredConstructor(types).newInstance(values);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T rsToEntity(ResultSet rs, Class<T> classEntity) {
        return rsToEntity(rs, classEntity, false);
    }

}
