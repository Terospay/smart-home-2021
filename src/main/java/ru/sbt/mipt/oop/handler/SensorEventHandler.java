package ru.sbt.mipt.oop.handler;

import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.home_component.SmartHome;

import java.util.Collection;

public class SensorEventHandler implements Handler {
    private final SmartHome smartHome;
    private Collection<Handler> handlers;

    public SensorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void registrationHandlers(Collection<Handler> handlers) {
        this.handlers = handlers;
    }


    public void handle(Event event) {
        System.out.println("Got event: " + event);
        for (Handler handler : handlers) {
            handler.handle(event);
        }
     }
}
