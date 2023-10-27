package pl.javastart.task.computer;

import pl.javastart.task.components.HardDrive;
import pl.javastart.task.components.Memory;
import pl.javastart.task.components.Processor;
import pl.javastart.task.exception.IllegalClockSpeedException;
import pl.javastart.task.printer.Printer;

import java.util.InputMismatchException;

public class Computer {
    private static final int PROCESSOR = 1;
    private static final int MEMORY = 2;
    private static final int BACK_TO_MAIN_MENU = 3;

    private final Printer printer;
    private final String name;

    private final Processor processor;
    private final Memory memory;
    private final HardDrive hardDrive;

    public Computer(Processor processor, Memory memory, HardDrive hardDrive, Printer printer, String name) {
        this.processor = processor;
        this.memory = memory;
        this.hardDrive = hardDrive;
        this.printer = printer;
        this.name = name;
    }

    public void overclock() {
        boolean quit = false;
        int choice;
        int targetClockSpeed;

        do {
            printOptions();

            try {
                choice = ComputerApp.scanner.nextInt();
                ComputerApp.scanner.nextLine();

                switch (choice) {
                    case PROCESSOR -> {
                        printer.printMessage(processor.getConfigInfo());
                        targetClockSpeed = readTargetClockSpeed();
                        processor.overclock(targetClockSpeed);
                        printer.printMessage("\nPodkręcenie częstotliwości taktowania procesora na " + targetClockSpeed +
                                "MHz przebiegło pomyślnie.\n" + processor.getConfigInfo());
                    }
                    case MEMORY -> {
                        printer.printMessage(memory.getConfigInfo());
                        targetClockSpeed = readTargetClockSpeed();
                        memory.overclock(targetClockSpeed);
                        printer.printMessage("\nPodkręcenie częstotliwości taktowania RAM-u na " + targetClockSpeed +
                                "MHz przebiegło pomyślnie.\n" + processor.getConfigInfo());
                    }
                    case BACK_TO_MAIN_MENU -> quit = true;
                    default -> throw new IllegalArgumentException();
                }

                quit = true;
            } catch (IllegalArgumentException e) {
                System.err.println("\nPodałeś nieprawidłową opcję. Spróbuj ponownie.\n");
            } catch (IllegalClockSpeedException e) {
                System.err.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("\nPodałeś wyraz zamiast liczby. Spróbuj ponownie\n");
                ComputerApp.scanner.nextLine();
            }
        } while (!quit);
    }

    private void printOptions() {
        printer.printMessage("""
                \nWybierz jedną z poniższych opcji podkręcania czestotliwości taktowania:
                > %d - Podkręć częstotliwość taktowania procesora
                > %d - Podkręć częstotliwość taktowania RAM-u
                > %d - Powrót do menu głównego
                Co wybierasz?\s""".formatted(PROCESSOR, MEMORY, BACK_TO_MAIN_MENU));
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
        return name + "\n\t{" +
                "processor=\n" + processor +
                "\n\tRAM=\n" + memory +
                "\n\tHDD=\n" + hardDrive +
                '}';
    }
}
