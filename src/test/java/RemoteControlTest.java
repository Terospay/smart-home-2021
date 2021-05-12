import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.home_component.SmartHome;
import ru.sbt.mipt.oop.home_component.alarm.ActiveAlarmState;
import ru.sbt.mipt.oop.home_component.alarm.Alarm;
import ru.sbt.mipt.oop.remote_control.RemoteControl;
import ru.sbt.mipt.oop.remote_control.RemoteControlImpl;
import ru.sbt.mipt.oop.remote_control.command.CommandAlarmActivate;

import java.util.Map;
import static org.junit.Assert.assertEquals;

public class RemoteControlTest {
    SmartHome smartHome;
    RemoteControl remoteControl;
    Alarm alarm;

    @Before
    public void init() {
        alarm = new Alarm();
        smartHome = new SmartHome(alarm);
    }

    @Test
    public void BindAndPressButtonOnRemoteController() {
        remoteControl = new RemoteControlImpl(Map.of("1", new CommandAlarmActivate(smartHome, "admin")));
        remoteControl.onButtonPressed("1", "123");
        assertEquals(true, alarm.getState() instanceof ActiveAlarmState);
    }

}
