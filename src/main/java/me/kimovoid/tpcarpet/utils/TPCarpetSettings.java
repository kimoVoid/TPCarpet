package me.kimovoid.tpcarpet.utils;

public class TPCarpetSettings {
    public static final String TP = "TP-Carpet";

    @Rule(desc = "Prevent the server from crashing due to StackOverFlowError, OutOfmemoryError or ClassCastException.", category = {TP, BUGFIX})
    public static boolean yeetUpdateSuppressionCrash = false;
}

