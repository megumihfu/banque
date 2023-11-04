import datatransferobject.ImplementIRequestHandle;
import entities.BankAccount;
import entities.User;
import usecases.*;
import presenter.ConsoleAppPresenter;
import controller.ConsoleAppController;

public class Main {
    public static void main(String[] args){
        // Create instances of your presenter and controller
        ConsoleAppPresenter presenter = new ConsoleAppPresenter();
        ConsoleAppController controller = new ConsoleAppController(presenter);

        // Run the application
        controller.run();
    }
}
