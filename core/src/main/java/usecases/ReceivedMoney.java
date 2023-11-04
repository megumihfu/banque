package usecases;

import datatransferobject.Request;
import datatransferobject.Response;
import datatransferobject.IRequestHandle;
import entities.BankAccount;

public class ReceivedMoney {
    private IRequestHandle requestHandle;

    public ReceivedMoney(IRequestHandle requestHandle) {
        this.requestHandle = requestHandle;
    }

    public Response receiveMoney(Request req, BankAccount targetAccount) {
        double receivedAmount = Double.parseDouble(req.getReqData());

        if (receivedAmount <= 0) {
            return new Response("Invalid amount. Amount should be greater than 0.");
        }
        return new Response("Money received successfully. New balance: " + targetAccount.getBalance());
    }

    public String messageReceived(BankAccount targetAccount){
        return "New balance is : "+targetAccount.getBalance();
    }

    public void notifyTransferResult(BankAccount sourceAccount, BankAccount targetAccount, boolean b) {
    }
}
