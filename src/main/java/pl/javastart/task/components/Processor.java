package pl.javastart.task.components;

public class Processor extends OverclockableComponent {
    private static final int TEMPERATURE_INCREASE = 10;

    public Processor(String model, String manufacturer, String serialNo,
                     int clockSpeed, int temperature, int maxTemperature) {
        super(model, manufacturer, serialNo, TEMPERATURE_INCREASE, clockSpeed, temperature, maxTemperature);
    }

    @Override
    public String toString() {
        return "\t\t" + super.toString() + "\n";
    }

    @Override
    public String getConfigInfo() {
        return super.getConfigInfo() + "\nPrzy podkręcaniu częstotliwości temperatura będzie wzrastać" +
                " o " + TEMPERATURE_INCREASE + " stopni na każde dodane 100MHz\n";
    }
}
