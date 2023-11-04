package presenter;
import java.util.Scanner;

public class ConsoleAppPresenter implements AppPresenter {
    private final Scanner scanner;

    public ConsoleAppPresenter() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public int getUserChoice() {
        try {
            System.out.print("Enter your choice: ");
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return getUserChoice();
        }
    }

    @Override
    public double getUserDoubleInput() {
        try {
            System.out.print("Enter a number: ");
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return getUserDoubleInput();
        }
    }
}
