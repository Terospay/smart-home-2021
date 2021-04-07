package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.base_device.Door;
import ru.sbt.mipt.oop.base_device.Light;

import java.util.Collection;

public class Room {
    private final Collection<Light> lights;
    private final Collection<Door> doors;
    private final String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }
}
