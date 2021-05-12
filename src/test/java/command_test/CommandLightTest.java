package command_test;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.home_component.Door;
import ru.sbt.mipt.oop.home_component.Light;
import ru.sbt.mipt.oop.home_component.Room;
import ru.sbt.mipt.oop.home_component.SmartHome;
import ru.sbt.mipt.oop.remote_control.command.CommandTurnOffAllLights;
import ru.sbt.mipt.oop.remote_control.command.CommandTurnOnAllLights;
import ru.sbt.mipt.oop.remote_control.command.CommandTurnOnHallLight;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CommandLightTest {
    SmartHome smartHome;
    Light hallLight;
    Light otherLight;
    Light anotherLight;

    @Before
    public void init() {
        hallLight = new Light("1", false);
        otherLight = new Light("2", true);
        anotherLight = new Light("3", false);
        Room hallRoom = new Room(new ArrayList<Light>(Arrays.asList(hallLight)), new ArrayList<Door>(), "hall");
        Room otherRoom = new Room(new ArrayList<Light>(Arrays.asList(otherLight, anotherLight)), new ArrayList<Door>(), "other");
        smartHome = new SmartHome(new ArrayList<Room>(Arrays.asList(hallRoom, otherRoom)));
    }

    @Test
    public void CommandTurnOnHallLights() {
        new CommandTurnOnHallLight(smartHome).execute();
        assertEquals(true, hallLight.isOn());
    }

    @Test
    public void CommandTurnOnAllLights() {
        new CommandTurnOnAllLights(smartHome).execute();
        boolean allLightsON = hallLight.isOn() && otherLight.isOn() && anotherLight.isOn();
        assertEquals(true, allLightsON);
    }

    @Test
    public void CommandTurnOffAllLights() {
        new CommandTurnOffAllLights(smartHome).execute();
        boolean allLightsOff = !hallLight.isOn() && !otherLight.isOn() && !anotherLight.isOn();
        assertEquals(true, allLightsOff);
    }
}
