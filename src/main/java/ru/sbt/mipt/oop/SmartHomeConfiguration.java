package ru.sbt.mipt.oop;

import com.coolcompany.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.adapter.EventHandlerAdapter;
import ru.sbt.mipt.oop.handler.EventHandlerWithAlarmSafety;
import ru.sbt.mipt.oop.handler.SensorEventHandler;
import ru.sbt.mipt.oop.home_component.SmartHome;
import ru.sbt.mipt.oop.reader.JsonSmartHomeReader;

@Configuration
public class SmartHomeConfiguration {
    @Bean
    SmartHome createSmartHome() {
        return new JsonSmartHomeReader().read("smart-home-1.js");
    }

    @Bean
    SensorEventHandler createSensorEventHandler(SmartHome smartHome) {
        return new SensorEventHandler(smartHome);
    }

    @Bean
    EventHandlerWithAlarmSafety eventHandlerWithAlarmSafety(SmartHome smartHome, SensorEventHandler handler) {
        return new EventHandlerWithAlarmSafety(smartHome, handler);
    }

    @Bean
    EventHandlerAdapter eventHandlerAdapter(EventHandlerWithAlarmSafety eventHandlerWithAlarmSafety) {
        return new EventHandlerAdapter(eventHandlerWithAlarmSafety);
    }

    @Bean
    SensorEventsManager sensorEventsManager(EventHandlerAdapter eventHandlerAdapter) {
        SensorEventsManager sensorEventsManager= new SensorEventsManager();
        sensorEventsManager.registerEventHandler(eventHandlerAdapter);
        return sensorEventsManager;
    }
}
