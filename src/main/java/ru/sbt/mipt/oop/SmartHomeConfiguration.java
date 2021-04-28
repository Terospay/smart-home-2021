package ru.sbt.mipt.oop;

import com.coolcompany.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.adapter.EventHandlerAdapter;
import ru.sbt.mipt.oop.event.EventType;
import ru.sbt.mipt.oop.handler.*;
import ru.sbt.mipt.oop.home_component.SmartHome;
import ru.sbt.mipt.oop.reader.JsonSmartHomeReader;

import java.util.Arrays;
import java.util.Map;

@Configuration
public class SmartHomeConfiguration {
    @Bean
    SmartHome createSmartHome() {
        return new JsonSmartHomeReader().read("smart-home-1.js");
    }

    @Bean
    DoorHandler createDoorHandler(SmartHome smartHome) {
        return new DoorHandler(smartHome);
    }

    @Bean
    LightHandler createLightHandler(SmartHome smartHome) {
        return new LightHandler(smartHome);
    }

    @Bean
    SensorEventHandler createSensorEventHandler(SmartHome smartHome, LightHandler lightHandler, DoorHandler doorHandler) {
        SensorEventHandler sensorEventHandler = new SensorEventHandler(smartHome);
        EventHandlerWithAlarmSafety lightHandlerWithAlarmSafety = new EventHandlerWithAlarmSafety(smartHome, lightHandler);
        EventHandlerWithAlarmSafety doorHandlerWithAlarmSafety = new EventHandlerWithAlarmSafety(smartHome, doorHandler);
        sensorEventHandler.registrationHandlers(Arrays.asList(lightHandlerWithAlarmSafety, doorHandlerWithAlarmSafety, new AlarmHandler(smartHome)));
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
        return new EventHandlerAdapter(sensorEventHandler, stringToSensorEvent());
    }

    @Bean
    SensorEventsManager sensorEventsManager(EventHandlerAdapter eventHandlerAdapter) {
        SensorEventsManager sensorEventsManager= new SensorEventsManager();
        sensorEventsManager.registerEventHandler(eventHandlerAdapter);
        return sensorEventsManager;
    }
}
