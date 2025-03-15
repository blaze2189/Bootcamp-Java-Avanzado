package com.example.refactor.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;

public class ExampleFileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleFileUtils.class);

    public static JSONObject getJsonFromFile(File inputSource) {
        LOGGER.info("Input source: "+ inputSource);
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

    /**
     * Se retorna un Optional para que se pueda hacer el manejo
     * del mismo desde la clase que lo invoque
     */
    public static Optional<File> getFileFromResources(String fileName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        //actualización a Optional
        //Se elimina IllegalArgumenException
         Optional<File> optionalFile = Optional
                 .ofNullable(Thread.currentThread().getContextClassLoader()).
                map(classloader ->classloader.getResource(fileName)).
                map(url -> new File(url.getFile()));

         //Se agrega log para informar que el archivo no ha sido encontrado
         if(optionalFile.isEmpty()){
             LOGGER.info("File: "+fileName +" not found");
         }

         return optionalFile;
    }

    public static Optional<File> getFileFromSystem(String filePath){
        File file=Paths.get(filePath).toFile();
        
        return Optional.of(file);
    }
}
