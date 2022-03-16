package com.lucas.codingdemo.component;

import com.lucas.codingdemo.model.Host;
import com.lucas.codingdemo.service.FileInService;
import com.lucas.codingdemo.service.FileOutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MainComponent implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(MainComponent.class);

    @Autowired
    FileInService fileInService;
    @Autowired
    FileOutService fileOutService;

    @Override
    public void run(String... args){
        try {
            if(args.length == 0){
                throw  new IllegalArgumentException("Missing path to input file");
            }
            List<Host> hosts;
            String fileIn = args[0];

            String fileOut = null;
            if (args.length == 2) {
                fileOut = args[1];
            }
            List<String> hostsStringList = fileInService.convertFileToStringList(fileIn);
            hosts = fileInService.convertStringToHosts(hostsStringList);
            fileOutService.generateOutputFile(hosts, fileOut);
        }catch (IllegalArgumentException e){
            logger.error(e.getMessage());
        }
    }

}
