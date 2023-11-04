import datatransferobject.ImplementIRequestHandle;
import entities.BankAccount;
import entities.User;
import usecases.*;
import presenter.ConsoleAppPresenter;
import controller.ConsoleAppController;

public class Main {
    public static void main(String[] args){
        ConsoleAppPresenter presenter = new ConsoleAppPresenter();
        ConsoleAppController controller = new ConsoleAppController(presenter);

        controller.run();
    }
}
