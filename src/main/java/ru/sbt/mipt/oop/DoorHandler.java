package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.base_device.Door;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorHandler implements DeviceHandler {
    private final SmartHome smartHome;

    public DoorHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(SensorEvent event) {
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    if (door.getId().equals(event.getObjectId())) {
                        if (event.getType() == DOOR_OPEN) {
                            doorOpen(room, door);
                        } else {
                            doorClose(room, door);
                        }
                    }
                }
            }
        }
    }

    private void doorOpen(Room room, Door door) {
        door.setOpen(true);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
    }

    private void doorClose(Room room, Door door) {
        door.setOpen(false);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
    }
}