package controller;

import model.*;
import service.calculeTauxTask;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CompteController {

    static Scanner input = new Scanner(System.in);

    public static void choix() {
        int choix = -1;
        while (choix != 0) {
            System.out.println("||=========================================||");
            System.out.println("||           Choisissez une option         ||");
            System.out.println("||=========================================||");
            System.out.println("1. Ajouter un nouveau Compte Courant");
            System.out.println("2. Ajouter un nouveau Compte Épargne");
            System.out.println("0. Quitter");
            System.out.println("----------------------------------------");
            System.out.print("Veuillez entrer votre choix : ");
            try {
                if (input.hasNextInt()) {
                    choix = input.nextInt();
                    input.nextLine();
                } else {
                    throw new IllegalArgumentException("Entrée invalide. Veuillez entrer un nombre.");
                }
                switch (choix) {
                    case 1:
                        addCompteCourant();
                        break;
                    case 2:
                        addCompteEpargne();
                        break;
                    case 0:
                        choix = 0;
                        break;
                    default:
                        System.out.println("Option invalide");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                input.nextLine();
            }
        }
    }

    public static void addCompteCourant() {
        try {
            System.out.println("||=========================================||");
            System.out.println("||       Ajouter un nouveau compte         ||");
            System.out.println("||=========================================||");
            System.out.println("Entrez le nom du client : ");
            String nom = input.nextLine();
            System.out.println("Entrez le prénom du client : ");
            String prenom = input.nextLine();
            System.out.println("Entrez le solde initial : ");
            double solde = input.nextDouble();
            System.out.println("Entrez le découvert autorisé : ");
            double decouvert = input.nextDouble();
            input.nextLine();
            System.out.println("||=========================================||");

            CompteCourant compteCourant = new CompteCourant(0, solde, nom, prenom, "", "", decouvert);
            compteCourant.addCompte(compteCourant.getnumeroCompte(), compteCourant);
            System.out.println("||=========================================||");
            System.out.println("Compte créé avec succès");
            System.out.println("Numéro de compte : " + compteCourant.getnumeroCompte());
            System.out.println("Solde : " + compteCourant.getsolde());
            System.out.println("Nom : " + compteCourant.getnom() + " " + compteCourant.getPrenom());
            System.out.println("Date de création : " + compteCourant.getDateCreation());
            System.out.println("Découvert : " + compteCourant.getDecouvert());
            System.out.println("||=========================================||");
        } catch (IllegalArgumentException e) {
            System.out.println("Échec de la création du compte : " + e.getMessage());
        }
    }

    public static void addCompteEpargne() {
        try {
            System.out.println("||=========================================||");
            System.out.println("||       Ajouter un nouveau compte         ||");
            System.out.println("||=========================================||");
            System.out.println("Entrez le nom du client : ");
            String nom = input.nextLine();
            System.out.println("Entrez le prénom du client : ");
            String prenom = input.nextLine();
            System.out.println("Entrez le solde initial : ");
            double solde = input.nextDouble();
            System.out.println("Entrez le taux d'intérêt : ");
            double tauxInteret = input.nextDouble();
            input.nextLine();
            System.out.println("||=========================================||");

            CompteEpargne compteEpargne = new CompteEpargne(0, solde, nom, prenom, "", "", tauxInteret);
            compteEpargne.addCompte(compteEpargne.getnumeroCompte(), compteEpargne);
            new calculeTauxTask().start(compteEpargne);
            System.out.println("||=========================================||");
            System.out.println("Compte créé avec succès");
            System.out.println("Numéro de compte : " + compteEpargne.getnumeroCompte());
            System.out.println("Solde : " + compteEpargne.getsolde());
            System.out.println("Nom : " + compteEpargne.getnom() + " " + compteEpargne.getPrenom());
            System.out.println("Date de création : " + compteEpargne.getDateCreation());
            System.out.println("Taux d'intérêt : " + compteEpargne.getTauxInteret());
            System.out.println("||=========================================||");
        } catch (IllegalArgumentException e) {
            System.out.println("Échec de la création du compte : " + e.getMessage());
        }
    }

    public static void afficherComptes() {
        System.out.println("||=========================================||");
        System.out.println("||        Afficher tous les comptes        ||");
        System.out.println("||=========================================||");

        HashMap<String, Compte> comptes = Compte.getComptes();
        if (comptes.isEmpty()) {
            System.out.println("Aucun compte disponible.");
            return;
        }

        for (Compte compte : comptes.values()) {
            if (compte instanceof CompteCourant) {
                CompteCourant cc = (CompteCourant) compte;
                System.out.println("Numéro de compte : " + cc.getnumeroCompte());
                System.out.println("Nom : " + cc.getnom() + " " + cc.getPrenom());
                System.out.println("Solde : " + cc.getsolde());
                System.out.println("Date de création : " + cc.getDateCreation());
                System.out.println("Découvert : " + cc.getDecouvert());
                System.out.println("Type : Compte Courant");
            } else if (compte instanceof CompteEpargne) {
                CompteEpargne ce = (CompteEpargne) compte;
                System.out.println("Numéro de compte : " + ce.getnumeroCompte());
                System.out.println("Nom : " + ce.getnom() + " " + ce.getPrenom());
                System.out.println("Solde : " + ce.getsolde());
                System.out.println("Date de création : " + ce.getDateCreation());
                System.out.println("Taux d'intérêt : " + ce.getTauxInteret());
                System.out.println("Type : Compte Épargne");
            }
            System.out.println("-----------------------------------------");
        }
    }

    public static void afficherDetail() {
        try {
            System.out.println("||=========================================||");
            System.out.println("||        Détails du compte                ||");
            System.out.println("||=========================================||");
            System.out.println("Entrez le numéro de compte : ");
            String numeroCompte = input.nextLine();

            HashMap<String, Compte> comptes = Compte.getComptes();
            Compte compte = comptes.get(numeroCompte);
            if (compte == null) {
                throw new IllegalStateException("Compte non trouvé.");
            }

            if (compte instanceof CompteCourant) {
                CompteCourant cc = (CompteCourant) compte;
                System.out.println("Numéro de compte : " + cc.getnumeroCompte());
                System.out.println("Nom : " + cc.getnom() + " " + cc.getPrenom());
                System.out.println("Solde : " + cc.getsolde());
                System.out.println("Date de création : " + cc.getDateCreation());
                System.out.println("Découvert : " + cc.getDecouvert());
                System.out.println("Type : Compte Courant");
            } else if (compte instanceof CompteEpargne) {
                CompteEpargne ce = (CompteEpargne) compte;
                System.out.println("Numéro de compte : " + ce.getnumeroCompte());
                System.out.println("Nom : " + ce.getnom() + " " + ce.getPrenom());
                System.out.println("Solde : " + ce.getsolde());
                System.out.println("Date de création : " + ce.getDateCreation());
                System.out.println("Taux d'intérêt : " + ce.getTauxInteret());
                System.out.println("Type : Compte Épargne");
            }
            System.out.println("||=========================================||");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void calculerInteret() {
        try {
            System.out.println("||=========================================||");
            System.out.println("||       Calculer l'intérêt du compte      ||");
            System.out.println("||=========================================||");
            System.out.println("Entrez le numéro de compte : ");
            String numeroCompte = input.nextLine();

            HashMap<String, Compte> comptes = Compte.getComptes();
            Compte compte = comptes.get(numeroCompte);
            if (compte == null) {
                throw new IllegalStateException("Compte non trouvé.");
            }

            if (compte instanceof CompteEpargne) {
                CompteEpargne ce = (CompteEpargne) compte;
                System.out.println("Vous avez un intérêt de " + ce.calculerInteret());
            } else {
                System.out.println("Vous avez un intérêt de 0");
            }
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void retrait() {
        try {
            System.out.println("||=========================================||");
            System.out.println("||         Retrait sur le compte          ||");
            System.out.println("||=========================================||");
            System.out.println("Entrez le numéro de compte : ");
            String numeroCompte = input.nextLine();
            System.out.println("Entrez le montant à retirer : ");
            double montant = input.nextDouble();
            input.nextLine();
            System.out.println("Entrez la destination : ");
            String destination = input.nextLine();

            HashMap<String, Compte> comptes = Compte.getComptes();
            Compte compte = comptes.get(numeroCompte);
            if (compte == null) {
                throw new IllegalStateException("Compte non trouvé.");
            }

            if (compte instanceof CompteCourant) {
                CompteCourant cc = (CompteCourant) compte;
                cc.retrait(montant);
                Retrait retrait = new Retrait(montant, destination);
                cc.addOperation(cc.getnumeroCompte(), retrait);
                System.out.println("Montant retiré avec succès, nouveau solde : " + cc.getsolde());
            } else if (compte instanceof CompteEpargne) {
                CompteEpargne ce = (CompteEpargne) compte;
                ce.retrait(montant);
                Retrait retrait = new Retrait(montant, destination);
                ce.addOperation(ce.getnumeroCompte(), retrait);
                System.out.println("Montant retiré avec succès, nouveau solde : " + ce.getsolde());
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void virement() {
        try {
            System.out.println("||=========================================||");
            System.out.println("||         Virement entre comptes         ||");
            System.out.println("||=========================================||");
            System.out.println("Entrez le numéro de compte source : ");
            String numeroCompte = input.nextLine();
            System.out.println("Entrez le montant à virer : ");
            double montant = input.nextDouble();
            input.nextLine();
            System.out.println("Entrez le numéro de compte destinataire : ");
            String numeroCompte2 = input.nextLine();

            HashMap<String, Compte> comptes = Compte.getComptes();
            Compte compte = comptes.get(numeroCompte);
            Compte compte2 = comptes.get(numeroCompte2);
            if (compte == null || compte2 == null) {
                throw new IllegalStateException("Un des comptes n'a pas été trouvé.");
            }

            if (compte instanceof CompteCourant) {
                CompteCourant cc = (CompteCourant) compte;
                cc.virement(montant, compte2);
                Retrait retrait = new Retrait(montant, "Transfert à " + compte2.getnom());
                Versement versement = new Versement(montant, "Transfert de " + cc.getnom());
                cc.addOperation(cc.getnumeroCompte(), retrait);
                compte2.addOperation(compte2.getnumeroCompte(), versement);
                System.out.println("Virement effectué avec succès, nouveau solde : " + cc.getsolde());
            } else if (compte instanceof CompteEpargne) {
                CompteEpargne ce = (CompteEpargne) compte;
                ce.virement(montant, compte2);
                Retrait retrait = new Retrait(montant, "Transfert à " + compte2.getnom());
                Versement versement = new Versement(montant, "Transfert de " + ce.getnom());
                ce.addOperation(ce.getnumeroCompte(), retrait);
                compte2.addOperation(compte2.getnumeroCompte(), versement);
                System.out.println("Virement effectué avec succès, nouveau solde : " + ce.getsolde());
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addSold() {
        try {
            System.out.println("||=========================================||");
            System.out.println("||       Augmenter le solde du compte     ||");
            System.out.println("||=========================================||");
            System.out.println("Entrez le numéro de compte : ");
            String numeroCompte = input.nextLine();
            System.out.println("Entrez le montant à ajouter : ");
            double montant = input.nextDouble();
            input.nextLine();
            System.out.println("Entrez la source : ");
            String source = input.nextLine();

            HashMap<String, Compte> comptes = Compte.getComptes();
            Compte compte = comptes.get(numeroCompte);
            if (compte == null) {
                throw new IllegalStateException("Compte non trouvé.");
            }

            if (compte instanceof CompteCourant) {
                CompteCourant cc = (CompteCourant) compte;
                cc.addSolde(montant);
                Versement versement = new Versement(montant, source);
                cc.addOperation(cc.getnumeroCompte(), versement);
                System.out.println("Montant ajouté avec succès, nouveau solde : " + cc.getsolde());
            } else if (compte instanceof CompteEpargne) {
                CompteEpargne ce = (CompteEpargne) compte;
                ce.addSolde(montant);
                Versement versement = new Versement(montant, source);
                ce.addOperation(ce.getnumeroCompte(), versement);
                System.out.println("Montant ajouté avec succès, nouveau solde : " + ce.getsolde());
            }
            System.out.println("||=========================================||");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getSolde() {
        try {
            System.out.println("||=========================================||");
            System.out.println("||           Solde du compte              ||");
            System.out.println("||=========================================||");
            System.out.println("Entrez le numéro de compte : ");
            String numeroCompte = input.nextLine();

            HashMap<String, Compte> comptes = Compte.getComptes();
            Compte compte = comptes.get(numeroCompte);
            if (compte == null) {
                throw new IllegalStateException("Compte non trouvé.");
            }

            if (compte instanceof CompteCourant) {
                CompteCourant cc = (CompteCourant) compte;
                System.out.println("Solde de votre compte courant : " + cc.getsolde() + " avec découvert de " + cc.getDecouvert());
            } else if (compte instanceof CompteEpargne) {
                CompteEpargne ce = (CompteEpargne) compte;
                System.out.println("Solde de votre compte épargne : " + ce.getsolde());
            }
            System.out.println("||=========================================||");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void afficherOperationOfAccount() {
        try {
            System.out.println("Entrez le numéro de compte : ");
            String numeroCompte = input.nextLine();

            HashMap<String, Compte> comptes = Compte.getComptes();
            Compte compte = comptes.get(numeroCompte);
            if (compte == null) {
                throw new IllegalStateException("Compte non trouvé.");
            }

            HashMap<String, List<operation>> operations = Compte.getOperations();
            List<operation> ops = operations.get(numeroCompte);

            if (ops == null || ops.isEmpty()) {
                System.out.println("Aucune opération trouvée pour ce compte.");
                return;
            }

            System.out.println("||=========================================||");
            System.out.println("||        Opérations du compte            ||");
            System.out.println("||=========================================||");
            System.out.println("Numéro de compte : " + numeroCompte);
            System.out.println("Nom : " + compte.getnom() + " " + compte.getPrenom());
            System.out.println("||=========================================||");
            System.out.println("Opérations :");

            for (operation op : ops) {
                if (op instanceof Retrait) {
                    Retrait rt = (Retrait) op;
                    System.out.println("Retrait de " + op.getMontant() + " vers " + rt.getDestination() + " le " + op.getDateOperation());
                } else if (op instanceof Versement) {
                    Versement vs = (Versement) op;
                    System.out.println("Versement de " + op.getMontant() + " depuis " + vs.getSource() + " le " + op.getDateOperation());
                }
            }
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}