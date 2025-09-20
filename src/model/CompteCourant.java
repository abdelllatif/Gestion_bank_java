package model;

public class CompteCourant extends Compte {
    private double decouvert;

    public CompteCourant(int numeroCompte, double solde, String nom, String prenom, String numero, String dateCreation, double decouvert) {
        super(numeroCompte, solde, nom, prenom, numero, dateCreation);
        if (solde < 0) throw new IllegalArgumentException("Le solde ne peut pas être négatif");
        if (decouvert < 0) throw new IllegalArgumentException("Le découvert ne peut pas être négatif");
        this.decouvert = decouvert;
    }

    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        if (decouvert < 0) throw new IllegalArgumentException("Le découvert ne peut pas être négatif");
        this.decouvert = decouvert;
    }

    public void retrait(double montant) {
        if (montant <= 0) throw new IllegalArgumentException("Le montant doit être positif");
        if (montant > this.solde + this.decouvert) throw new IllegalStateException("Solde insuffisant pour le retrait");
        if (montant <= this.solde) {
            this.solde -= montant;
        } else {
            this.decouvert -= (montant - this.solde);
            this.solde = 0;
        }
    }

    public void addSolde(double montant) {
        if (montant <= 0) throw new IllegalArgumentException("Le montant doit être positif");
        this.solde += montant;
    }

    public void virement(double montant, Compte compte) {
        if (montant <= 0) throw new IllegalArgumentException("Le montant doit être positif");
        if (montant > this.solde + this.decouvert) throw new IllegalStateException("Solde insuffisant pour le virement");
        if (montant <= this.solde) {
            this.solde -= montant;
        } else {
            this.decouvert -= (montant - this.solde);
            this.solde = 0;
        }
        compte.addSolde(montant);
    }

    @Override
    public double calculerInteret() {
        return 0;
    }

    @Override
    public String toString() {
        return super.toString() + " | CompteCourant [decouvert=" + decouvert + "]";
    }
}