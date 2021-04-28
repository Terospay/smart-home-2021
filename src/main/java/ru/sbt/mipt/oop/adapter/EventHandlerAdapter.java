package ru.sbt.mipt.oop.adapter;

import com.coolcompany.events.CCSensorEvent;
import com.coolcompany.events.EventHandler;
import ru.sbt.mipt.oop.event.EventType;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.handler.Handler;

import java.util.Map;

public class EventHandlerAdapter implements EventHandler {
    private final Handler handler;

    public EventHandlerAdapter(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        handler.handle(ccSensorEventToSensorEvent(event));
    }

    public SensorEvent ccSensorEventToSensorEvent(CCSensorEvent ccSensorEvent) {
        Map<String, EventType> stringToSensorEvent = Map.of("LightIsOn", ru.sbt.mipt.oop.event.EventType.LIGHT_ON,
                "LightIsOff", ru.sbt.mipt.oop.event.EventType.LIGHT_OFF,
                "DoorIsOpen", ru.sbt.mipt.oop.event.EventType.DOOR_OPEN,
                "DoorIsUnlocked", ru.sbt.mipt.oop.event.EventType.DOOR_OPEN,
                "DoorIsClosed", ru.sbt.mipt.oop.event.EventType.DOOR_CLOSED,
                "DoorIsLocked", ru.sbt.mipt.oop.event.EventType.DOOR_CLOSED);
        return new SensorEvent(stringToSensorEvent.get(ccSensorEvent.getEventType()), ccSensorEvent.getObjectId());
    }
}
