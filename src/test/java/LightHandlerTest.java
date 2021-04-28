import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.EventType;
import ru.sbt.mipt.oop.handler.SensorEventHandler;
import ru.sbt.mipt.oop.home_component.Light;
import ru.sbt.mipt.oop.home_component.*;
import ru.sbt.mipt.oop.home_component.SmartHome;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LightHandlerTest {
    SmartHome smartHome;
    Light testingLight;

    @Before
    public void init() {
        testingLight = new Light("1", true);
        Door door = new Door("2", true);
        Room room = new Room(new ArrayList<Light>(Arrays.asList(testingLight)), new ArrayList<Door>(Arrays.asList(door)), "hall");
        smartHome = new SmartHome(new ArrayList<Room>(Arrays.asList(room)));
    }

    @Test
    public void TurnOffOneLight() throws IOException {
        SensorEvent event = new SensorEvent(EventType.LIGHT_OFF, "1");
        new SensorEventHandler(smartHome).handle(event);
        boolean expectedState = false;
        boolean actualState = testingLight.isOn();
        assertEquals(expectedState, actualState);
    }

    @Test
    public void handleSameCommand() throws IOException {
        SensorEvent event = new SensorEvent(EventType.LIGHT_OFF, "1");
        new SensorEventHandler(smartHome).handle(event);
        new SensorEventHandler(smartHome).handle(event);
        boolean expectedState = false;
        boolean actualState = testingLight.isOn();
        assertEquals(expectedState, actualState);
    }
}