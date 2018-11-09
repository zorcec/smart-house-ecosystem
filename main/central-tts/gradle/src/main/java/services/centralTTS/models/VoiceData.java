package main.java.services.centralTTS.models;

public class VoiceData {

    public VoiceService voiceService;
    public String text;
    public String voice;
    public String name;

    public enum VoiceService {
        amazonPolly
    }
}
