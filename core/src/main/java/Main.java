import datatransferobject.ImplementIRequestHandle;
import datatransferobject.Request;
import entities.BankAccount;
import entities.User;
import usecases.BankTransfer;
import usecases.DisplayRIB;
import usecases.DisplayInfoAccount;
import usecases.ConverterDevice;
import usecases.CapManagement;
import datatransferobject.Response;
import datatransferobject.IRequestHandle;

import java.util.UUID;

public class Main {
    public static void main(String[] args){
        IRequestHandle reqHandle = new ImplementIRequestHandle();
        UUID accountId = UUID.randomUUID();
        User user = new User("John", "Doe", "john@example.com", "+1 123-456-7890", "123 Main Street, Anytown");
        User user2 = new User("Johnny", "Doezz", "johnzzz@example.com", "+1 1278903-456-", "123 Main Street, Anytown");
        BankAccount sourceAccount = new BankAccount(accountId, 5000.0, 1500.0, user);
        BankAccount targetAccount = new BankAccount(accountId, 10000.0, 2000.0, user2);
        /* test ConverterDevice */

        ConverterDevice converterDevice = new ConverterDevice(reqHandle);
        Response resp = converterDevice.runConversion();
        System.out.println(resp.getResponseData());

        //test BankTransfer

        System.out.println("source acc = "+sourceAccount.getBalance());
        System.out.println("target acc = "+targetAccount.getBalance());

        Request transferRequest = new Request("100.0");

        BankTransfer bankTransfer = new BankTransfer(reqHandle);

        Response response = bankTransfer.transferMoney(transferRequest, sourceAccount, targetAccount);

        System.out.println("source acc = "+sourceAccount.getBalance());
        System.out.println("target acc = "+targetAccount.getBalance());
        System.out.println(response.getResponseData());


        /* test CapManagement */

        System.out.println("source acc = "+sourceAccount.getLimit());
        Request capRequest = new Request("3500.0");
        CapManagement capManagement = new CapManagement(reqHandle);
        Response r = capManagement.manageCapital(capRequest, sourceAccount);
        System.out.println("source acc = "+sourceAccount.getLimit());
        System.out.println(r.getResponseData());

        /* test DisplayInfoAccount */
        Request infoRequest = new Request("");
        DisplayInfoAccount displayInfoAccount = new DisplayInfoAccount(reqHandle);

        Response response1 = displayInfoAccount.displayInfo(infoRequest, sourceAccount);
        Response response2 = displayInfoAccount.displayInfo(infoRequest, targetAccount);
        System.out.println(response1.getResponseData());
        System.out.println(response2.getResponseData());

    /*  test DisplayRIB */
        DisplayRIB displayRIB = new DisplayRIB(reqHandle);
        Request req = new Request("");
        Response r2 = displayRIB.displayRIB(req, sourceAccount);
        System.out.println(r2.getResponseData());
    }
}
