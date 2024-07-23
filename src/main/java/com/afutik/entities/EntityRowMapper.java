package com.afutik.entities;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class EntityRowMapper {
    public static <T> List<T> rowsToEntities(ResultSet rs, Class<T> classEntity) {
        try {
            List<T> entities = new ArrayList<>();
            while(rs.next()) entities.add(rowToEntity(rs, classEntity));
            return entities;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T rowToEntity(ResultSet rs, Class<T> classEntity) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            T bean = classEntity.getDeclaredConstructor().newInstance();
            for(int i = 1; i <= columnCount; i++) {
                String columnName = rsmd.getColumnName(i);
                Object columnValue = rs.getObject(i);

                var field = classEntity.getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(bean, columnValue);
            }
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Object> entityToRow(Object entity) {
        List<Object> values = new ArrayList<>();
        Class<?> tClass = entity.getClass();

        for(Field field : tClass.getDeclaredFields()) {
            field.setAccessible(true);

            try {
                Object value = field.get(entity);
                values.add(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return values;
    }
}
