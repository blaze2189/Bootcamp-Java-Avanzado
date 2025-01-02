package com.example.refactor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFactory {

    private static final Properties properties;
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyFactory.class);
    private static final String CONFIG_FILE = "config.properties";

    static {
        properties = new Properties();
        
        try (InputStream inputStream = PropertyFactory.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("ERROR while reading the configuration file: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    public static Properties getProperties(){
        return properties;
    }

}
