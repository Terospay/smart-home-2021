package ru.sbt.mipt.oop.event;

public class AlarmEvent implements Event{
    private final EventType eventType;
    private final String code;

    public AlarmEvent(EventType eventType, String code) {
        this.eventType = eventType;
        this.code = code;
    }

    @Override
    public EventType getType() {
        return eventType;
    }

    public String getCode() {
        return code;
    }
}
