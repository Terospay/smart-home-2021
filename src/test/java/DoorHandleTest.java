import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.EventType;
import ru.sbt.mipt.oop.handler.EventHandler;
import ru.sbt.mipt.oop.home_component.Light;
import ru.sbt.mipt.oop.home_component.Room;
import ru.sbt.mipt.oop.home_component.SmartHome;


import ru.sbt.mipt.oop.home_component.Door;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class DoorHandleTest {

    SmartHome smartHome;

    @Before
    public void init() {
        Light light = new Light("1", true);
        Door door = new Door("2", true);
        Room room = new Room(new ArrayList<Light>(Arrays.asList(light)), new ArrayList<Door>(Arrays.asList(door)), "hall");
        smartHome = new SmartHome(new ArrayList<Room>(Arrays.asList(room)));
    }

    @Test
    public void simpleDoorClose() throws IOException {
        Room room = smartHome.getRooms().iterator().next();
        Door door = room.getDoors().iterator().next();

        SensorEvent event = new SensorEvent(EventType.DOOR_CLOSED, "2");
        new EventHandler(smartHome).handle(event);
        boolean expectedState = false;
        boolean actualState =door.isOpen();
        assertEquals(expectedState, actualState);

    }

    @Test
    public void handleSameCommand() throws IOException {
        Room room = smartHome.getRooms().iterator().next();
        Door door = room.getDoors().iterator().next();

        SensorEvent event = new SensorEvent(EventType.DOOR_CLOSED, "2");
        new EventHandler(smartHome).handle(event);
        new EventHandler(smartHome).handle(event);
        boolean expectedState = false;
        boolean actualState =door.isOpen();
        assertEquals(expectedState, actualState);
    }
}


