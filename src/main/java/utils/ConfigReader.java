package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try {
            try (FileInputStream input = new FileInputStream(Paths.get(System.getProperty("user.dir"), "config.properties").toFile())) {
                properties.load(input);
            } catch (IOException e) {
                System.err.println(String.format(Constants.ERROR_LOADING_CONFIG, e.getMessage()));
            }
        } catch (Exception e) {
            System.err.println(String.format(Constants.ERROR_ACCESSING_CONFIG, e.getMessage()));
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            return System.getProperty(key);
        }
        return value;
    }

    public static String getProperty(String key, String defaultValue) {
        String value = properties.getProperty(key);
        if (value == null || value.isEmpty()) {
            String systemProperty = System.getProperty(key);
            return systemProperty != null ? systemProperty : defaultValue;
        }
        return value;
    }
}

