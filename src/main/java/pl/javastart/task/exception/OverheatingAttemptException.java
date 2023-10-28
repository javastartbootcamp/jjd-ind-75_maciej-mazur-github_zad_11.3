package pl.javastart.task.exception;

public class OverheatingAttemptException extends RuntimeException {
    private int targetClockSpeed;
    int targetTemperature;

    public OverheatingAttemptException(int targetClockSpeed, int targetTemperature) {
        this.targetClockSpeed = targetClockSpeed;
        this.targetTemperature = targetTemperature;
    }

    public int getTargetClockSpeed() {
        return targetClockSpeed;
    }

    public int getTargetTemperature() {
        return targetTemperature;
    }
}
