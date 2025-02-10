package com.example.refactor.util;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;


public class ExampleFileUtilsTest {

    @Test
    public void findFileResource() {
        Path path = Paths.get("");
        ExampleFileUtils.getJsonFromFile(path.toFile());
    }

    @Test
    public void testGetFileFromResourcesNotFound() {

        Optional<File> optionalFile = ExampleFileUtils.getFileFromResources("fileNotFound.jpg");
        assertFalse(optionalFile.isPresent());

    }

    @Test
    public void testGetFileFromResources() {

            ExampleFileUtils.getFileFromResources("");
            assert true;

    }

    @Test
    public void testFileOptional() {
        String fileName = "playlist.json";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        Optional<File> optionalURL = Optional.ofNullable(classLoader.getResource(fileName)).
                map(url -> new File(url.getFile()));

        optionalURL.ifPresent(System.out::println);

    }

    @Test
    public void testGetJsonFromFileEmptyJson(){
        String fakeFileName ="./fake.json";
        File fakeFile = ExampleFileUtils.getFileFromResources(fakeFileName).get();
        ExampleFileUtils.getJsonFromFile(fakeFile);
    }

    @Test
    public void testGetJsonFromFileFileNotFound(){
        String fakeFileName ="./fakes.json";
        File fakeFile = Paths.get(fakeFileName).toFile();
        ExampleFileUtils.getJsonFromFile(fakeFile);
    }

}