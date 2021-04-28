package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Arrays;

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
        EventHandler eventHandler = new EventHandler(smartHome);
        eventHandler.registrationDeviceHandlers(new ArrayList<DeviceHandler>(Arrays.asList(new DoorHandler(smartHome), new LightHandler(smartHome))));
        SensorEvent event = sensorEventGenerator.getNextSensorEvent();
        while (event != null) {
            eventHandler.handle(event);
            event = sensorEventGenerator.getNextSensorEvent();
        }
    }


}