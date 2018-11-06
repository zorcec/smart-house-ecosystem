package helpers.data.fs;

import helpers.configurations.*;
import helpers.logging.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.*;

import com.sun.imageio.plugins.common.InputStreamAdapter;
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

    public static void define(Class definition) {
        try {
            String content = "";
            String name = definition.getSimpleName();
            Logger.info(String.format("Reading new data stream: %s!", name));
            File file = new File(getFileNamePath(name));
            if(file.exists()) {
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
                int data = inputStreamReader.read();
                while(data != -1){
                    content += (char)data;
                    data = inputStreamReader.read();
                }
                inputStreamReader.close();
            } else {
                Logger.info(String.format("File doesn't exist: %s!", file.getName()));
            }
        } catch (Exception exception) {
            Logger.exception(exception);
        }
    }

    public static void save(Object data) {
        try {
            String name = data.getClass().getSimpleName();
            String content = xstream.toXML(data);

            File file = new File(getFileNamePath(name));
            if(!file.exists()) {
                Logger.info(String.format("File doesn't exist, creating new one: %s!", file.getName()));
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            outputStreamWriter.write(content);
            outputStreamWriter.close();
        } catch (Exception exception) {
            Logger.exception(exception);
        }
    }

    private static String getFileNamePath(String name) {
        return dataPath + "/" + name + ".xml";
    }
/*
    private Object castToType(Class type) {
        return type.cast(xstream.fromXML(content));
    }
*/
}