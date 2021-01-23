package com.upb.snowden.utils;

public class Logger {

    public static final boolean isLoggingEnabled = true;

    public static void log(String text) {
        if (isLoggingEnabled) {
            System.out.println(text);
        }
    }
}
