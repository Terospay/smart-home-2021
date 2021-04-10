package ru.sbt.mipt.oop.handler;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.home_component.Light;
import ru.sbt.mipt.oop.home_component.Room;
import ru.sbt.mipt.oop.home_component.SmartHome;

public class HallDoorHandler implements Handler {
    private final SmartHome smartHome;

    public HallDoorHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(SensorEvent event) {


        Action action = (object) -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("hall")) {
                    for (Light light : room.getLights()) {
                        SensorEvent sensorEvent = new SensorEvent(SensorEventType.LIGHT_OFF, light.getId());
                        new LightHandler(smartHome).handle(sensorEvent);
                    }
                }
            }
        };
        smartHome.execute(action);
    }
}
