package com.lucas.codingdemo.service;

import com.lucas.codingdemo.model.Host;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class FileInServiceTest {

    public static final String N10 = "n10,1366829460,1366831260,60|37.0,65.0,87.0,41.0,100.0,76.0,83.0,82.0,50.0,54.0";
    public static final String N12 = "n12,1366829460,1366831260,60|40.0,35.0,75.0,35.0,62.0,67.0,38.0,31.0,34.0,34.0";
    FileInService fileInService = new FileInService();
    public static final String CUSTOM_PATH = "/src/test/resources/Coding Demo Data.txt";

    @Test
    void whenPathIsValidThenMethodShouldReturnStringList(){
        List<String> expectedReturn = new ArrayList<>();
        expectedReturn.add(N10);
        expectedReturn.add(N12);
        String path = System.getProperty("user.dir") + CUSTOM_PATH;
        List<String> actualReturn = fileInService.convertFileToStringList(path);
        assertEquals(expectedReturn,actualReturn);
    }

    @Test
    void whenStringListThenMethodShouldReturnHostList(){
        List<String> hostStringList = new ArrayList<>();
        List<Host> expectedReturn = createExpectedList();
        hostStringList.add(N10);
        hostStringList.add(N12);

        List<Host> actualReturn = fileInService.convertStringToHosts(hostStringList);
        assertEquals(expectedReturn,actualReturn);
    }


    private List<Host> createExpectedList(){
        Host host1 = new Host();
        Host host2 = new Host();
        List<Host> hostList = new ArrayList<>();
        host1.setHostName("n10");
        host1.setAvgValue(67.5);
        host1.setMaxValue(100.0);
        host1.setMinValue(37.0);
        hostList.add(host1);
        host2.setHostName("n12");
        host2.setAvgValue(45.1);
        host2.setMaxValue(75.0);
        host2.setMinValue(31.0);
        hostList.add(host2);

        return hostList;
    }


}
