package ru.sbt.mipt.oop.handler;

import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.home_component.SmartHome;

import static ru.sbt.mipt.oop.event.SensorEventType.*;

public class EventHandler implements Handler {
    private final SmartHome smartHome;

    public EventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }


    public void handle(SensorEvent event) {
        System.out.println("Got event: " + event);
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            new LightHandler(smartHome).handle(event);
        }
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            new DoorHandler(smartHome).handle(event);
        }
     }
}
