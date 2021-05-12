package ru.sbt.mipt.oop.remote_control.command;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.home_component.SmartHome;
import ru.sbt.mipt.oop.home_component.alarm.Alarm;

public class CommandAlarmAlert implements Command{
    private final SmartHome smartHome;

    public CommandAlarmAlert(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        Action action = (object) -> {
            if (object instanceof Alarm) {
                Alarm alarm = (Alarm) object;
                alarm.alert();
            }
        };
        smartHome.execute(action);
    }
}
