package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class Compte {
    private static int number = 11111;
    protected String numeroCompte;
    protected double solde;
    protected String nom;
    protected String prenom;
    protected String dateCreation;
    protected static HashMap<String, Compte> comptes = new HashMap<>();
    protected static HashMap<String, List<operation>> operations = new HashMap<>();

    public Compte(int numeroCompte, double solde, String nom, String prenom, String numero, String dateCreation) {
        LocalDateTime dateNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.numeroCompte = "Ctn-" + ++number;
        this.solde = solde;
        this.nom = nom;
        this.prenom = prenom;
        this.dateCreation = dateNow.format(formatter);
    }

    public void addCompte(String id, Compte compte) {
        comptes.put(id, compte);
    }

    public static void addOperation(String numeroCompte, operation op) {
        operations.computeIfAbsent(numeroCompte, k -> new ArrayList<>());
        operations.get(numeroCompte).add(op);
    }

    public static HashMap<String, Compte> getComptes() {
        return comptes;
    }

    public static HashMap<String, List<operation>> getOperations() {
        return operations;
    }

    public String getnumeroCompte() {
        return numeroCompte;
    }

    public double getsolde() {
        return solde;
    }

    public String getnom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void addSolde(double montant) {
    }

    public void retrait(double montant) {
    }

    public double calculerInteret() {
        return 0;
    }


    public void virement(double montant, Compte compte) {
    }

    ;

    @Override
    public String toString() {
        return "Compte [numero=" + numeroCompte + ", solde=" + solde + ", nom=" + nom + ", prenom=" + prenom + ", date=" + dateCreation + "]";
    }
}
