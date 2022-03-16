package com.lucas.codingdemo.service;

import com.lucas.codingdemo.model.Host;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.lucas.codingdemo.util.RoundingUtil.roundAvoid;

@Service
public class FileInService {

    Logger logger = LoggerFactory.getLogger(FileInService.class);

    public List<String> convertFileToStringList(String filePath){
        List<String> hosts = new ArrayList<>();
        try(Stream<String> hostsStrings = Files.lines(Paths.get(filePath))) {
            hostsStrings.forEachOrdered(hosts::add);
            logger.info("Data successfully gathered from file");
        } catch (IOException e) {
           logger.error(e.getMessage());
        }

        return hosts;
    }

    public List<Host> convertStringToHosts(List<String> hosts){
        List<Host> hostList = new ArrayList<>();
        for(String host: hosts){
            Host hostPOJO = new Host();
            String[] splitHost = host.split("[|]");
            String[] hostInfo = splitHost[0].split(",");
            String[] values = splitHost[1].split(",");
            List<Double> valuesList = new ArrayList<>();


            for(String value: values){
                try {
                    valuesList.add(Double.parseDouble(value));
                } catch (NumberFormatException nfe) {
                    valuesList.add(0.0);
                }
            }

            double sum = 0.0;
            double max = Double.MIN_VALUE;
            double min = Double.MAX_VALUE;
            for (Double aDouble : valuesList) {
                double doubleValue = aDouble;
                sum += doubleValue;
                if(doubleValue > max){
                    max = doubleValue;
                }
                if(doubleValue < min){
                    min = doubleValue;
                }
            }
            hostPOJO.setHostName(hostInfo[0]);
            hostPOJO.setAvgValue(roundAvoid(sum/ values.length,1));
            hostPOJO.setMaxValue(roundAvoid(max,1));
            hostPOJO.setMinValue(roundAvoid(min,1));
            hostList.add(hostPOJO);
        }
        return hostList;
    }

}
