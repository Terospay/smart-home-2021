package ru.sbt.mipt.oop.handler;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.home_component.Light;
import ru.sbt.mipt.oop.home_component.SmartHome;

import static ru.sbt.mipt.oop.event.EventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.event.EventType.LIGHT_ON;

public class LightHandler implements Handler {
    private final SmartHome smartHome;

    public LightHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(Event event) {
        if(event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            Action action = (object) -> {
                if (object instanceof Light) {
                    SensorEvent sensorEvent = (SensorEvent) event;
                    Light light = (Light) object;
                    if (sensorEvent.getObjectId().equals(light.getId())) {
                        if (sensorEvent.getType() == LIGHT_ON) {
                            lightOn(light);
                        } else if (sensorEvent.getType() == LIGHT_OFF) {
                            lightOff(light);
                        }
                    }
                }
            };
            smartHome.execute(action);
        }
    }

    private void lightOn(Light light) {
        light.setOn(true);
        System.out.println("Light " + light.getId() + " was turned on.");
    }

    private void lightOff(Light light) {
        light.setOn(false);
        System.out.println("Light " + light.getId() + " was turned off.");
    }
}
