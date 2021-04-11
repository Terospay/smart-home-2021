package ru.sbt.mipt.oop.home_component.alarm;

public class AlertAlarmState implements AlarmState{
    private final Alarm alarm;

    public AlertAlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {}

    @Override
    public void deactivate(String code) {
        if(alarm.isCorrect(code)) {
            alarm.setState(new InactiveAlarmState(alarm));
        } else {
            alert();
        }
    }

    @Override
    public void alert() {
        System.out.println("Sending sms");
    }
}
