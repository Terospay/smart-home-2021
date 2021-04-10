package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.home_component.Door;
import ru.sbt.mipt.oop.home_component.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class DoorHandler implements Handler {
    private final SmartHome smartHome;

    public DoorHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(SensorEvent event) {
        Action action = (object) -> {
            if (object instanceof Door) {
                Door door = (Door) object;
                if (event.getObjectId().equals(door.getId())) {
                    if (event.getType() == DOOR_OPEN) {
                        doorOpen(door);
                    } else if (event.getType() == DOOR_CLOSED) {
                        doorClose(door);
                        new HallDoorHandler(smartHome).handle(event);
                    }
                }
            }
        };
        smartHome.execute(action);
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
