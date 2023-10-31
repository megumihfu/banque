package usecases;

import datatransferobject.Request;
import datatransferobject.Response;
import datatransferobject.IRequestHandle;
import entities.BankAccount;

public class CapManagement {
    private IRequestHandle requestHandle;

    public CapManagement(IRequestHandle requestHandle) {
        this.requestHandle = requestHandle;
    }

    public Response manageCapital(Request req, BankAccount bankAccount) {
        double newLimit = Double.parseDouble(req.getReqData());
        bankAccount.setLimit(newLimit);

        return new Response("Cap management was updated with success.");
    }
}
