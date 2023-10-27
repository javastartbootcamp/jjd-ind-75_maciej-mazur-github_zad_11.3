package pl.javastart.task.components;

import pl.javastart.task.exception.IllegalClockSpeedException;
import java.util.InputMismatchException;

abstract class OverclockableComponent extends Component implements Overclockable {
    private final Config config;

    public OverclockableComponent(String model, String manufacturer, String serialNo,
                                  int temperatureIncrease, int clockSpeed, int temperature, int maxTemperature) {
        super(model, manufacturer, serialNo);
        config = new Config(temperatureIncrease, clockSpeed, temperature, maxTemperature);
    }

    @Override
    public void overclock(int targetClockSpeed) throws InputMismatchException, IllegalClockSpeedException {
        if (targetClockSpeed < config.getClockSpeed()) {
            throw new IllegalClockSpeedException("\nThis is supposed to be an overclocking, not downclocking. "
                    + targetClockSpeed + " is therefore an incorrect target clock speed.\n");
        }

        int targetTemperature = config.getTemperature() + (targetClockSpeed - config.getClockSpeed()) / 100 * config.getTemperatureIncrease();

        if (targetTemperature > config.getMaxTemperature()) {
            throw new IllegalClockSpeedException("\n" + targetClockSpeed + " clock speed would burn the component\n");
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
