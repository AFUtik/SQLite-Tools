package com.afutik.config;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class YamlConfig {
    public final Map<String, Object> config;

    public String getString(String name) {
        return get(name, String.class);
    }

    public Integer getInt(String name) {
        return get(name, Integer.class);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String name, Class<T> tClass) {
        String[] keys = name.split("\\.");
        Map<String, Object> currentMap = config;
        Object value = null;

        for (int i = 0; i < keys.length; i++) {
            value = currentMap.get(keys[i]);
            if (i < keys.length - 1) {
                if (value instanceof Map) {
                    currentMap = (Map<String, Object>) value;
                } else {
                    return null;
                }
            }
        }
        if(tClass.isInstance(value)) {
            return (T) value;
        } else {
            throw new RuntimeException("Cast Exception");
        }
    }
}
