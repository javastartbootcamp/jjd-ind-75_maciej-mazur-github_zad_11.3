package pl.javastart.task.computer;

import pl.javastart.task.components.HardDrive;
import pl.javastart.task.components.Memory;
import pl.javastart.task.components.Processor;
import pl.javastart.task.printer.Printer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ComputerApp {
    private static final int OVERCLOCK = 1;
    private static final int PRINT_COMPUTER_INFO = 2;
    private static final int QUIT_PROGRAM = 3;

    private static final Printer printer = new Printer();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Processor processor = new Processor("Core i5-6600K", "Intel", "12345", 3500, 40, 80);
        Memory memory = new Memory("CL16 Renegade Black", "Kingston", "456789", 3200, 40, 100, 32);
        HardDrive hardDrive = new HardDrive("BARRACUDA", "Seagate", "102938475", 8);

        Computer computer = new Computer(processor, memory, hardDrive, printer, "Dell Inspiron 3020 S");
        run(computer);

    }

    private static void run(Computer computer) {
        boolean quit = false;
        int choice;

        do {
            printOptions();

            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case OVERCLOCK -> computer.overclock();
                    case PRINT_COMPUTER_INFO -> printer.printMessage(computer.toString());
                    case QUIT_PROGRAM -> quit = true;
                    default -> throw new IllegalArgumentException();
                }
            } catch (InputMismatchException e) {
                System.err.println("\nPodałeś nieprawidłową opcję. Spróbuj ponownie.\n");
                scanner.nextLine();
            }
        } while (!quit);
    }

    private static void printOptions() {
        printer.printMessage("""
                \nWybierz jedną z opcji:
                > %d - podkręcenie częstotliwości procesora lub RAM-u
                > %d - Wyświetl szczegółowe dane o podzespołach komputera
                > %d - Koniec programu

                Którą opcję wybierasz?\s""".formatted(OVERCLOCK, PRINT_COMPUTER_INFO, QUIT_PROGRAM));
    }
}
