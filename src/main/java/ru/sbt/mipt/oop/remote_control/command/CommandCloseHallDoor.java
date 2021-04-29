package ru.sbt.mipt.oop.remote_control.command;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.home_component.Door;
import ru.sbt.mipt.oop.home_component.Room;
import ru.sbt.mipt.oop.home_component.SmartHome;

public class CommandCloseHallDoor implements Command {
    private final SmartHome smartHome;

    public CommandCloseHallDoor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        Action action = (object) -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("hall")) {
                    Action hallAction = getHallAction();
                    room.execute(hallAction);
                }
            }
        };
        smartHome.execute(action);
    }

    private Action getHallAction() {
        Action hallAction = (inner_object) -> {
            if (inner_object instanceof Door) {
                Door hallDoor = (Door) inner_object;
                hallDoor.setOpen(false);
            }
        };
        return hallAction;
    }
}
