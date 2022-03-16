package com.lucas.codingdemo.service;

import com.lucas.codingdemo.model.Host;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class FileOutServiceTest {


    public static final String OUTPUT_DATA = "/Output-Data.txt";
    public static final String CUSTOM_PATH = "/src/test/resources" + OUTPUT_DATA;
    FileOutService fileOutService = new FileOutService();
    static Host host;
    static List<Host> hostList;


    @BeforeAll
    static void setup(){
        host = new Host();
        hostList = new ArrayList<>();
        host.setHostName("testHost");
        host.setAvgValue(50.1);
        host.setMinValue(22.0);
        host.setMaxValue(155.7);
        hostList.add(host);
    }

    @AfterAll
    static void done(){

        try {
            Files.deleteIfExists(Paths.get(System.getProperty("user.dir") + OUTPUT_DATA));
            Files.deleteIfExists(Paths.get(System.getProperty("user.dir") + CUSTOM_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


    @Test
    void ifPathIsNullThenOutputFileShouldBeCreatedInWorkingDir(){
        fileOutService.generateOutputFile(hostList,null);
        assertTrue(Files.exists(Paths.get(System.getProperty("user.dir") + OUTPUT_DATA)));
    }

    @Test
    void ifPathIsNotNullThenOutputFileShouldBeCreatedInGivenPath(){
        String outputPath = System.getProperty("user.dir") + "/src/test/resources";
        fileOutService.generateOutputFile(hostList,outputPath);
        assertTrue(Files.exists(Paths.get(System.getProperty("user.dir") + CUSTOM_PATH)));

    }
}
