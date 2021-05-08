package ru.sbt.mipt.oop.remote_control;

import ru.sbt.mipt.oop.remote_control.command.Command;

import java.util.Map;

public class RemoteControlImpl implements RemoteControl{
    private Map<String, Command> binnedCommand;

    public RemoteControlImpl(Map<String, Command> binnedCommand) {
        this.binnedCommand = binnedCommand;
    }

    public void bindButton(String buttonCode, Command command) {
        binnedCommand.put(buttonCode, command);
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (binnedCommand.containsKey(buttonCode)) {
            binnedCommand.get(buttonCode).execute();
        }
    }
}
