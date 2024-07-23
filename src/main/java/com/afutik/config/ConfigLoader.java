package com.afutik.config;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class ConfigLoader {
    private static Map<String, Object> config;

    static {
        loadConfig("config.yml");
    }

    public static void loadConfig(String fileName) {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = ConfigLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("file not found: " + fileName);
            }
            config = yaml.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String name, Class<T> tClass) {
        if (config == null) throw new IllegalStateException("Config not loaded");

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
            return null;
        }
    }
}
