package main.java.helpers.logging;

import java.util.Date;
import java.io.StringWriter;
import java.io.PrintWriter;

import main.java.helpers.configurations.*;

public class Logger {

    private static String serviceName = "unknown";
    private static String serviceVersion = "unknown";
    private static boolean isInitialized = false;

    public enum Type {
        INFO {
            public String toString() {
                return "INFO";
            }
        },
        WARN {
            public String toString() {
                return "WARN";
            }
        },
        ERROR {
            public String toString() {
                return "ERROR";
            }
        },
    }

    public static void init() {
        if(!Logger.isInitialized && PropertiesReader.isConfigurationLoaded("service")) {
            Logger.serviceName = PropertiesReader.getProperty("service", "NAME");
            Logger.serviceVersion = PropertiesReader.getProperty("service", "VERSION");
            Logger.isInitialized = true;
        }
    }

    public static void log(Type type, String message) {
        Logger.init(); // try to init if it is not jet
        String outputMessage = Logger.createLogHeader(type);
        message = message.replace("\n", "\n-> ");
        outputMessage += String.format("\n-> %s", message);
        System.out.println(outputMessage);
    }

    public static void info(String message) {
        Logger.log(Type.INFO, message);
    }

    public static void exception(Exception exception) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        Logger.log(Type.ERROR, sw.toString());
    }

    public static void error(String message) {
        Logger.log(Type.ERROR, message);
    }

    private static String createLogHeader(Type type) {
        return String.format("%s %s %s [%s]", new Date(), type, Logger.serviceName, Logger.serviceVersion);
    }

}