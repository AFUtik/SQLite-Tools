package com.afutik.entities;

import com.afutik.annotations.Key;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Entity {
    public static List<String> getKeysName(Class<? extends Entity> classEntity) {
        List<String> keys = new ArrayList<>();
        for(Field field : classEntity.getDeclaredFields()) {
            field.setAccessible(true);
            if(field.isAnnotationPresent(Key.class)) keys.add(field.getName());
        }
        return keys;
    }

    public static List<String> getValuesName(Class<? extends Entity> classEntity) {
        List<String> values = new ArrayList<>();
        for (Field field : classEntity.getDeclaredFields()) {
            field.setAccessible(true);
            if (!field.isAnnotationPresent(Key.class)) values.add(field.getName());
        }
        return values;
    }

    public List<String> getKeysName() {
        return Entity.getKeysName(this.getClass());
    }

    public List<String> getValuesName() {
        return Entity.getValuesName(this.getClass());
    }

    public Object[] getKeys() {
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

    public Object[] getValues() {
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
}