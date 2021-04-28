package ru.sbt.mipt.oop.handler;

import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.event.EventType;
import ru.sbt.mipt.oop.home_component.SmartHome;
import ru.sbt.mipt.oop.home_component.alarm.Alarm;

public class EventHandlerWithAlarmSafety implements Handler {
    private final SmartHome smartHome;
    private final Handler handler;

    public EventHandlerWithAlarmSafety(SmartHome smartHome, Handler handler) {
        this.smartHome = smartHome;
        this.handler = handler;
    }


    public void handle(Event event) {
        if (event.getType() != EventType.ALARM_ACTIVATE && event.getType() != EventType.ALARM_DEACTIVATE) {
            Alarm alarm = smartHome.getAlarm();
            if (alarm != null && !alarm.isInactive()) {
                alarm.alert();
            } else {
                handler.handle(event);
            }
        } else {
            handler.handle(event);
        }
    }

}
