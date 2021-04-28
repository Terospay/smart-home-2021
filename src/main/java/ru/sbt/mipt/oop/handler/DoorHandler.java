package ru.sbt.mipt.oop.handler;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.home_component.Door;
import ru.sbt.mipt.oop.home_component.SmartHome;

import static ru.sbt.mipt.oop.event.EventType.*;

public class DoorHandler implements Handler {
    private final SmartHome smartHome;

    public DoorHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(Event event) {
        if(event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            Action action = (object) -> {
                if (object instanceof Door) {
                    SensorEvent sensorEvent = (SensorEvent) event;
                    Door door = (Door) object;
                    if (sensorEvent.getObjectId().equals(door.getId())) {
                        if (sensorEvent.getType() == DOOR_OPEN) {
                            doorOpen(door);
                        } else if (sensorEvent.getType() == DOOR_CLOSED) {
                            doorClose(door);
                            new HallDoorHandler(smartHome).handle(sensorEvent);
                        }
                    }
                }
            };
            smartHome.execute(action);
        }
    }

    private void doorOpen(Door door) {
        door.setOpen(true);
        System.out.println("Door " + door.getId() + " was opened.");
    }

    private void doorClose(Door door) {
        door.setOpen(false);
        System.out.println("Door " + door.getId() + " was closed.");
    }
}
