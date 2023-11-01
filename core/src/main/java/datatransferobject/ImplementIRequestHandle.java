package datatransferobject;

import entities.BankAccount;

public class ImplementIRequestHandle implements IRequestHandle{
    private BankAccount bankAccount;

    @Override
    public Response handleRequest(Request req) {
        String requestData = req.getReqData();
        Response response;

        if ("transfer_money".equals(requestData)) {
            response = new Response("Transfert d'argent effectué avec succès.");
        } else if ("check_balance".equals(requestData)) {
            response = new Response("Solde actuel : "+this.bankAccount.getBalance());
        } else {
            response = new Response("Commande inconnue.");
        }
        return response;
    }
}
