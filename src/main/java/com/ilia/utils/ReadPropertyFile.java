package com.ilia.utils;

import java.io.FileInputStream;
import java.util.Objects;
import java.util.Properties;

public final class ReadPropertyFile {
    private ReadPropertyFile() {
    }

    public static String getValue(String key) throws Exception {
        Properties properties = new Properties();
        FileInputStream file = new FileInputStream(System.getProperty("user.dir")
                + "/src/test/resources/config/config.properties");
        properties.load(file);
        String value = properties.getProperty(key);
        if(Objects.isNull(value)){
            throw new Exception("Property name " + key + " is not found. Please check config.properties.");
        }
        return value;
    }
}
