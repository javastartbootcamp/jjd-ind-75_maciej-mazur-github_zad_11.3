package pl.javastart.task.components;

public class Memory extends OverclockableComponent {
    private static final int TEMPERATURE_INCREASE = 10;

    private final int capacity;

    public Memory(String model, String manufacturer, String serialNo,
                  int clockSpeed, int temperature, int maxTemperature, int capacity) {
        super(model, manufacturer, serialNo, TEMPERATURE_INCREASE, clockSpeed, temperature, maxTemperature);
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "\t\t{Pojemność=" + capacity + "GB}\n" + super.toString() + "\n";
    }

    @Override
    public String getConfigInfo() {
        return super.getConfigInfo() + "\nPrzy podkręcaniu częstotliwości temperatura będzie wzrastać" +
                " o " + TEMPERATURE_INCREASE + " stopni na każde dodane 100MHz\n";
    }
}
