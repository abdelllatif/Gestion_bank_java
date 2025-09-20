import controller.CompteController;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int choix = -1;
        Scanner input = new Scanner(System.in);
        while (choix != 0) {
            System.out.println("||=========================================||");
            System.out.println("||       SYSTÈME DE GESTION BANCAIRE       ||");
            System.out.println("||=========================================||");
            System.out.println("1.  Créer un nouveau compte");
            System.out.println("2.  Retirer de l'argent");
            System.out.println("3.  Déposer de l'argent");
            System.out.println("4.  Effectuer un virement");
            System.out.println("5.  Voir l'intérêt d'un compte");
            System.out.println("6.  Consulter les détails d'un compte");
            System.out.println("7.  Voir tous les comptes");
            System.out.println("8.  Consulter les opérations d'un compte");
            System.out.println("0.  Quitter");
            System.out.println("----------------------------------------");
            System.out.print("Veuillez entrer votre choix : ");

            if (input.hasNextInt()) {
                choix = input.nextInt();
                input.nextLine();
            } else {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                input.nextLine();
                continue;
            }

            switch (choix) {
                case 1:
                    CompteController.choix();
                    break;
                case 2:
                    CompteController.retrait();
                    break;
                case 3:
                    CompteController.addSold();
                    break;
                case 4:
                    CompteController.virement();
                    break;
                case 5:
                    CompteController.calculerInteret();
                    break;
                case 6:
                    CompteController.afficherDetail();
                    break;
                case 7:
                    CompteController.afficherComptes();
                    break;
                case 8:
                    CompteController.afficherOperationOfAccount();
                    break;
                case 0:
                    System.out.println("Merci d'avoir utilisé notre service.");
                    break;
                default:
                    System.out.println("Option invalide.");
            }
        }
    }
}
