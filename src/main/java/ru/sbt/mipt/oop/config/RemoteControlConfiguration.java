package ru.sbt.mipt.oop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.home_component.SmartHome;
import ru.sbt.mipt.oop.remote_control.RemoteControl;
import ru.sbt.mipt.oop.remote_control.RemoteControlImpl;
import ru.sbt.mipt.oop.remote_control.RemoteControlRegistry;
import ru.sbt.mipt.oop.remote_control.command.*;

import java.util.*;

@Configuration
public class RemoteControlConfiguration {
    @Bean
    String createCode() {
        return "admin";
    }

    @Bean
    Command CommandAlarmActivate(SmartHome smartHome, String code) {
        return new CommandAlarmActivate(smartHome, code);
    }

    @Bean
    Command createCommandAlarmAlert(SmartHome smartHome) {
        return new CommandAlarmAlert(smartHome);
    }

    @Bean
    Command createCommandCloseHallDoor(SmartHome smartHome) {
        return new CommandCloseHallDoor(smartHome);
    }

    @Bean
    Command createCommandTurnOffAllLights(SmartHome smartHome) {
        return new CommandTurnOffAllLights(smartHome);
    }

    @Bean
    Command createCommandTurnOnAllLights(SmartHome smartHome) {
        return new CommandTurnOnAllLights(smartHome);
    }

    @Bean
    Command createCommandTurnOnHallLights(SmartHome smartHome) {
        return new CommandTurnOnHallLight(smartHome);
    }

    @Bean
    List<String> createButtons() {
        return new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "1", "2", "3", "4"));
    }

    @Bean
    Map<String, Command> createBinnedCommand(List<Command> commands, List<String> buttons) {
        int binnedSize = Math.min(buttons.size(), commands.size());
        Map<String, Command> binnedCommand = new HashMap<String, Command>();
        for (int i = 0; i < binnedSize; ++i) {
            binnedCommand.put(buttons.get(i), commands.get(i));
        }
        return binnedCommand;
    }

    @Bean
    RemoteControl createRemoteControl(Map<String, Command> binnedCommand) {
        return new RemoteControlImpl(binnedCommand);
    }

    @Bean
    RemoteControlRegistry createRemoteControlRegistry(RemoteControl remoteControl) {
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControlRegistry.registerRemoteControl(remoteControl, "123");
        return remoteControlRegistry;
    }
}
