package datatransferobject;

public class ImplementIRequestHandle implements IRequestHandle{
     @Override
     public Response handleRequest(Request req) {
         String requestData = req.getReqData();
         Response response;

         if ("transfer_money".equals(requestData)) {
             response = new Response("Transfert d'argent effectué avec succès.");
         } else if ("check_balance".equals(requestData)) {
             response = new Response("Solde actuel : 1000 euros");
         } else {
             response = new Response("Commande inconnue.");
         }

         return response;
     }
}
