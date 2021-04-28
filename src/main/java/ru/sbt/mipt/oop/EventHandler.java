package ru.sbt.mipt.oop;

import java.util.Collection;

public class EventHandler {
    private final SmartHome smartHome;
    private Collection<DeviceHandler> deviceHandlers;

    public EventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void registrationDeviceHandlers(Collection<DeviceHandler> deviceHandlers) {
        this.deviceHandlers = deviceHandlers;
    }


    public void handle(SensorEvent event) {
        System.out.println("Got event: " + event);
        for (DeviceHandler deviceHandler : deviceHandlers) {
            deviceHandler.handle(event);
        }
    }
}
