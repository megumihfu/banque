package controller;

import interactor.ReceivedMoneyInteractor;
import model.MoneyTransferRequest;
import presenter.MoneyTransferPresenter;
import view.ConsoleView;


public class ReceivedMoneyController {
    /* faire méthode run()
    Implémentez la logique des contrôleurs :

    Chaque contrôleur doit avoir une méthode run() qui gère l'interaction de l'utilisateur pour la fonctionnalité spécifique.
    Dans la méthode run(), suivez les étapes décrites dans l'étape 17, mais adaptez-les à chaque fonctionnalité :
        Créez une demande (Request) appropriée en fonction de l'opération que l'utilisateur souhaite effectuer.
        Instanciez l'interactor correspondant (par exemple, "GestionArgentInteractor," "AccesRIBInteractor," etc.) et injectez les gateways nécessaires dans le constructeur du contrôleur.
        Appelez la méthode de l'interactor pour gérer la demande et récupérer la réponse.
        Si vous avez un présentateur (ce qui est une bonne pratique pour gérer la présentation des résultats à l'utilisateur), instanciez-le et demandez-lui de gérer la réponse. Sinon, affichez simplement la réponse à la console.*/

    public class ReceivedMoneyController {

        private final ReceivedMoneyInteractor interactor;
        private final MoneyTransferPresenter presenter;

        public ReceivedMoneyController() {
            // Initialisez l'interactor, le présentateur et d'autres dépendances si nécessaire.
            interactor = new ReceivedMoneyInteractor();
            presenter = new MoneyTransferPresenter();
        }

        public void run() {
            ConsoleView.displayMessage("Bienvenue dans le contrôleur de réception d'argent.");

            // L'utilisateur peut saisir les détails de la transaction ici.
            MoneyTransferRequest request = getUserInput(); // Vous devez créer cette méthode pour obtenir les détails de la transaction.

            // Traitez la demande en utilisant l'interactor.
            String result = interactor.handleMoneyTransfer(request);

            // Présentez le résultat à l'utilisateur en utilisant le présentateur.
            presenter.displayTransferResult(result);
        }

        // Vous devrez implémenter des méthodes pour obtenir les détails de la transaction, par exemple :
        private MoneyTransferRequest getUserInput() {
            // Demandez à l'utilisateur de saisir les détails de la transaction et créez l'objet MoneyTransferRequest.
            // Vous devrez probablement utiliser Scanner ou une autre méthode d'entrée utilisateur.
            // Cette méthode doit être implémentée pour votre cas d'utilisation spécifique.
            // Par exemple :
            ConsoleView.displayMessage("Entrez le montant à recevoir : ");
            double amount = ConsoleView.getDoubleInput();
            ConsoleView.displayMessage("Entrez le numéro de compte de l'expéditeur : ");
            String senderAccountNumber = ConsoleView.getStringInput();
            // ... d'autres détails de la transaction.
            return new MoneyTransferRequest(amount, senderAccountNumber, /* autres détails */);
        }

        public static void main(String[] args) {
            ReceivedMoneyController controller = new ReceivedMoneyController();
            controller.run();
        }
    }

}
