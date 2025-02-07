package com.example.refactor.service;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.fail;


public class ExampleFileUtilsTest {

    @Test
    public void findFileResource(){
        Path path = Paths.get("");
        ExampleFileUtils.getJsonFromFile(path.toFile());
    }

    @Test
    public void testGetFileFromResourcesNotFound(){

        try {
            ExampleFileUtils.getFileFromResources("fileNotFound.jpg");
            fail("IllegalArgumentException expected");
        }catch (IllegalArgumentException illegalArgumentException){
            assert true;
        }

    }

    @Test
    public void testGetFileFromResources(){

        try {
            ExampleFileUtils.getFileFromResources("");
            assert true;
        }catch (IllegalArgumentException illegalArgumentException){
            fail("Exception not expected");
        }

    }

}