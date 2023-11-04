package controller;

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

//MemoryRepository accountRepository;


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
        User user1 = new User("John", "Doe", "john@example.com", "+1 123-456-7890", "123 Main Street, Anytown");
        User user2 = new User("Jane", "Doe", "jane@example.com", "+1 234-567-8901", "456 Elm Street, Anytown");
        accountRepository.addAccount(user1.getFirstName() + user1.getLastName(), new BankAccount(UUID.randomUUID(), 5000.0, 1500.0, user1));
        accountRepository.addAccount(user2.getFirstName() + user2.getLastName(), new BankAccount(UUID.randomUUID(), 10000.0, 2000.0, user2));
    }
    private void createNewAccount() {
        presenter.displayMessage("Creating a new account...");

        // Get user details
        presenter.displayMessage("Enter first name:");
        String firstName = getUserInputString();
        presenter.displayMessage("Enter last name:");
        String lastName = getUserInputString();
        presenter.displayMessage("Enter email address:");
        String email = getUserInputString();
        presenter.displayMessage("Enter phone number:");
        String phone = getUserInputString();
        presenter.displayMessage("Enter address:");
        String address = getUserInputString();

        // Create a new User object
        User newUser = new User(firstName, lastName, email, phone, address);

        // Set an initial balance and limit for the account, could be user input or predefined
        double initialBalance = 0.0; // This could also be a user input
        double initialLimit = 0.0; // This could also be a user input

        // Create a new BankAccount object
        BankAccount newAccount = new BankAccount(UUID.randomUUID(), initialBalance, initialLimit, newUser);

        // Add the account to the repository
        accountRepository.addAccount(newUser.getFirstName() + newUser.getLastName(), newAccount);

        presenter.displayMessage("Account created successfully!");
    }

    private boolean authenticateUser() {
        presenter.displayMessage("Please enter your first name:");
        String firstName = getUserInputString();
        presenter.displayMessage("Please enter your last name:");
        String lastName = getUserInputString();

        String key = firstName + lastName;
        currentAccount = accountRepository.getAccount(key);
        if (currentAccount != null) {
            presenter.displayMessage("Authenticated successfully.");
            return true;
        } else {
            presenter.displayMessage("Authentication failed. No account found for the given name.");
            return false;
        }
    }
    @Override
    public void run() {
        UUID accountId = UUID.randomUUID();

        boolean exit = false;

        while (!exit) {
            presenter.displayMessage("Menu:");
            presenter.displayMessage("1. Converter Device");
            presenter.displayMessage("2. Bank Transfer");
            presenter.displayMessage("3. Cap Management");
            presenter.displayMessage("4. Display Info Account");
            presenter.displayMessage("5. Display RIB");
            presenter.displayMessage("6. Received Money");
            presenter.displayMessage("7. Quit");

            int choice = presenter.getUserChoice();

            switch (choice) {
                case 1:
                    ConverterDevice();
                    break;
                case 2: // Bank Transfer
                    if (authenticateUser()) {
                        BankAccount targetAccount = getAccountFromUser(); // Ajouté pour obtenir le compte cible
                        if (targetAccount != null) {
                            BankTransfer(currentAccount, targetAccount);
                        } else {
                            presenter.displayMessage("Account not found.");
                        }
                    }
                    break;
                case 3: // Cap Management
                    if (authenticateUser()) {
                        testCapManagement(currentAccount);
                    }
                    break;
                case 4: // Display Info Account
                    if (authenticateUser()) {
                        DisplayInfoAccount(currentAccount);
                    }
                    break;
                case 5:
                    DisplayRIB(currentAccount);
                    break;
                case 6:
                    ReceivedMoney(currentAccount, currentAccount);
                    break;
                case 7:
                    exit = true;
                    break;
                case 8:
                    createNewAccount();
                    break;
                default:
                    presenter.displayMessage("Invalid choice. Please try again.");
            }
        }
    }
    private BankAccount getAccountFromUser() {
        presenter.displayMessage("Please enter the account's first name:");
        String firstName = getUserInputString(); // Supposition qu'une telle méthode existe
        presenter.displayMessage("Please enter the account's last name:");
        String lastName = getUserInputString(); // Supposition qu'une telle méthode existe

        String key = firstName + lastName;

        // Supposition que MemoryRepository a une méthode getAccount(String key)
        // qui retourne un BankAccount ou null si aucun compte n'est trouvé
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

    private void BankTransfer(BankAccount sourceAccount, BankAccount targetAccount) {
        presenter.displayMessage("Enter the transfer amount : ");
        double transferAmount = presenter.getUserDoubleInput();
        Request transferRequest = new Request(Double.toString(transferAmount));

        BankTransfer bankTransfer = new BankTransfer(reqHandle);
        Response response = bankTransfer.transferMoney(transferRequest, sourceAccount, targetAccount);

        presenter.displayMessage("source acc = " + sourceAccount.getBalance());
        presenter.displayMessage("target acc = " + targetAccount.getBalance());
        presenter.displayMessage(response.getResponseData());
    }

    private void testCapManagement(BankAccount sourceAccount) {
        presenter.displayMessage("Enter the new capital limit : ");
        double newLimit = presenter.getUserDoubleInput();
        Request capRequest = new Request(Double.toString(newLimit));

        CapManagement capManagement = new CapManagement(reqHandle);
        Response r = capManagement.manageCapital(capRequest, sourceAccount);

        presenter.displayMessage("source acc = " + sourceAccount.getLimit());
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

    private void ReceivedMoney(BankAccount sourceAccount, BankAccount targetAccount) {
        ReceivedMoney receivedMoney = new ReceivedMoney(reqHandle);
        receivedMoney.notifyTransferResult(sourceAccount, targetAccount, true);
    }
}