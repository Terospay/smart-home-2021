package ru.sbt.mipt.oop.home_component.alarm;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.home_component.HomeComponent;

public class Alarm implements HomeComponent, Actionable {
    private String code = "admin";
    private AlarmState state = new InactiveAlarmState(this);

    public Alarm(){}

    public Alarm(String code) {
        this.code = code;
    }

    @Override
    public void execute(Action action) {
        action.act(this);
    }

    public void activate(String code) {
        state.activate(code);
    }

    public void deactivate(String code) {
        state.deactivate(code);
    }

    public void alert() {
        state.alert();
    }

    protected void setCode(String code) {
        this.code = code;
    }

    protected void setState(AlarmState state) {
        this.state = state;
    }

    public boolean isCorrect(String code) {
        return code.equals(this.code);
    }

    public boolean isInactive() {
        return state.getClass() == InactiveAlarmState.class;
    }
}
