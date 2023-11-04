package controller;
import java.util.List;
import datatransferobject.IRequestHandle;
import datatransferobject.ImplementIRequestHandle;
import datatransferobject.Request;
import datatransferobject.Response;
import entities.BankAccount;
import entities.User;
import presenter.AppPresenter;
import usecases.*;
import java.util.Scanner;
import repositories.MemoryRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;



public class ConsoleAppController implements AppController {
    private final AppPresenter presenter;
    private final IRequestHandle reqHandle;
    private BankAccount currentAccount;
    private final MemoryRepository accountRepository;
    private final Scanner scanner;


    public ConsoleAppController(AppPresenter presenter) {
        this.presenter = presenter;
        this.reqHandle = new ImplementIRequestHandle();
        this.accountRepository = new MemoryRepository();
        initializeAccounts();
        this.scanner = new Scanner(System.in);
    }

    public String getUserInputString() {
        return scanner.nextLine();
    }


    private void initializeAccounts() {
        User user1 = new User("Yan", "M", "yan@example.com", "+1 123-456-7890", "123 Main Street, Anytown");
        User user2 = new User("Ines", "D", "ines@example.com", "+1 234-567-8901", "456 Elm Street, Anytown");
        accountRepository.addAccount(user1.getFirstName() + user1.getLastName(), new BankAccount(UUID.randomUUID(), 5000.0, 1500.0, user1));
        accountRepository.addAccount(user2.getFirstName() + user2.getLastName(), new BankAccount(UUID.randomUUID(), 10000.0, 2000.0, user2));
    }
    private void createNewAccount() {
        presenter.displayMessage("Creating a new account...");

        presenter.displayMessage("Enter your first name:");
        String firstName = getUserInputString();
        presenter.displayMessage("Enter your last name:");
        String lastName = getUserInputString();
        presenter.displayMessage("Enter your email address:");
        String email = getUserInputString();
        presenter.displayMessage("Enter your phone number:");
        String phone = getUserInputString();
        presenter.displayMessage("Enter your postal address:");
        String address = getUserInputString();


        User newUser = new User(firstName, lastName, email, phone, address);

        double initialBalance = 0.0;
        double initialLimit = 0.0;

        BankAccount newAccount = new BankAccount(UUID.randomUUID(), initialBalance, initialLimit, newUser);
        accountRepository.addAccount(newUser.getFirstName() + newUser.getLastName(), newAccount);

        presenter.displayMessage("Account created successfully !");
    }

    private boolean authenticateUser() {
        presenter.displayMessage("\nAuthentification");
        presenter.displayMessage("Please enter your first name : ");
        String firstName = getUserInputString();
        presenter.displayMessage("Please enter your last name : ");
        String lastName = getUserInputString();

        String key = firstName + lastName;
        currentAccount = accountRepository.getAccount(key);
        if (currentAccount != null) {
            presenter.displayMessage("Authentication successful.");
            return true;
        } else {
            presenter.displayMessage("Authentication failed. No account found for the given name.");
            return false;
        }
    }
    public void run() {
        presenter.displayMessage("Do you have an existing account? (yes/no)");
        presenter.displayMessage("Your choice : ");
        String hasAccount = getUserInputString().trim().toLowerCase();

        if (hasAccount.equals("yes")) {
            if (authenticateUser()) {
                displayMenuAndHandleUserChoice(); // Cette méthode gère le menu existant et les choix de l'utilisateur.
            } else {
                presenter.displayMessage("Authentication failed. Redirecting to account creation.");
                createNewAccount();
            }
        } else if (hasAccount.equals("no")) {
            createNewAccount();
            displayMenuAndHandleUserChoice();
        } else {
            presenter.displayMessage("Invalid response. Please start over.");
        }
    }
    private void displayMenuAndHandleUserChoice() {
        boolean exit = false;

        while (!exit) {
            presenter.displayMessage("\nMenu:");
            presenter.displayMessage("1. How to use our Bank App");
            presenter.displayMessage("2. Send monney");
            presenter.displayMessage("3. Manage your capital limit");
            presenter.displayMessage("4. Display personnal informations");
            presenter.displayMessage("5. Display RIB");
            presenter.displayMessage("6. Transaction history");
            presenter.displayMessage("7. Create a new account");
            presenter.displayMessage("8. Converter monney");
            presenter.displayMessage("9. Quit");

            int choice = presenter.getUserChoice();

            switch (choice) {
                case 1:
                    displayInitialMessage();
                    break;
                case 2:
                    if (authenticateUser()) {
                        BankAccount targetAccount = getAccountFromUser();
                        if (targetAccount != null) {
                            BankTransfer(currentAccount, targetAccount);
                        } else {
                            presenter.displayMessage("Account not found.");
                        }
                    }
                    break;
                case 3:
                    if (authenticateUser()) {
                        CapManagement(currentAccount);
                    }
                    break;
                case 4:
                    if (authenticateUser()) {
                        DisplayInfoAccount(currentAccount);
                    }
                    break;
                case 5:
                    DisplayRIB(currentAccount);
                    break;
                case 6:
                    if (authenticateUser()) {
                        displayReceivedTransactions(currentAccount);
                    }
                    break;
                case 7:
                    createNewAccount();
                    break;
                case 8:
                    ConverterDevice();
                    break;
                case 9:
                    exit = true;
                    break;
                default:
                    presenter.displayMessage("Invalid choice. Please try again.");;
            }
        }
    }

