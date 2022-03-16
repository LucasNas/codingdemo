package com.lucas.codingdemo.model;

import lombok.Data;

@Data
public class Host implements Comparable<Host> {
    String hostName;
    double avgValue;
    double minValue;
    double maxValue;

    @Override
    public int compareTo(Host host){
        return Double.compare(getAvgValue(), host.getAvgValue());
    }

    @Override
    public String toString() {
        return  hostName + ':' +
                " Average: " + avgValue +
                " Max: " + maxValue +
                " Min: " + minValue;
    }
}
