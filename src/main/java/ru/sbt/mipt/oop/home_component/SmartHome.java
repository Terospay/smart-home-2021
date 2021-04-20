package ru.sbt.mipt.oop.home_component;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable, HomeComponent {
    Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    @Override
    public void execute(Action action) {
        action.act(this);
        rooms.forEach(room -> room.execute(action));
    }
}
