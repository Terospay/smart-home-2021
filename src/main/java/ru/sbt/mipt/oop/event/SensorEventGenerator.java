package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.event.SensorEvent;

public interface SensorEventGenerator {
    public SensorEvent getNextSensorEvent();
}
