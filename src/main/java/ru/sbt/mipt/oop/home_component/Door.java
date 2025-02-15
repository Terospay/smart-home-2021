package ru.sbt.mipt.oop.home_component;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;

public class Door implements HomeComponent, Actionable {
    private final String id;
    private boolean isOpen;

    public Door(String id, boolean isOpen) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public void execute(Action action) {
        action.act(this);
    }
}
