package ru.sbt.mipt.oop;

import com.coolcompany.events.SensorEventsManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.config.SmartHomeConfiguration;

public class Application {

    public static void main(String[] args) {
        // считываем состояние дома из файла
//        SmartHome smartHome = new JsonSmartHomeReader().read("smart-home-1.js");
        // начинаем цикл обработки событий
//        SensorEventGenerator sensorEventGenerator = new RandomSensorEventGenerator();
//        SequentialEventHandler sequentialEventHandler = new SequentialEventHandler(smartHome, sensorEventGenerator);
//        sequentialEventHandler.start();
        ApplicationContext context = new AnnotationConfigApplicationContext(SmartHomeConfiguration.class);
        SensorEventsManager sensorEventsManager = context.getBean(SensorEventsManager.class);
        sensorEventsManager.start();
    }

}
