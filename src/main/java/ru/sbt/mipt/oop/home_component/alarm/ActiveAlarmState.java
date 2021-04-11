package ru.sbt.mipt.oop.home_component.alarm;

public class ActiveAlarmState implements AlarmState{
    private final Alarm alarm;

    public ActiveAlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {}

    @Override
    public void deactivate(String code) {
        if (alarm.isCorrect(code)) {
            alarm.setState(new InactiveAlarmState(alarm));
        } else {
            alarm.setState(new AlertAlarmState(alarm));
            alert();
        }
    }

    @Override
    public void alert() {
        alarm.setState(new AlertAlarmState(alarm));
        System.out.println("Sending sms");
    }
}
