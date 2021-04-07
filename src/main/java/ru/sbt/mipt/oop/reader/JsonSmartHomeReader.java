package ru.sbt.mipt.oop.reader;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.reader.SmartHomeReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonSmartHomeReader implements SmartHomeReader {

    @Override
    public SmartHome read(String filename) throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(filename)));
        SmartHome smartHome = gson.fromJson(json, SmartHome.class);
        return smartHome;
    }
}
