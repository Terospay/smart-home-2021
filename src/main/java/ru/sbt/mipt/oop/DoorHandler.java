package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.base_device.Door;
import ru.sbt.mipt.oop.base_device.Light;

import static ru.sbt.mipt.oop.SensorEventType.*;

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
        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
        if (room.getName().equals("hall")) {
            hallDoorClosedAction();
        }
    }

    private void hallDoorClosedAction() {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                command.report();
            }
        }
    }
}
