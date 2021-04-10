package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.home_component.SmartHome;
import ru.sbt.mipt.oop.reader.JsonSmartHomeReader;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHome smartHome = new JsonSmartHomeReader().read("smart-home-1.js");
        // начинаем цикл обработки событий
        SensorEventGenerator sensorEventGenerator = new RandomSensorEventGenerator();
        SequentialEventHandler sequentialEventHandler = new SequentialEventHandler(smartHome, sensorEventGenerator);
        sequentialEventHandler.start();

    }

}
