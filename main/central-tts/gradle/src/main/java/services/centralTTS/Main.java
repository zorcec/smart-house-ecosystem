package services.centralTTS;

import helpers.configurations.*;
import helpers.logging.*;
import helpers.spark.*;
import helpers.data.fs.*;

public class Main {

    public static void main(String[] args) throws Exception {
        try {
            Logger.init();
            PropertiesReader.init();
            WebServer.init();
            PersistentFileSystem.init();

            // activate services
            Logger.info("Service is starting!");
            new Service();

        } catch (Exception exception) {
            Logger.exception(exception);
        }
    }

}