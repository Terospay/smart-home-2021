package ru.sbt.mipt.oop.remote_control.command;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.home_component.Light;
import ru.sbt.mipt.oop.home_component.SmartHome;

public class CommandTurnOnAllLights implements Command{
    private final SmartHome smartHome;

    public CommandTurnOnAllLights(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        Action action = (object) -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                light.setOn(true);
            }
        };
        smartHome.execute(action);
    }
}
