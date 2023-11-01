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

        String info = "User Information:\n" +
                "First Name: " + user.getFirstName() + "\n" +
                "Last Name: " + user.getLastName() + "\n" +
                "Email: " + user.getEmail() + "\n" +
                "Phone Number: " + user.getPhoneNumber() + "\n" +
                "Address: " + user.getAddress();

        return new Response(info);
    }
}
