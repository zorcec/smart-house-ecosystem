package services.centralTTS;

import helpers.configurations.*;
import helpers.logging.*;
import helpers.spark.*;
import helpers.data.fs.*;

import main.java.services.centralTTS.models.*;

public class Service {

    public Service() {
        PersistentFileSystem.define("voices", VoicesData.class);
    }

    public void init() {
        try {
            WebServer.registerGet("transform", (req, res) -> {
                String text = req.queryParams("text");
                return text;
            });
        } catch (Exception exception) {
            Logger.exception(exception);
        }
    }

}