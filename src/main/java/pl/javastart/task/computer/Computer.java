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

        do {
            printOptions();

            try {
                choice = ComputerApp.scanner.nextInt();
                ComputerApp.scanner.nextLine();

                switch (choice) {
                    case PROCESSOR -> {
                        printer.printMessage(processor.getConfigInfo());
                        processor.overclock();
                    }
                    case MEMORY -> {
                        printer.printMessage(memory.getConfigInfo());
                        memory.overclock();
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
                Wybierz jedną z poniższych opcji podkręcania czestotliwości taktowania:
                > 1 - Podkręć częstotliwość taktowania procesora
                > 2 - Podkręć częstotliwość taktowania RAM-u
                > 3 - Powrót do menu głównego
                Co wybierasz?\s""");
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
