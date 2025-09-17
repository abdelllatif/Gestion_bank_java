import controller.CompteController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int choix = -1;
        Scanner input = new Scanner(System.in);
        while (choix != 0) {
            System.out.println("||=========================================||");
            System.out.println("||          BANK MANAGEMENT SYSTEM         ||");
            System.out.println("||=========================================||");
            System.out.println("1.  Create a new account");
            System.out.println("2.  Retrait money");
            System.out.println("3.  Effectuer un virement");
            System.out.println("4.  Voir interet d'un compte");
            System.out.println("5.  View account details");
            System.out.println("6.  View all accounts");
            System.out.println("7.  View account operations");
            System.out.println("0.  Exit");
            System.out.println("----------------------------------------");
            System.out.print(" Please enter your choice: ");

            if (input.hasNextInt()) {
                choix = input.nextInt();
                input.nextLine();
            } else {
                System.out.println("Invalid input. Please enter a number.");
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
                    CompteController.virement();
                    break;
                case 4:
                    CompteController.calculerInteret();
                    break;
                case 5:
                    CompteController.afficherDetail();
                    break;
                case 6:
                    CompteController.afficherComptes();
                    break;
                case 0:
                    System.out.println("Thank you for using our service");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
