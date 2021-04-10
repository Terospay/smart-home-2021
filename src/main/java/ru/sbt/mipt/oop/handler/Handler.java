package ru.sbt.mipt.oop.handler;

import ru.sbt.mipt.oop.event.SensorEvent;

public interface Handler {
    void handle(SensorEvent event);
}
