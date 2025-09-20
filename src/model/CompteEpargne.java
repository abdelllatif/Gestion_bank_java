package model;

public class CompteEpargne extends Compte {
    private double tauxInteret;
    private Boolean running = true;

    public CompteEpargne(int numeroCompte, double solde, String nom, String prenom, String numero, String dateCreation, double tauxInteret) {
        super(numeroCompte, solde, nom, prenom, numero, dateCreation);
        if (solde < 0) throw new IllegalArgumentException("Le solde ne peut pas être négatif");
        if (tauxInteret < 0) throw new IllegalArgumentException("Le taux d'intérêt ne peut pas être négatif");
        this.tauxInteret = tauxInteret;
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(double tauxInteret) {
        if (tauxInteret < 0) throw new IllegalArgumentException("Le taux d'intérêt ne peut pas être négatif");
        this.tauxInteret = tauxInteret;
    }

    public void retrait(double montant) {
        if (montant <= 0) throw new IllegalArgumentException("Le montant doit être positif");
        if (montant > this.solde) throw new IllegalStateException("Solde insuffisant pour le retrait");
        this.solde -= montant;
    }

    public void addSolde(double montant) {
        if (montant <= 0) throw new IllegalArgumentException("Le montant doit être positif");
        this.solde += montant;
    }

    public void virement(double montant, Compte compte) {
        if (montant <= 0) throw new IllegalArgumentException("Le montant doit être positif");
        if (montant > this.solde) throw new IllegalStateException("Solde insuffisant pour le virement");
        this.solde -= montant;
        compte.addSolde(montant);
    }

    @Override
    public double calculerInteret() {
        this.solde += this.solde * (this.tauxInteret / 100);
        return this.solde;
    }

    @Override
    public String toString() {
        return super.toString() + " | CompteEpargne [tauxInteret=" + tauxInteret + "]";
    }
}