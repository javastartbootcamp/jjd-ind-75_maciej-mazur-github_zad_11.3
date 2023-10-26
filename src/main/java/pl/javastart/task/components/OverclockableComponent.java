package pl.javastart.task.components;

import pl.javastart.task.computer.ComputerApp;
import pl.javastart.task.exception.IllegalClockSpeedException;
import pl.javastart.task.printer.Printer;

import java.util.InputMismatchException;

abstract class OverclockableComponent extends Component implements Overclockable {
    private final Config config;
    private final Printer printer;

    public OverclockableComponent(String model, String manufacturer, String serialNo,
                                  int temperatureIncrease, int clockSpeed, int temperature, int maxTemperature,
                                  Printer printer) {
        super(model, manufacturer, serialNo);
        config = new Config(temperatureIncrease, clockSpeed, temperature, maxTemperature);
        this.printer = printer;
    }

    @Override
    public void overclock() throws InputMismatchException, IllegalClockSpeedException {
        int targetClockSpeed = readTargetClockSpeed();
        int targetTemperature = config.getTemperature() + (targetClockSpeed - config.getClockSpeed()) / 100 * config.getTemperatureIncrease();

        if (targetTemperature > config.getMaxTemperature()) {
            throw new IllegalClockSpeedException("\n" + targetClockSpeed + " clock speed would burn the component\n");
        }

        config.setTemperature(targetTemperature);
        config.setClockSpeed(targetClockSpeed);
        printer.printMessage("\nPodkręcenie częstotliwości taktowania przebiegło pomyślnie." +
                "\nAktualna częstotliwość taktowania to " + targetClockSpeed +
                "MHz przy temperaturze pracy " + targetTemperature + " stopni\n");
    }

    private int readTargetClockSpeed() {
        printer.printMessage("Podaj oczekiwaną częstotliwość taktowania: ");
        /*
        potencjalny InputMismatchException zostanie przez tę metodę automatycznie wyrzucony do overclock(), a overclock() przerzuci go wyżej do obsługi
         */
        int speedChoice = ComputerApp.scanner.nextInt();
        ComputerApp.scanner.nextLine();
        return speedChoice;
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
