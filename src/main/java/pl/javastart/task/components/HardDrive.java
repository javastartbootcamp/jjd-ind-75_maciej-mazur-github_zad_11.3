package pl.javastart.task.components;

public class HardDrive extends Component {
    private final int capacity;

    public HardDrive(String model, String manufacturer, String serialNo, int capacity) {
        super(model, manufacturer, serialNo);
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "\t\t{" +
                "pojemność=" + capacity +
                "TB}" +
                "\n\t\t" + super.toString() + "\n";
    }
}
