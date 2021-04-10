package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.home_component.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class EventHandler implements Handler{
    private final SmartHome smartHome;

    public EventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }


    public void handle(SensorEvent event) {
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
            new LightHandler(smartHome).handle(event);
        }
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            new DoorHandler(smartHome).handle(event);
        }
     }
}
