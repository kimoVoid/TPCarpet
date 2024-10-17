package me.kimovoid.tpcarpet.utils;

import carpet.settings.Rule;
import static carpet.settings.RuleCategory.*;


public class TPCarpetSettings {
    public static final String TP = "TPCARPET";
    public static final String BUGFIX = "BUGFIX";

    @Rule(desc = "Prevent the server from crashing due to StackOverFlowError, OutOfmemoryError or ClassCastException.", category = {TP, BUGFIX})
    public static boolean yeetUpdateSuppressionCrash = false;
}

