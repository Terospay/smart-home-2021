package command_test;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.home_component.SmartHome;
import ru.sbt.mipt.oop.home_component.alarm.ActiveAlarmState;
import ru.sbt.mipt.oop.home_component.alarm.Alarm;
import ru.sbt.mipt.oop.home_component.alarm.AlertAlarmState;
import ru.sbt.mipt.oop.remote_control.command.CommandAlarmActivate;
import ru.sbt.mipt.oop.remote_control.command.CommandAlarmAlert;

import static org.junit.Assert.assertEquals;


public class CommandAlarmTest {
    SmartHome smartHome;
    Alarm testingAlarm;

    @Before
    public void init() {
        testingAlarm = new Alarm();
        smartHome = new SmartHome(testingAlarm);
    }

    @Test
    public void CommandActivateAlarm() {
        new CommandAlarmActivate(smartHome, "123").execute();
        assertEquals(true, testingAlarm.getState() instanceof ActiveAlarmState);
    }

    @Test
    public void CommandAlertAlarm() {
        new CommandAlarmAlert(smartHome).execute();
        assertEquals(true, testingAlarm.getState() instanceof AlertAlarmState);
    }
}
