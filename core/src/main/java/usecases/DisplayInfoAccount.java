package usecases;

import datatransferobject.IRequestHandle;
import datatransferobject.Request;
import datatransferobject.Response;
import entities.BankAccount;
import entities.User;

public class DisplayInfoAccount {
    private IRequestHandle requestHandle;

    public DisplayInfoAccount(IRequestHandle requestHandle) {
        this.requestHandle = requestHandle;
    }

    public Response displayInfo(Request req, BankAccount bankAccount) {
        User user = bankAccount.getUser();
        if (user == null) {
            return new Response("No user information associated with this account.");
        }

        // Assume BankAccount has a getBalance() method to retrieve the balance.
        String balanceInfo = String.format("Your current balance: %.2f", bankAccount.getBalance());
        String capInfo = String.format("Your current cap is : %.2f", bankAccount.getCap());

        String info = "User Information:\n" +
                "First Name: " + user.getFirstName() + "\n" +
                "Last Name: " + user.getLastName() + "\n" +
                "Email: " + user.getEmail() + "\n" +
                "Phone Number: " + user.getPhoneNumber() + "\n" +
                "Address: " + user.getAddress() + "\n" +
                capInfo + "\n" +
                balanceInfo;
        return new Response(info);
    }
}
