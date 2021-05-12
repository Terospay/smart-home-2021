import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.handler.SensorEventHandler;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.EventType;
import ru.sbt.mipt.oop.home_component.Door;
import ru.sbt.mipt.oop.home_component.Light;
import ru.sbt.mipt.oop.home_component.Room;
import ru.sbt.mipt.oop.home_component.SmartHome;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class HallDoorHandleTest {
    SmartHome smartHome;
    Light testingLight1;
    Light testingLight2;

    @Before
    public void init() {
        testingLight1 = new Light("1", true);
        testingLight2 = new Light("2", true);
        Door door = new Door("3", true);
        Room room = new Room(new ArrayList<Light>(Arrays.asList(testingLight1, testingLight2)), new ArrayList<Door>(Arrays.asList(door)), "hall");
        smartHome = new SmartHome(new ArrayList<Room>(Arrays.asList(room)));
    }

    @Test
    public void CloseHallDoorLightsCheck() {
        SensorEvent event = new SensorEvent(EventType.DOOR_CLOSED, "3");
        new SensorEventHandler(smartHome).handle(event);
        boolean expectedState = false;
        boolean actualState1 = testingLight1.isOn();
        boolean actualState2= testingLight2.isOn();
        assertEquals(expectedState, actualState1);
        assertEquals(expectedState, actualState2);
    }
}
