package usecases;

import datatransferobject.Request;
import datatransferobject.Response;
import datatransferobject.IRequestHandle;
import entities.BankAccount;

public class BankTransfer {
    private IRequestHandle requestHandle;

    public BankTransfer(IRequestHandle requestHandle) {
        this.requestHandle = requestHandle;
    }

    public Response transferMoney(Request req, BankAccount sourceAccount, BankAccount targetAccount) {
        double transferAmount = Double.parseDouble(req.getReqData());
        if (sourceAccount.getBalance() >= transferAmount) {
            sourceAccount.setBalance(sourceAccount.getBalance() - transferAmount);
            targetAccount.setBalance(targetAccount.getBalance() + transferAmount);
            return new Response("Money transferred successfully.");
        } else {
            return new Response("Insufficient funds in the source account.");
        }
    }
}
