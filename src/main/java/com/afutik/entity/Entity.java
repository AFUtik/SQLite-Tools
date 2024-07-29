package com.afutik.entity;

import com.afutik.annotation.Key;
import com.afutik.annotation.Value;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public interface Entity {
    static List<String> getKeysName(Class<? extends Entity> classEntity) {
        List<String> keys = new ArrayList<>();
        for(Field field : classEntity.getDeclaredFields()) {
            field.setAccessible(true);
            if(field.isAnnotationPresent(Key.class)) {
                String name = field.getAnnotation(Key.class).name();
                if(!name.isEmpty()) {
                    keys.add(name);
                } else {
                    keys.add(field.getName());
                }
            };
        }
        return keys;
    }

    static List<String> getValuesName(Class<? extends Entity> classEntity) {
        List<String> values = new ArrayList<>();
        for (Field field : classEntity.getDeclaredFields()) {
            field.setAccessible(true);
            if(field.isAnnotationPresent(Value.class)) {
                values.add(field.getAnnotation(Value.class).name());
            } else if (!field.isAnnotationPresent(Key.class)) {
                values.add(field.getName());
            };
        }
        return values;
    }

    default Object[] getKeys() {
        List<Object> keys = new ArrayList<>();
        for(Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if(field.isAnnotationPresent(Key.class)) {
                try {
                    keys.add(field.get(this));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return keys.toArray();
    }

    default Object[] getValues() {
        List<Object> values = new ArrayList<>();
        for(Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if(!field.isAnnotationPresent(Key.class)) {
                try {
                    values.add(field.get(this));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return values.toArray();
    }

    default Object[] toRow() {
        Field[] fields = this.getClass().getDeclaredFields();
        Object[] values = new Object[fields.length];
        for(int i = 0; i < fields.length; i++) {
            try {
                Field field = fields[i];
                field.setAccessible(true);
                values[i] = field.get(this);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return values;
    }
}
