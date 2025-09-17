package model;

public class CompteCourant extends Compte {
    private double decouvert;

    public CompteCourant(int numeroCompte, double solde, String nom, String prenom, String numero, String dateCreation, double decouvert) {
        super(numeroCompte, solde, nom, prenom, numero, dateCreation);
        this.decouvert = decouvert;
    }

    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }
    public void retrait(double montant){
        if(montant <= this.solde + this.decouvert){
            this.solde -= montant;
        }else{
            System.out.println("Montant not enoght");
        }
    }
    public void addSolde(double montant){
        this.solde += montant;
    }

    public void virement(double montant, Compte compte){
        if(montant <= this.solde + this.decouvert){
            compte.addSolde(montant);
            this.solde -= montant;
        }
    }
    @Override
    public double calculerInteret(){
        return 0;
    }

    @Override
    public String toString() {
        return super.toString() + " | CompteCourant [decouvert=" + decouvert + "]";
    }
}

















