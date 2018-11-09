package services.centralTTS;

import java.io.File;
import java.io.InputStream;
import javax.servlet.ServletOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import org.apache.commons.io.FileUtils;

import helpers.configurations.*;
import helpers.logging.*;
import helpers.spark.*;
import helpers.data.fs.*;

import main.java.services.centralTTS.clients.AmazonPolly;
import main.java.services.centralTTS.models.*;

public class Service {

    private VoicesData voicesData;
    private String dataPath;
    private AmazonPolly voiceService;

    public Service() {
        try {
            this.dataPath = PropertiesReader.getProperty("service", "AUDIO_DATA", "");
            this.voicesData = (VoicesData)PersistentFileSystem.define(VoicesData.class);

            voiceService = new AmazonPolly();
            voiceService.createService();

            this.getAudio("test");
            
            WebServer.registerGet("transform", (req, res) -> {
                String text = req.queryParams("text");
                Logger.info("New request: " + text);

                // send audio
                res.type("audio/mp3");
                ServletOutputStream out = res.raw().getOutputStream();
                InputStream audio = this.getAudio(text);
                int data = audio.read();
                while (data >= 0) {
                    out.write((char) data);
                    data = audio.read();
                }
                out.close();

                return 200;
            });

        } catch (Exception exception) {
            Logger.exception(exception);
        }
    }

    private InputStream getAudio(String text) {
        InputStream audio = null;

        // try to find existing
        for(VoiceData voiceData : this.voicesData.items) {
            if(voiceData.text.equals(text)) {

            }
        }

        // synthesize if needed
        if(audio == null) {
            try {
                // create new mp3 file
                File file = new File(getFileNamePath("test"));
                file.getParentFile().mkdirs();
                file.createNewFile();
                // write audio to it
                audio = voiceService.synthesize(text);
                FileUtils.copyInputStreamToFile(audio, file);
            } catch (Exception exception) {
                Logger.exception(exception);
            }
        }

        return audio;
    }

    private String getFileNamePath(String name) {
        return dataPath + "/" + name + ".mp3";
    }

}