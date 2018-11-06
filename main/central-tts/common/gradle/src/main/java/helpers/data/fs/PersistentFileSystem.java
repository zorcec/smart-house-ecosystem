package helpers.data.fs;

import helpers.configurations.*;
import helpers.logging.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class PersistentFileSystem {

    private static String dataPath = "";
    private static XStream xstream;

    public static void init() {
        dataPath = PropertiesReader.getProperty("service", "FS_DATA", "");
        xstream = new XStream(new StaxDriver());
        Logger.info(String.format("File system data initialized, directory: %s!", dataPath));
    }

    public static void define(String name, Class definition) {
        try {
            Logger.info(String.format("Reading new data stream: %s!", name));
            String content = "";
            File file = new File(dataPath + "/" + name + ".xml");
            if(file.exists()) {
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
                int data = inputStreamReader.read();
                while(data != -1){
                    content += (char)data;
                    data = inputStreamReader.read();
                }
                inputStreamReader.close();
            } else {
                Logger.info(String.format("File doesn't exist, creating empty: %s!", file.getName()));
                Logger.info(file.getAbsolutePath());
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (Exception exception) {
            Logger.exception(exception);
        }
    }
/*
    private Object castToType(Class type) {
        return type.cast(xstream.fromXML(content));
    }
*/
}