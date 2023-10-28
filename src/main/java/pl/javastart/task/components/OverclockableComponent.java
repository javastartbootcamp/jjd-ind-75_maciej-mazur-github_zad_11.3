package pl.javastart.task.components;

import pl.javastart.task.exception.DownclockingAttemptException;
import pl.javastart.task.exception.OverheatingAttemptException;
import java.util.InputMismatchException;

abstract class OverclockableComponent extends Component implements Overclockable {
    private final Config config;

    public OverclockableComponent(String model, String manufacturer, String serialNo,
                                  int temperatureIncrease, int clockSpeed, int temperature, int maxTemperature) {
        super(model, manufacturer, serialNo);
        config = new Config(temperatureIncrease, clockSpeed, temperature, maxTemperature);
    }

    @Override
    public void overclock(int targetClockSpeed) throws InputMismatchException, OverheatingAttemptException {
        if (targetClockSpeed < config.getClockSpeed()) {
            throw new DownclockingAttemptException();
        }

        int targetTemperature = config.getTemperature() + (targetClockSpeed - config.getClockSpeed()) / 100 * config.getTemperatureIncrease();

        if (targetTemperature > config.getMaxTemperature()) {
            throw new OverheatingAttemptException(targetClockSpeed, targetTemperature);
        }

        config.setTemperature(targetTemperature);
        config.setClockSpeed(targetClockSpeed);
    }

    @Override
    public String toString() {
        return "\t\t{" +
                "Częstotliwość taktowania=" + config.getClockSpeed() +
                "MHZ, temperatura=" + config.getTemperature() +
                "stopni, maksymalna temperatura=" + config.getMaxTemperature() +
                "stopni}" +
                "\n\t\t" + super.toString();
    }

    public String getConfigInfo() {
        return String.format("Aktualna częstotliwość taktowania procesora to %dMHz przy temperaturze %d stopni." +
                " Maksymalna temperatura pracy to %d stopni.",
                config.getClockSpeed(), config.getTemperature(), config.getMaxTemperature());
    }
}
