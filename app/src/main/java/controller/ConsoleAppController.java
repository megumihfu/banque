package controller;

import datatransferobject.IRequestHandle;
import datatransferobject.ImplementIRequestHandle;
import datatransferobject.Request;
import datatransferobject.Response;
import entities.BankAccount;
import entities.User;
import presenter.AppPresenter;
import usecases.*;

import java.util.UUID;

public class ConsoleAppController implements AppController {
    private AppPresenter presenter;
    private IRequestHandle reqHandle;

    public ConsoleAppController(AppPresenter presenter) {
        this.presenter = presenter;
        this.reqHandle = new ImplementIRequestHandle();
    }

    @Override
    public void run() {
        UUID accountId = UUID.randomUUID();
        User user = new User("John", "Doe", "john@example.com", "+1 123-456-7890", "123 Main Street, Anytown");
        User user2 = new User("Johnny", "Doezz", "johnzzz@example.com", "+1 1278903-456-", "123 Main Street, Anytown");
        BankAccount sourceAccount = new BankAccount(accountId, 5000.0, 1500.0, user);
        BankAccount targetAccount = new BankAccount(accountId, 10000.0, 2000.0, user2);

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
                case 2:
                    BankTransfer(sourceAccount, targetAccount);
                    break;
                case 3:
                    testCapManagement(sourceAccount);
                    break;
                case 4:
                    DisplayInfoAccount(sourceAccount, targetAccount);
                    break;
                case 5:
                    DisplayRIB(sourceAccount);
                    break;
                case 6:
                    ReceivedMoney(sourceAccount, targetAccount);
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    presenter.displayMessage("Invalid choice. Please try again.");
            }
        }
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


    private void DisplayInfoAccount(BankAccount sourceAccount, BankAccount targetAccount) {
        Request infoRequest = new Request("");
        DisplayInfoAccount displayInfoAccount = new DisplayInfoAccount(reqHandle);
        Response response1 = displayInfoAccount.displayInfo(infoRequest, sourceAccount);
        Response response2 = displayInfoAccount.displayInfo(infoRequest, targetAccount);
        presenter.displayMessage(response1.getResponseData());
        presenter.displayMessage(response2.getResponseData());
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
