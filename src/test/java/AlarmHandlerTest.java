import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.event.AlarmEvent;
import ru.sbt.mipt.oop.event.EventType;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.handler.SensorEventHandler;
import ru.sbt.mipt.oop.home_component.Door;
import ru.sbt.mipt.oop.home_component.Light;
import ru.sbt.mipt.oop.home_component.Room;
import ru.sbt.mipt.oop.home_component.SmartHome;
import ru.sbt.mipt.oop.home_component.alarm.Alarm;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

public class AlarmHandlerTest {
    SmartHome smartHome;
    Light testLight;

    @Before
    public void init() {
        testLight = new Light("1", true);
        Door door = new Door("2", true);
        Room room = new Room(new ArrayList<Light>(Arrays.asList(testLight)), new ArrayList<Door>(Arrays.asList(door)), "hall");
        Alarm alarm = new Alarm();
        smartHome = new SmartHome(new ArrayList<Room>(Arrays.asList(room)), alarm);
    }

    @Test
    public void ActivateAlarmAndTryTurnOffLight() {
        alarmEventStart(EventType.ALARM_ACTIVATE, "123");
        sensorEventStart(EventType.LIGHT_OFF, "1");
        boolean expected = true;
        boolean actual = testLight.isOn();
        assertEquals(expected, actual);
    }

    @Test
    public void ActivateAndDeactivateAlarmAndTryTurnOffLight() {
        alarmEventStart(EventType.ALARM_ACTIVATE, "123");
        alarmEventStart(EventType.ALARM_DEACTIVATE, "123");
        sensorEventStart(EventType.LIGHT_OFF, "1");
        boolean expected = false;
        boolean actual = testLight.isOn();
        assertEquals(expected, actual);
    }

    @Test
    public void ActivateAndDeactivateAlarmWithWrongCodeAndTryTurnOffLight() {
        alarmEventStart(EventType.ALARM_ACTIVATE, "123");
        alarmEventStart(EventType.ALARM_DEACTIVATE, "1");
        sensorEventStart(EventType.LIGHT_OFF, "1");
        boolean expected = true;
        boolean actual = testLight.isOn();
        assertEquals(expected, actual);
    }

    public void alarmEventStart(EventType eventType, String code) {
        AlarmEvent event = new AlarmEvent(eventType, code);
        new SensorEventHandler(smartHome).handle(event);
    }

    public void sensorEventStart(EventType eventType, String objectId) {
        SensorEvent event = new SensorEvent(eventType, objectId);
        new SensorEventHandler(smartHome).handle(event);
    }
}
