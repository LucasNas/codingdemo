package com.lucas.codingdemo.component;


import com.lucas.codingdemo.model.Host;
import com.lucas.codingdemo.service.FileInService;
import com.lucas.codingdemo.service.FileOutService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainComponentTest {

    public static final String INPUT_PATH = "test/mock";
    @InjectMocks
    MainComponent mainComponent;

    @Mock
    FileInService fileInServiceMock;

    @Mock
    FileOutService fileOutServiceMock;

    @Test
    void whenThereIsNoInputFileThenAppShouldStop(){
        String[] mockArgs = {};
        mainComponent.run(mockArgs);

        verify(fileInServiceMock,times(0)).convertFileToStringList(anyString());
        verify(fileInServiceMock,times(0)).convertStringToHosts(anyList());
        verify(fileOutServiceMock,times(0)).generateOutputFile(anyList(),anyString());
    }

    @Test
    void whenThereIsOnlyArgThenInputPathShouldThatArg(){
        String[] mockArgs = {INPUT_PATH};
        Host hostMock = Mockito.mock(Host.class);
        List<Host> hostListMock = new ArrayList<>();
        List<String> stringListMock = new ArrayList<>();
        hostListMock.add(hostMock);

        doReturn(stringListMock).when(fileInServiceMock).convertFileToStringList(INPUT_PATH);
        doReturn(hostListMock).when(fileInServiceMock).convertStringToHosts(stringListMock);

        mainComponent.run(mockArgs);

        verify(fileInServiceMock,times(1)).convertFileToStringList(INPUT_PATH);
        verify(fileInServiceMock,times(1)).convertStringToHosts(stringListMock);
        verify(fileOutServiceMock,times(1)).generateOutputFile(hostListMock,null);
    }

    @Test
    void whenThereAre2ArgThenSecondArgShouldBeOutputPath(){
        String[] mockArgs = {INPUT_PATH, "test/output"};
        Host hostMock = Mockito.mock(Host.class);
        List<Host> hostListMock = new ArrayList<>();
        List<String> stringListMock = new ArrayList<>();
        hostListMock.add(hostMock);

        doReturn(stringListMock).when(fileInServiceMock).convertFileToStringList(INPUT_PATH);
        doReturn(hostListMock).when(fileInServiceMock).convertStringToHosts(stringListMock);

        mainComponent.run(mockArgs);


        verify(fileInServiceMock,times(1)).convertFileToStringList(INPUT_PATH);
        verify(fileInServiceMock,times(1)).convertStringToHosts(stringListMock);
        verify(fileOutServiceMock,times(1)).generateOutputFile(hostListMock,"test/output");
    }
}
