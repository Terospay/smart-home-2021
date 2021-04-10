import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.home_component.Light;
import ru.sbt.mipt.oop.home_component.*;
import ru.sbt.mipt.oop.home_component.SmartHome;
import ru.sbt.mipt.oop.reader.JsonSmartHomeReader;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LightHandlerTest {
    SmartHome smartHome;

    @Before
    public void init() {
        Light light = new Light("1", true);
        Door door = new Door("2", true);
        Room room = new Room(new ArrayList<Light>(Arrays.asList(light)), new ArrayList<Door>(Arrays.asList(door)), "hall");
        smartHome = new SmartHome(new ArrayList<Room>(Arrays.asList(room)));
    }

    @Test
    public void TurnOffOneLight() throws IOException {
        Room room = smartHome.getRooms().iterator().next();
        Light light = room.getLights().iterator().next();

        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, "1");
        new EventHandler(smartHome).handle(event);
        boolean expectedState = false;
        boolean actualState = light.isOn();
        assertEquals(expectedState, actualState);
    }

    @Test
    public void handleSameCommand() throws IOException {
        Room room = smartHome.getRooms().iterator().next();
        Light light = room.getLights().iterator().next();

        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, "1");
        new EventHandler(smartHome).handle(event);
        new EventHandler(smartHome).handle(event);
        boolean expectedState = false;
        boolean actualState = light.isOn();
        assertEquals(expectedState, actualState);
    }
}
