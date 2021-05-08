package ru.sbt.mipt.oop.config;

import com.coolcompany.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.adapter.EventHandlerAdapter;
import ru.sbt.mipt.oop.event.EventType;
import ru.sbt.mipt.oop.handler.*;
import ru.sbt.mipt.oop.home_component.SmartHome;
import ru.sbt.mipt.oop.reader.JsonSmartHomeReader;

import java.util.List;
import java.util.Map;

@Configuration
public class SmartHomeConfiguration {
    @Bean
    SmartHome createSmartHome() {
        return new JsonSmartHomeReader().read("smart-home-1.js");
    }

    @Bean
    Handler createDoorHandler(SmartHome smartHome) {
        return new EventHandlerWithAlarmSafety(smartHome, new DoorHandler(smartHome));
    }

    @Bean
    Handler createLightHandler(SmartHome smartHome) {
        return new EventHandlerWithAlarmSafety(smartHome, new LightHandler(smartHome));
    }

    @Bean
    Handler createAlarmHandler(SmartHome smartHome) {
        return new AlarmHandler(smartHome);
    }

    @Bean
    SensorEventHandler createSensorEventHandler(SmartHome smartHome, List<Handler> handlers) {
        SensorEventHandler sensorEventHandler = new SensorEventHandler(smartHome);
        sensorEventHandler.registrationHandlers(handlers);
        return sensorEventHandler;
    }

    @Bean
    Map<String, EventType> stringToSensorEvent() {
        return Map.of("LightIsOn", ru.sbt.mipt.oop.event.EventType.LIGHT_ON,
                "LightIsOff", ru.sbt.mipt.oop.event.EventType.LIGHT_OFF,
                "DoorIsOpen", ru.sbt.mipt.oop.event.EventType.DOOR_OPEN,
                "DoorIsUnlocked", ru.sbt.mipt.oop.event.EventType.DOOR_OPEN,
                "DoorIsClosed", ru.sbt.mipt.oop.event.EventType.DOOR_CLOSED,
                "DoorIsLocked", ru.sbt.mipt.oop.event.EventType.DOOR_CLOSED);
    }

    @Bean
    EventHandlerAdapter eventHandlerAdapter(SensorEventHandler sensorEventHandler, Map<String, EventType> stringToSensorEvent) {
        return new EventHandlerAdapter(sensorEventHandler, stringToSensorEvent);
    }

    @Bean
    SensorEventsManager sensorEventsManager(EventHandlerAdapter eventHandlerAdapter) {
        SensorEventsManager sensorEventsManager= new SensorEventsManager();
        sensorEventsManager.registerEventHandler(eventHandlerAdapter);
        return sensorEventsManager;
    }
}
