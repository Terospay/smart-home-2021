package ru.sbt.mipt.oop;

import java.io.IOException;

public interface SmartHomeReader {
    SmartHome read(String filename) throws IOException;
}
