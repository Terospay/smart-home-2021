package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class EventHandler {
    private final SmartHome smartHome;

    public EventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }


    public void handle(SensorEvent event) {
        System.out.println("Got event: " + event);
        new LightHandler(smartHome).handle(event);
        new DoorHandler(smartHome).handle(event);

    }
}
