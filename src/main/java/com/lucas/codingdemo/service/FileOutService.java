package com.lucas.codingdemo.service;

import com.lucas.codingdemo.model.Host;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@Service
public class FileOutService {

    Logger logger = LoggerFactory.getLogger(FileOutService.class);

    public void generateOutputFile(List<Host> hosts, String filePath){
        if(filePath != null && Files.isDirectory(Paths.get(filePath))){
            filePath = filePath  +"/Output-Data.txt";
        }else{
            filePath = System.getProperty("user.dir") + "/Output-Data.txt";
        }
        Collections.sort(hosts);
        Collections.reverse(hosts);

        try(FileWriter fileWriter = new FileWriter(filePath)) {
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(Host host: hosts){
                printWriter.println(host);
            }
            logger.info("Output file written successfully");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }
}
