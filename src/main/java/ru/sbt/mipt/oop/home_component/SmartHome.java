package ru.sbt.mipt.oop.home_component;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.home_component.alarm.Alarm;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable, HomeComponent {
    private Collection<Room> rooms = new ArrayList<Room>();
    private Alarm alarm = null;

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public SmartHome(Collection<Room> rooms, Alarm alarm) {
        this(rooms);
        this.alarm = alarm;
    }

    public SmartHome(Alarm alarm) {
        this.alarm = alarm;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void execute(Action action) {
        action.act(this);
        if(alarm != null) {
            action.act(alarm);
        }
        rooms.forEach(room -> room.execute(action));
    }
}
