package ru.sbt.mipt.oop.handler;

import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventGenerator;
import ru.sbt.mipt.oop.handler.EventHandler;
import ru.sbt.mipt.oop.home_component.SmartHome;

public class SequentialEventHandler {
    private final SmartHome smartHome;
    private final SensorEventGenerator sensorEventGenerator;

    public SequentialEventHandler(SmartHome smartHome, SensorEventGenerator sensorEventGenerator) {
        this.sensorEventGenerator = sensorEventGenerator;
        this.smartHome = smartHome;
    }

//    public void start() {
//        SensorEvent event = sensorEventGenerator.getNextSensorEvent();
//        while (event != null) {
//            EventHandler eventHandler = new EventHandler(smartHome);
//            eventHandler.handleEvent(event);
//            event = sensorEventGenerator.getNextSensorEvent();
//        }
//    }

    public void start() {
        SensorEvent event = sensorEventGenerator.getNextSensorEvent();
        while (event != null) {
            new EventHandler(smartHome).handle(event);
            event = sensorEventGenerator.getNextSensorEvent();
        }
    }


}
