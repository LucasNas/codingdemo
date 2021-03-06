package com.lucas.codingdemo.util;

public class RoundingUtil {

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    private RoundingUtil(){
        throw  new IllegalStateException("Utility class");
    }
}
