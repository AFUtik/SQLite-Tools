package com.afutik.config;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigLoader {
    public static final String RESOURCE_PATH = "src/main/resources/";

    public static YamlConfig loadYamlConfig(String fileName) {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = ConfigLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("file not found: " + fileName);
            }
            return new YamlConfig(yaml.load(inputStream));
        } catch (IOException ex) {
            return null;
        }
    }

    public static String loadString(String relativePath) {
        Path path = Paths.get(relativePath);
        try {
            String content = Files.readString(path);
            if (content == null) {
                throw new IOException("Resource not found: " + relativePath);
            }
            return content;
        } catch (IOException ex) {
            return null;
        }
    }
}
