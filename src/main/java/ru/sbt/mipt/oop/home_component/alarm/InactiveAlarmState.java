package ru.sbt.mipt.oop.home_component.alarm;

public class InactiveAlarmState implements AlarmState{
    private final Alarm alarm;

    public InactiveAlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        alarm.setCode(code);
        alarm.setState(new ActiveAlarmState(alarm));
    }

    @Override
    public void deactivate(String code) {}

    @Override
    public void alert() {
        alarm.setState(new AlertAlarmState(alarm));
        System.out.println("Sending sms");
    }
}
