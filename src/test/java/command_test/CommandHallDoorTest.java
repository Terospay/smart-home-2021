package command_test;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.home_component.Door;
import ru.sbt.mipt.oop.home_component.Light;
import ru.sbt.mipt.oop.home_component.Room;
import ru.sbt.mipt.oop.home_component.SmartHome;
import ru.sbt.mipt.oop.remote_control.command.CommandCloseHallDoor;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CommandHallDoorTest {
    SmartHome smartHome;
    Door hallDoor;

    @Before
    public void init() {
        hallDoor = new Door("1", true);
        Room hall = new Room(new ArrayList<Light>(), new ArrayList<Door>(Arrays.asList(hallDoor)), "hall");
        smartHome = new SmartHome(new ArrayList<Room>(Arrays.asList(hall)));
    }

    @Test
    public void CommandCloseHallDoor() {
        new CommandCloseHallDoor(smartHome).execute();
        assertEquals(false, hallDoor.isOpen());
    }
}
