package ru.sbt.mipt.oop.handler;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.home_component.Light;
import ru.sbt.mipt.oop.home_component.SmartHome;

import static ru.sbt.mipt.oop.event.SensorEventType.*;

public class LightHandler implements Handler {
    private final SmartHome smartHome;

    public LightHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(SensorEvent event) {
        Action action = (object) -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                if (event.getObjectId().equals(light.getId())) {
                    if (event.getType() == LIGHT_ON) {
                        lightOn(light);
                    } else if (event.getType() == LIGHT_OFF) {
                        lightOff(light);
                    }
                }
            }
        };
        smartHome.execute(action);
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
