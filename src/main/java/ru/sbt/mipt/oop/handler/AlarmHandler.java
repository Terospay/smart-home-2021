package ru.sbt.mipt.oop.handler;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.event.AlarmEvent;
import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.home_component.SmartHome;
import ru.sbt.mipt.oop.home_component.alarm.Alarm;

import static ru.sbt.mipt.oop.event.EventType.*;

public class AlarmHandler implements Handler{
    final SmartHome smartHome;

    public AlarmHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(Event event) {
        Action action = (object) -> {
            AlarmEvent alarmEvent = (AlarmEvent) event;
            if (object instanceof Alarm) {
                Alarm alarm = (Alarm) object;
                if (alarmEvent.getType() == ALARM_ACTIVATE) {
                    alarm.activate(alarmEvent.getCode());
                }
                if(alarmEvent.getType() == ALARM_DEACTIVATE) {
                    alarm.deactivate(alarmEvent.getCode());
                }
            }
        };
        smartHome.execute(action);
    }
}
