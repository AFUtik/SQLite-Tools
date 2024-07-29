package com.afutik.config;

import lombok.SneakyThrows;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;

public class ConfigLoader {
    public static YamlConfig loadYamlConfig(String fileName) {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = ConfigLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("file not found: " + fileName);
            }
            return new YamlConfig(yaml.load(inputStream));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public static String loadResourceAsString(String resourcePath) {
        try (InputStream inputStream = ConfigLoader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + resourcePath);
            }
            return new String(inputStream.readAllBytes());
        }
    }
}
