package ru.sbt.mipt.oop.adapter;

import com.coolcompany.events.CCSensorEvent;
import com.coolcompany.events.EventHandler;
import ru.sbt.mipt.oop.event.EventType;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.handler.Handler;

import java.util.Map;

public class EventHandlerAdapter implements EventHandler {
    private final Handler handler;
    private final Map<String, EventType> stringToSensorEvent;

    public EventHandlerAdapter(Handler handler, Map<String, EventType> stringToSensorEvent) {
        this.handler = handler;
        this.stringToSensorEvent = stringToSensorEvent;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        if(event != null) {
            handler.handle(ccSensorEventToSensorEvent(event));
        }
    }

    public SensorEvent ccSensorEventToSensorEvent(CCSensorEvent ccSensorEvent) {
        return new SensorEvent(stringToSensorEvent.get(ccSensorEvent.getEventType()), ccSensorEvent.getObjectId());
    }
}
