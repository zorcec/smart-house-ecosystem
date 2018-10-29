package helpers.configurations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.lang.reflect.Type;

import helpers.logging.*;

public class PropertiesReader {

    private static HashMap<String, Properties> configurations = new HashMap<String, Properties>();

    public static void init() {
        PropertiesReader.loadConfigurationFiles();
    }

    public static String getProperty(String configurationFileName, String keyName) {
        return PropertiesReader.getProperty(configurationFileName, keyName, "");
    }

    public static String getProperty(String configurationFileName, String keyName, String defaultValue) {
        Properties propertiesFile = PropertiesReader.configurations.get(configurationFileName + ".properties");
        if(propertiesFile != null) {
            String value = propertiesFile.getProperty(keyName);
            // do we have that value
            if (value != null && !value.isEmpty()) {
                return value;
            }
            Logger.error(String.format("[%s %s] was not found in the configuration file!", configurationFileName, keyName));
            return defaultValue;
        }
        Logger.error(String.format("[%s] configuration file was not found!", configurationFileName));
        return defaultValue;
    }

    public static double getProperty(String configurationFileName, String keyName, double defaultValue) {
        Properties propertiesFile = PropertiesReader.configurations.get(configurationFileName + ".properties");
        if(propertiesFile != null) {
            String value = propertiesFile.getProperty(keyName);
            // do we have that value
            if (value != null && !value.isEmpty()) {
                return PropertiesReader.parseDouble(value);
            }
            Logger.error(String.format("[%s %s] was not found in the configuration file!", configurationFileName, keyName));
            return defaultValue;
        }
        Logger.error(String.format("[%s] configuration file was not found!", configurationFileName));
        return defaultValue;
    }

    public static int getProperty(String configurationFileName, String keyName, int defaultValue) {
        Properties propertiesFile = PropertiesReader.configurations.get(configurationFileName + ".properties");
        if(propertiesFile != null) {
            String value = propertiesFile.getProperty(keyName);
            // do we have that value
            if (value != null && !value.isEmpty()) {
                return PropertiesReader.parseInteger(value);
            }
            Logger.error(String.format("[%s %s] was not found in the configuration file!", configurationFileName, keyName));
            return defaultValue;
        }
        Logger.error(String.format("[%s] configuration file was not found!", configurationFileName));
        return defaultValue;
    }

    public static boolean isConfigurationLoaded(String configurationFileName) {
        return PropertiesReader.configurations.get(configurationFileName + ".properties") != null;
    }

    private static void loadConfigurationFiles() {
        String resourcesPath = "../resources/config";

        // make sure we have service.properties
        File serviceFile = new File(resourcesPath + "/service.properties");
        PropertiesReader.loadFile(serviceFile);

        // load everything else
        File f = new File(resourcesPath); // current directory
        File[] files = f.listFiles();
        for (File file : files) {
            if (!file.isDirectory() && !file.getName().equals("service.properties")) {
                PropertiesReader.loadFile(file);
            }
        }
    }

    private static void loadFile(File configurationFile) {
        Logger.info("Loading configuration file: " + configurationFile.getName());
        Properties prop = new Properties();
        InputStream input = null;
        String fileName = configurationFile.getName();

        try {
            input = new FileInputStream(configurationFile);
            // load a properties file
            prop.load(input);
            PropertiesReader.configurations.put(fileName, prop);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static Double parseDouble(String value) {
        try{
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static Integer parseInteger(String value) {
        try{
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}