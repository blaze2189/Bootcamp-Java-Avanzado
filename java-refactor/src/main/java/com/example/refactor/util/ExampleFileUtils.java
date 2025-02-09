package com.example.refactor.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleFileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleFileUtils.class);

    public static JSONObject getJsonFromFile(File inputSource) {
        JSONParser parser = new JSONParser();
        //agregando try-resources para cerrar el recurso FileReader
        try (FileReader fileReader = new FileReader(inputSource)) {
            return (JSONObject) parser.parse(fileReader);
        } catch (IOException | ParseException e) {
            //Removiendo printstackrace
            //se escribe el mensaje de error en el log
            LOGGER.error(e.getMessage());
        }

        return null;
    }

    public static File getFileFromResources(String fileName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        //actualización a Optional
        URL optionalURL = Optional.ofNullable(classLoader.getResource(fileName)).
                orElseThrow(() -> new IllegalArgumentException("Missing file"));

        return new File(optionalURL.getFile());
    }

}
