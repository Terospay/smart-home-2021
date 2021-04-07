package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.reader.JsonSmartHomeReader;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHome smartHome = new JsonSmartHomeReader().read("smart-home-1.js");
        // начинаем цикл обработки событий
        SensorEvent event = RandomEventGenerator.getNextSensorEvent();
        while (event != null) {
            EventHandler eventHandler = new EventHandler(smartHome);
            eventHandler.handleEvent(event);
            event = RandomEventGenerator.getNextSensorEvent();
        }
    }

}
