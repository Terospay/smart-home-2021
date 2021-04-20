package ru.sbt.mipt.oop.reader;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.home_component.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonSmartHomeReader implements SmartHomeReader {

    @Override
    public SmartHome read(String filename) {
        Gson gson = new Gson();
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gson.fromJson(json, SmartHome.class);
    }
}
