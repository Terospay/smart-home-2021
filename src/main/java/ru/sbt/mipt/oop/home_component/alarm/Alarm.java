package ru.sbt.mipt.oop.home_component.alarm;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.home_component.HomeComponent;

public class Alarm implements HomeComponent, Actionable, AlarmState {
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

    @Override
    public void activate(String code) {
        state.activate(code);
    }

    @Override
    public void deactivate(String code) {
        state.deactivate(code);
    }

    @Override
    public void alert() {
        state.alert();
    }

    public void setCode(String code) {
        this.code = code;
    }

    public  void setState(AlarmState state) {
        this.state = state;
    }

    public boolean isCorrect(String code) {
        return code.equals(this.code);
    }

    public AlarmState getState() {
        return state;
    }
}
