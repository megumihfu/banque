import datatransferobject.ImplementIRequestHandle;
import datatransferobject.Request;
import entities.BankAccount;
import usecases.BankTransfer;
import usecases.ConverterDevice;
import usecases.CapManagement;
import datatransferobject.Response;
import datatransferobject.IRequestHandle;

import java.util.UUID;

public class Main {
    public static void main(String[] args){
        IRequestHandle reqHandle = new ImplementIRequestHandle();
        /* test ConverterDevice

        ConverterDevice converterDevice = new ConverterDevice(reqHandle);
        Response resp = converterDevice.runConversion();
        System.out.println(resp.getResponseData());*/

        UUID accountId = UUID.randomUUID();

        /* test BankTransfer

        BankAccount sourceAccount = new BankAccount(accountId, 5000, 1500);
        BankAccount targetAccount = new BankAccount(accountId, 2000, 500);
        System.out.println("source acc = "+sourceAccount.getBalance());
        System.out.println("target acc = "+targetAccount.getBalance());

        Request transferRequest = new Request("100.0");

        BankTransfer bankTransfer = new BankTransfer(reqHandle);

        Response response = bankTransfer.transferMoney(transferRequest, sourceAccount, targetAccount);

        System.out.println("source acc = "+sourceAccount.getBalance());
        System.out.println("target acc = "+targetAccount.getBalance());
        System.out.println(response.getResponseData());*/

        BankAccount sourceAccount = new BankAccount(accountId, 5000, 1500);
        System.out.println("source acc = "+sourceAccount.getLimit());
        Request capRequest = new Request("3500.0");
        CapManagement capManagement = new CapManagement(reqHandle);
        Response response = capManagement.manageCapital(capRequest, sourceAccount);
        System.out.println("source acc = "+sourceAccount.getLimit());
        System.out.println(response.getResponseData());


    }
}
