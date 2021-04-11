import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.handler.EventHandler;
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

    @Before
    public void init() {
        Light light1 = new Light("1", true);
        Light light2 = new Light("2", true);
        Door door = new Door("3", true);
        Room room = new Room(new ArrayList<Light>(Arrays.asList(light1, light2)), new ArrayList<Door>(Arrays.asList(door)), "hall");
        smartHome = new SmartHome(new ArrayList<Room>(Arrays.asList(room)));
    }

    @Test
    public void CloseHallDoorLightsCheck() {
        Room room = smartHome.getRooms().iterator().next();
        Door door = room.getDoors().iterator().next();
        Light light1= room.getLights().iterator().next();
        Light light2= room.getLights().iterator().next();

        SensorEvent event = new SensorEvent(EventType.DOOR_CLOSED, "3");
        new EventHandler(smartHome).handle(event);
        boolean expectedState = false;
        boolean actualState1 = light1.isOn();
        boolean actualState2= light2.isOn();
        assertEquals(expectedState, actualState1);
        assertEquals(expectedState, actualState2);
    }
}
