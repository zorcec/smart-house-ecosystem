package helpers.spark;

import spark.Spark;
import spark.Route;
import java.util.concurrent.Callable;

import helpers.configurations.*;
import helpers.logging.*;

public class WebServer {

    private static int port;

    public static int getPort() {
        return port;
    }

    public static void init() {
        port = PropertiesReader.getProperty("service", "PORT", 80);
        Spark.port(port);
        Logger.info(String.format("Web server initialized on port %s!", port));
    }

    public static void registerGet(String routeName, Route route) {
        Spark.get(routeName, route);
        Logger.info("Registering new route to the Spark web server: " + routeName);
    }

}