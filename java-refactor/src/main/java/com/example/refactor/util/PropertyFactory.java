package com.example.refactor.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFactory {

    private static final Properties properties= new Properties();
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyFactory.class);
    private static final String CONFIG_FILE = "config.properties";

    static {
        
        try (InputStream inputStream = PropertyFactory.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("ERROR while reading the configuration file: {}", e.getMessage());
        }
    }

    public static Properties getProperties(){
        return properties;
    }

}
