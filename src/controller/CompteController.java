package controller;

import model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import service.calculeTauxTask;

public class CompteController {

    static Scanner input = new Scanner(System.in);

    public static void choix() {
        int choix = -1;
        while (choix != 0) {
            System.out.println("||=========================================||");
            System.out.println("||            Choose an option             ||");
            System.out.println("||=========================================||");
            System.out.println("1.  Add a new Compte Courant");
            System.out.println("2.  Add a new Compte Epargne");
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
                    AddCompteCourant();
                    break;
                case 2:
                    AddCompteEpargne();
                    break;
                case 0:
                    choix = 0;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public static void AddCompteCourant() {
        System.out.println("||=========================================||");
        System.out.println("||            Add a new account            ||");
        System.out.println("||=========================================||");
        System.out.println("Enter the client name: ");
        String nom = input.nextLine();
        System.out.println("Enter the client Prenom: ");
        String prenom = input.nextLine();
        System.out.println("Enter the client balance: ");
        double solde = input.nextDouble();
        System.out.println("Enter the client decouvert: ");
        double decouvert = input.nextDouble();
        input.nextLine();
        System.out.println("||=========================================||");

        try {
            CompteCourant compteCourant = new CompteCourant(0, solde, nom, prenom, "", "", decouvert);
            compteCourant.addCompte(compteCourant.getnumeroCompte(), compteCourant);
            System.out.println("||=========================================||");
            System.out.println("Account created successfully");
            System.out.println("Account number: " + compteCourant.getnumeroCompte());
            System.out.println("Account balance: " + compteCourant.getsolde());
            System.out.println("Account name: " + compteCourant.getnom() + " " + compteCourant.getPrenom());
            System.out.println("Account creation date: " + compteCourant.getDateCreation());
            System.out.println("Account decouvert: " + compteCourant.getDecouvert());
            System.out.println("||=========================================||");
        } catch (Exception e) {
            System.out.println("Account creation failed: " + e.getMessage());
        }
    }

    public static void AddCompteEpargne() {
        System.out.println("||=========================================||");
        System.out.println("||            Add a new account            ||");
        System.out.println("||=========================================||");
        System.out.println("Enter the client name: ");
        String nom = input.nextLine();
        System.out.println("Enter the client Prenom: ");
        String prenom = input.nextLine();
        System.out.println("Enter the client balance: ");
        double solde = input.nextDouble();
        System.out.println("Enter the client taux Interet: ");
        double tauxInteret = input.nextDouble();
        System.out.println("||=========================================||");

        try {
            CompteEpargne compteEpargne = new CompteEpargne(0, solde, nom, prenom, "", "", tauxInteret);
            compteEpargne.addCompte(compteEpargne.getnumeroCompte(), compteEpargne);
// Start the scheduled task
            new calculeTauxTask().start(compteEpargne);
            System.out.println("||=========================================||");
            System.out.println("Account created successfully");
            System.out.println("Account number: " + compteEpargne.getnumeroCompte());
            System.out.println("Account balance: " + compteEpargne.getsolde());
            System.out.println("Account name: " + compteEpargne.getnom() + " " + compteEpargne.getPrenom());
            System.out.println("Account creation date: " + compteEpargne.getDateCreation());
            System.out.println("Account tauxInteret: " + compteEpargne.getTauxInteret());
            System.out.println("||=========================================||");
        } catch (Exception e) {
            System.out.println("Account creation failed: " + e.getMessage());
        }
    }

    public static void afficherComptes() {
        System.out.println("||=========================================||");
        System.out.println("||            Show All Accounts            ||");
        System.out.println("||=========================================||");

        HashMap<String, Compte> comptes = Compte.getComptes();
        if (comptes.isEmpty()) {
            System.out.println("Aucun compte disponible.");
            return;
        }

        for (Compte compte : comptes.values()) {
            if (compte instanceof CompteCourant) {
                CompteCourant cc = (CompteCourant) compte;
                System.out.println("Account number: " + cc.getnumeroCompte());
                System.out.println("Name: " + cc.getnom() + " " + cc.getPrenom());
                System.out.println("Balance: " + cc.getsolde());
                System.out.println("Creation date: " + cc.getDateCreation());
                System.out.println("Decouvert: " + cc.getDecouvert());
                System.out.println("Type: Compte Courant");
            } else if (compte instanceof CompteEpargne) {
                CompteEpargne ce = (CompteEpargne) compte;
                System.out.println("Account number: " + ce.getnumeroCompte());
                System.out.println("Name: " + ce.getnom() + " " + ce.getPrenom());
                System.out.println("Balance: " + ce.getsolde());
                System.out.println("Creation date: " + ce.getDateCreation());
                System.out.println("Taux Interet: " + ce.getTauxInteret());
                System.out.println("Type: Compte Epargne");
            }
            System.out.println("-----------------------------------------");
        }
    }

    public static void afficherDetail() {
        System.out.println("||=========================================||");
        System.out.println("||            Show Account Details        ||");
        System.out.println("||=========================================||");
        System.out.println("Enter the account number: ");
        String numeroCompte = input.nextLine();

        HashMap<String, Compte> comptes = Compte.getComptes();
        Compte compte = comptes.get(numeroCompte);

        if (compte == null) {
            System.out.println("Account not found.");
            return;
        }

        if (compte instanceof CompteCourant) {
            CompteCourant cc = (CompteCourant) compte;
            System.out.println("Account number: " + cc.getnumeroCompte());
            System.out.println("Name: " + cc.getnom() + " " + cc.getPrenom());
            System.out.println("Balance: " + cc.getsolde());
            System.out.println("Creation date: " + cc.getDateCreation());
            System.out.println("Decouvert: " + cc.getDecouvert());
            System.out.println("Type: Compte Courant");
        } else if (compte instanceof CompteEpargne) {
            CompteEpargne ce = (CompteEpargne) compte;
            System.out.println("Account number: " + ce.getnumeroCompte());
            System.out.println("Name: " + ce.getnom() + " " + ce.getPrenom());
            System.out.println("Balance: " + ce.getsolde());
            System.out.println("Creation date: " + ce.getDateCreation());
            System.out.println("Taux Interet: " + ce.getTauxInteret());
            System.out.println("Type: Compte Epargne");
        }
        System.out.println("||=========================================||");
    }

    public static void calculerInteret() {
        System.out.println("||=========================================||");
        System.out.println("||          Descover Account Interet       ||");
        System.out.println("||=========================================||");
        System.out.println("Enter the account number: ");
        String numeroCompte = input.nextLine();
        HashMap<String, Compte> comptes = Compte.getComptes();
        Compte compte = comptes.get(numeroCompte);
        if (compte == null) {
            System.out.println("Account not found.");
            return;
        }
        if (compte instanceof CompteEpargne) {
            CompteEpargne cc = (CompteEpargne) compte;
            System.out.println("Vous aver un interet de " + cc.calculerInteret());
        } else if (compte instanceof CompteCourant) {
            System.out.println("Vous aver un interet de 0");
        }
    }

    public static void retrait() {
        System.out.println("||=========================================||");
        System.out.println("||          Retrait Account Amount       ||");
        System.out.println("||=========================================||");
        System.out.println("Enter the account number: ");
        String numeroCompte = input.nextLine();
        System.out.println("Enter the amount to withdraw: ");
        double montant = input.nextDouble();
        System.out.println("Entrer the destination: ");
        String distination = input.nextLine();
        HashMap<String, Compte> comptes = Compte.getComptes();
        Compte compte = comptes.get(numeroCompte);
        if (compte == null) {
            System.out.println("Account not found.");
            return;
        }
        if (compte instanceof CompteCourant) {
            CompteCourant cc = (CompteCourant) compte;
            if (cc.getsolde() < montant) {
                if(montant<=cc.getsolde()+cc.getDecouvert()){
                    cc.retrait(montant);
                }
            }

            Retrait retrait = new Retrait(montant, distination);
            cc.addOperation(cc.getnumeroCompte(), retrait);
            System.out.println("Montant retirer avec succes ur solde now is" + cc.getsolde());
        } else if (compte instanceof CompteEpargne) {
            CompteEpargne ce = (CompteEpargne) compte;
            ce.retrait(montant);
            Retrait retrait = new Retrait(montant, distination);
            ce.addOperation(ce.getnumeroCompte(), retrait);
            System.out.println("Montant retirer avec succes ur solde now is" + ce.getsolde());
        }
    }


    public static void virement() {
        System.out.println("||=========================================||");
        System.out.println("||          Virement Account Amount       ||");
        System.out.println("||=========================================||");
        System.out.println("Enter the account number: ");
        String numeroCompte = input.nextLine();
        if (numeroCompte.isEmpty()) {
            System.out.println("Account number 1 is empty.");
            return;
        }
        System.out.println("Enter the amount to withdraw: ");
        double montant = input.nextDouble();
        if (montant <= 0) {
            System.out.println("Montant must be positive");
            return;
        }
        input.nextLine();

        System.out.println("Enter the account number to transfer: ");
        String numeroCompte2 = input.nextLine();
        if (numeroCompte2.isEmpty()) {
            System.out.println("Account number 2 is empty.");
            return;
        }

        HashMap<String, Compte> comptes = Compte.getComptes();
        Compte compte = comptes.get(numeroCompte);
        Compte compte2 = comptes.get(numeroCompte2);
        if (compte instanceof CompteCourant) {
            CompteCourant cc = (CompteCourant) compte;
            cc.virement(montant, compte2);
            String destination1 = "Transfert";
            String destination2 = cc.getnom() + " " + cc.getPrenom();
            Retrait retrait = new Retrait(montant, destination2);
            Versement versement = new Versement(montant, destination2);
            cc.addOperation(cc.getnumeroCompte(), retrait);
            cc.addOperation(compte2.getnumeroCompte(), versement);
        } else if (compte instanceof CompteEpargne) {
            CompteEpargne ce = (CompteEpargne) compte;
            ce.virement(montant, compte2);
            String destination1 = "Transfert";
            String destination2 = ce.getnom() + " " + ce.getPrenom();
            Retrait retrait = new Retrait(montant, destination1);
            Versement versement = new Versement(montant, destination2);
            ce.addOperation(ce.getnumeroCompte(), retrait);
            ce.addOperation(compte2.getnumeroCompte(), versement);
        }
    }

    public static void addSold() {
        System.out.println("||=========================================||");
        System.out.println("||          Augmenter Account balance       ||");
        System.out.println("||=========================================||");
        System.out.println("Enter the account number: ");
        String numeroCompte = input.nextLine();
        System.out.println("Enter the amount to add: ");
        double montant = input.nextDouble();
        System.out.println("Entrer la source: ");
        String source = input.nextLine();
        HashMap<String, Compte> comptes = Compte.getComptes();
        Compte compte = comptes.get(numeroCompte);
        if (compte == null) {
            System.out.println("Account not found.");
            return;
        }

        if (compte instanceof CompteCourant) {
            CompteCourant cc = (CompteCourant) compte;
            cc.addSolde(cc.getsolde() + montant);
            Versement versement = new Versement(montant, source);
            cc.addOperation(cc.getnumeroCompte(), versement);
            System.out.println("Montant Ajouter avec succes ur solde now is" + cc.getsolde());
        } else if (compte instanceof CompteEpargne) {
            CompteEpargne ce = (CompteEpargne) compte;
            ce.retrait(montant);
            Versement versement = new Versement(montant, source);
            ce.addOperation(ce.getnumeroCompte(), versement);
            System.out.println("Montant Ajouter avec succes ur solde now is" + ce.getsolde());
        }
        System.out.println("||=========================================||");
    }

    public static void getSolde() {
        System.out.println("||=========================================||");
        System.out.println("||           Account Balance          ||");
        System.out.println("||=========================================||");
        System.out.println("Enter the account number: ");
        String numeroCompte = input.nextLine();
        HashMap<String, Compte> comptes = Compte.getComptes();
        Compte compte = comptes.get(numeroCompte);
        if (compte == null) {
            System.out.println("Account not found.");
        }

        if (compte instanceof CompteCourant) {
            CompteCourant cc = (CompteCourant) compte;
            System.out.println("Solde de votre compte courant est de " + cc.getsolde() + " avec decouvert de " + cc.getDecouvert());
        } else if (compte instanceof CompteEpargne) {
            CompteEpargne ce = (CompteEpargne) compte;
            System.out.println("Solde de votre compte epargne est de " + ce.getsolde());
        }
        System.out.println("||=========================================||");
    }


    public static void afficherOperationOfAccount() {
        System.out.println("Enter the account number: ");
        String numeroCompte = input.nextLine();

        HashMap<String, Compte> comptes = Compte.getComptes();
        Compte compte = comptes.get(numeroCompte);

        if (compte == null) {
            System.out.println("Compte introuvable !");
            return;
        }

        HashMap<String, List<operation>> operations = Compte.getOperations();
        List<operation> ops = operations.get(numeroCompte);

        if (ops == null || ops.isEmpty()) {
            System.out.println("Aucune opération trouvée pour ce compte.");
            return;
        }

        System.out.println("||=========================================||");
        System.out.println("||           Account Operations            ||");
        System.out.println("||=========================================||");
        System.out.println("Account number: " + numeroCompte);
        System.out.println("Account name: " + compte.getnom() + " " + compte.getPrenom());
        System.out.println("||=========================================||");
        System.out.println("Operations:");

        for (operation op : ops) {
            if (op instanceof Retrait) {
                Retrait rt = (Retrait) op;
                System.out.println("Retrait de " + op.getMontant() + " vers " + rt.getDestination() + " le " + op.getDateOperation());
            } else if (op instanceof Versement) {
                Versement vs = (Versement) op;
                System.out.println("Versement de " + op.getMontant() + " depuis " + vs.getSource() + " le " + op.getDateOperation());
            }
        }
    }


}