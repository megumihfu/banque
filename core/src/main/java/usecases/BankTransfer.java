package usecases;

import datatransferobject.Request;
import datatransferobject.Response;
import datatransferobject.IRequestHandle;
import entities.BankAccount;

public class BankTransfer {
    private final IRequestHandle requestHandle;

    public BankTransfer(IRequestHandle requestHandle) {
        this.requestHandle = requestHandle;
    }

    public Response transferMoney(Request req, BankAccount sourceAccount, BankAccount targetAccount) {
        double transferAmount = Double.parseDouble(req.getReqData());
        if (sourceAccount.getBalance() >= transferAmount) {
            sourceAccount.setBalance(sourceAccount.getBalance() - transferAmount);
            targetAccount.setBalance(targetAccount.getBalance() + transferAmount);

            ReceivedMoney receivedMoneyUseCase = new ReceivedMoney(requestHandle);
            Response receivedMoneyResponse = receivedMoneyUseCase.receiveMoney(req, targetAccount);
            Transaction transaction = new Transaction(transferAmount, sourceAccount.getUser().getFirstName(), targetAccount.getUser().getFirstName());
            sourceAccount.addOutgoingTransaction(transaction); // Vous devez aussi gérer les transactions sortantes
            targetAccount.addIncomingTransaction(transaction);


            return new Response(receivedMoneyUseCase.messageReceived(targetAccount));
        } else {
            return new Response("Insufficient funds in the source account.");
        }
    }
}
