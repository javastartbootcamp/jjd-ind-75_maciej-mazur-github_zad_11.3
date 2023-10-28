package pl.javastart.task.components;

class Config {
    private final int temperatureIncrease;
    private int clockSpeed;
    private int temperature;
    private final int maxTemperature;

    Config(int temperatureIncrease, int clockSpeed, int temperature, int maxTemperature) {
        this.temperatureIncrease = temperatureIncrease;
        this.clockSpeed = clockSpeed;
        this.temperature = temperature;
        this.maxTemperature = maxTemperature;
    }

    int getTemperatureIncrease() {
        return temperatureIncrease;
    }

    int getClockSpeed() {
        return clockSpeed;
    }

    void setClockSpeed(int clockSpeed) {
        this.clockSpeed = clockSpeed;
    }

    int getTemperature() {
        return temperature;
    }

    void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    int getMaxTemperature() {
        return maxTemperature;
    }
}
