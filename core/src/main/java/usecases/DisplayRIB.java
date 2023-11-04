package usecases;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import datatransferobject.IRequestHandle;
import datatransferobject.Request;
import datatransferobject.Response;
import entities.BankAccount;


public class DisplayRIB {
    private IRequestHandle requestHandle;

    public DisplayRIB(IRequestHandle requestHandle) {
        this.requestHandle = requestHandle;
    }

    private final Random random = new Random();

    public String generateRandomAccountNumber() {
        int accountNumber = 10000000 + random.nextInt(90000000);
        return String.valueOf(accountNumber);
    }

    public String generateRandomIBAN() {
        return "FR76" + generateRandomAccountNumber() + "1234";
    }

    public String generateRandomBICCode() {
        return "BIC" + random.nextInt(10000);
    }

    public Response displayRIB(Request req, BankAccount bankAccount) {
        if (bankAccount == null) {
            return new Response("No bank account found.");
        }

        String ribInfo = "Bank Name: Bank93\n" +
                "Date: " + getCurrentDate() + "\n" +
                "Account Number: " + bankAccount.getAccountNumber() + "\n" +
                "IBAN: " + bankAccount.getIban() + "\n" +
                "BIC Code: " + bankAccount.getBicCode();

        return new Response(ribInfo);
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }
}
