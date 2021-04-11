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
//        System.out.println("Got event: " + event);
//        DeviceHandler deviceHandler = null;
//        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
//            // событие от источника света
//            deviceHandler = new LightHandler(smartHome);
//        } else if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
//            // событие от двери
//            deviceHandler = new DoorHandler(smartHome);
//        }
//        deviceHandler.handle(event);
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            LightHandler lightHandler = new LightHandler(smartHome);
            new EventHandlerWithAlarmSafety(smartHome).handle(lightHandler, event);
        }
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            DoorHandler doorHandler = new DoorHandler(smartHome);
            new EventHandlerWithAlarmSafety(smartHome).handle(doorHandler, event);
        }
        if (event.getType() == ALARM_ACTIVATE || event.getType() == ALARM_DEACTIVATE) {
            new AlarmHandler(smartHome).handle(event);
        }
     }
}
