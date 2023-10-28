package pl.javastart.task.components;

public abstract class Component {
    private final String model;
    private final String manufacturer;
    private final String serialNo;

    public Component(String model, String manufacturer, String serialNo) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.serialNo = serialNo;
    }

    @Override
    public String toString() {
        return "Dane produkcyjne{" +
                "model='" + model + '\'' +
                ", producent='" + manufacturer + '\'' +
                ", numer seryjny='" + serialNo + '\'' +
                '}';
    }
}
