package ru.sbt.mipt.oop.handler;

import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.home_component.SmartHome;
import ru.sbt.mipt.oop.home_component.alarm.ActiveAlarmState;
import ru.sbt.mipt.oop.home_component.alarm.Alarm;
import ru.sbt.mipt.oop.home_component.alarm.AlarmState;
import ru.sbt.mipt.oop.home_component.alarm.AlertAlarmState;

public class EventHandlerWithAlarmSafety {
    private final SmartHome smartHome;

    public EventHandlerWithAlarmSafety(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void handle(Handler handler, Event event) {
        Alarm alarm = smartHome.getAlarm();
        AlarmState alarmState = alarm.getState();
        if (alarmState.getClass() == ActiveAlarmState.class || alarmState.getClass() == AlertAlarmState.class) {
            alarm.alert();
        } else {
            handler.handle(event);
        }
    }
}
