package services.centraltts;

import helpers.configurations.*;
import helpers.logging.*;
import helpers.spark.*;

public class Service {

    public Service() {
        try {
            Logger.info("Something stupid");
        } catch (Exception exception) {
            Logger.exception(exception);
        }
    }

}