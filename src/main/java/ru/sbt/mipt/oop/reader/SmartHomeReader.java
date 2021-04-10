package ru.sbt.mipt.oop.reader;

import ru.sbt.mipt.oop.home_component.SmartHome;

import java.io.IOException;

public interface SmartHomeReader {
    SmartHome read(String filename) throws IOException;
}
