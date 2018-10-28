package services.centraltts;

import helpers.configurations.*;
import helpers.logging.*;

public class Main {

    public static void main(String[] args) throws Exception {
        try {
            Logger.init();
            PropertiesReader.init();
            Logger.info("Service is started!");

            // activate services
            Service service = new Service();

        } catch (Exception exception) {
            Logger.exception(exception);
        }
    }

}