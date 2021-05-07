package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.base_device.Door;
import ru.sbt.mipt.oop.base_device.Light;

public class HallDoorHandler implements DeviceHandler {
    private final SmartHome smartHome;

    public HallDoorHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(SensorEvent event) {
        boolean isHallDoor = checkIsHallDoor(event);
        if (isHallDoor) {
            for (Room homeRoom : smartHome.getRooms()) {
                for (Light light : homeRoom.getLights()) {
                    light.setOn(false);
                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                    command.report();
                }
            }
        }
    }

    private boolean checkIsHallDoor(SensorEvent event) {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Door door : homeRoom.getDoors()) {
                if (event.getObjectId().equals(door.getId()) && homeRoom.getName().equals("hall")) {
                    return true;
                }
            }
        }
        return false;
    }


}