    private BankAccount getAccountFromUser() {
        presenter.displayMessage("Please enter the recipient's first name :");
        String firstName = getUserInputString();
        presenter.displayMessage("Please enter the recipient's last name :");
        String lastName = getUserInputString();

        String key = firstName + lastName;

        return accountRepository.getAccount(key);
    }


    public String getUserInput() {
        return scanner.nextLine();
    }
    private void ConverterDevice() {
        ConverterDevice converterDevice = new ConverterDevice(reqHandle);
        Response resp = converterDevice.runConversion();
        presenter.displayMessage(resp.getResponseData());
    }
    private void displayInitialMessage() {
        presenter.displayMessage("Bonjour et bienvenue dans notre système de gestion de comptes bancaires.\n "
                + "Des comptes sont déjà pré-créés, tels que ceux appartenant à Yan M et Ines D. \n"
                + "Attention à la sensibilité à la casse et aux espaces supplémentaires lors de la saisie des noms pour les transactions.\n "
                + "Assurez-vous d'entrer les noms correctement pour une expérience optimale.\n");

        presenter.displayMessage("Hello and welcome to our bank account management system.\n "
                + "Several accounts, such as those belonging to Yan M and Ines D, have been pre-created for your convenience.\n "
                + "Please be aware of case sensitivity and extra spaces when entering names for transactions.\n "
                + "Ensure proper name entry for an optimal experience.\n");
    }


    private void BankTransfer(BankAccount sourceAccount, BankAccount targetAccount) {
        presenter.displayMessage("Enter the amount you want to transfer : ");
        double transferAmount = presenter.getUserDoubleInput();
        Request transferRequest = new Request(Double.toString(transferAmount));

        BankTransfer bankTransfer = new BankTransfer(reqHandle);
        Response response = bankTransfer.transferMoney(transferRequest, sourceAccount, targetAccount);

        presenter.displayMessage("After your transfer you currently have : " + sourceAccount.getBalance());


    }

    private void CapManagement(BankAccount sourceAccount) {
        presenter.displayMessage("Enter the new capital limit : ");
        double newLimit = presenter.getUserDoubleInput();
        Request capRequest = new Request(Double.toString(newLimit));

        CapManagement capManagement = new CapManagement(reqHandle);
        Response r = capManagement.manageCapital(capRequest, sourceAccount);

        presenter.displayMessage("Your new capital limit is at : " + sourceAccount.getLimit());
        presenter.displayMessage(r.getResponseData());
    }


    private void DisplayInfoAccount(BankAccount account) {
        Request infoRequest = new Request("");
        DisplayInfoAccount displayInfoAccount = new DisplayInfoAccount(reqHandle);
        Response response = displayInfoAccount.displayInfo(infoRequest, account);
        presenter.displayMessage(response.getResponseData());
    }

    private void DisplayRIB(BankAccount sourceAccount) {
        DisplayRIB displayRIB = new DisplayRIB(reqHandle);
        Request req = new Request("");
        Response r2 = displayRIB.displayRIB(req, sourceAccount);
        presenter.displayMessage(r2.getResponseData());
    }

    private void displayReceivedTransactions(BankAccount account) {
        List<Transaction> transactions = account.getIncomingTransactions();
        if (transactions.isEmpty()) {
            presenter.displayMessage("No transactions received.");
            return;
        }
        for (Transaction transaction : transactions) {
            presenter.displayMessage(transaction.toString());
        }
        List<Transaction> outgoingTransactions = account.getOutgoingTransactions();
        for (Transaction transaction : outgoingTransactions) {
            presenter.displayMessage(transaction.toString());
        }
    }
    private void ReceivedMoney(BankAccount sourceAccount, BankAccount targetAccount) {
        ReceivedMoney receivedMoney = new ReceivedMoney(reqHandle);
        receivedMoney.notifyTransferResult(sourceAccount, targetAccount, true);
    }
}