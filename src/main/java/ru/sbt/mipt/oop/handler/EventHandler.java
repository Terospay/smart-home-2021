package ru.sbt.mipt.oop.handler;

import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.home_component.SmartHome;

import static ru.sbt.mipt.oop.event.EventType.*;

public class EventHandler implements Handler {
    private final SmartHome smartHome;

    public EventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }


    public void handle(Event event) {
        System.out.println("Got event: " + event);
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            LightHandler lightHandler = new LightHandler(smartHome);
            new EventHandlerWithAlarmSafety(smartHome, lightHandler).handle(event);
        }
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            DoorHandler doorHandler = new DoorHandler(smartHome);
            new EventHandlerWithAlarmSafety(smartHome, doorHandler).handle(event);
        }
        if (event.getType() == ALARM_ACTIVATE || event.getType() == ALARM_DEACTIVATE) {
            new AlarmHandler(smartHome).handle(event);
        }
     }
}
