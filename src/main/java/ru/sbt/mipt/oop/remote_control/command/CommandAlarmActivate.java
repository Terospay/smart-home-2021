package ru.sbt.mipt.oop.remote_control.command;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.home_component.SmartHome;
import ru.sbt.mipt.oop.home_component.alarm.Alarm;

public class CommandAlarmActivate implements Command{
    private final SmartHome smartHome;

    public CommandAlarmActivate(SmartHome smartHome, String code) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        Action action = (object) -> {
            if (object instanceof Alarm) {
                Alarm alarm = (Alarm) object;
                alarm.activate("admin");
            }
        };
        smartHome.execute(action);
    }
}
