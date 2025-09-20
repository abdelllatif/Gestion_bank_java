package model;

public class CompteEpargne extends Compte {
    private double tauxInteret;

    public CompteEpargne(int numeroCompte, double solde, String nom, String prenom, String numero, String dateCreation, double tauxInteret) {
        super(numeroCompte, solde, nom, prenom, numero, dateCreation);
        if (tauxInteret < 0) {
            throw new IllegalArgumentException("Taux d'interet cannot be negative");
        }
        this.tauxInteret = tauxInteret;
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(double tauxInteret) {
        if (tauxInteret < 0) {
            throw new IllegalArgumentException("Taux d'interet cannot be negative");
        }
        this.tauxInteret = tauxInteret;
    }

    public void retrait(double montant) {
        try {
            if (montant <= 0) {
                throw new IllegalArgumentException("Montant must be positive");
            }
            if (montant <= this.solde) {
                this.solde -= montant;
                System.out.println("Retrait effectué, solde actuel: " + this.solde);
            } else {
                throw new IllegalStateException("Montant insuffisant");
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Erreur retrait: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erreur inattendue: " + e.getMessage());
        }
    }

    public void addSolde(double montant) {
        try {
            if (montant <= 0) {
                throw new IllegalArgumentException("Montant must be positive");
            }
            this.solde += montant;
            System.out.println("Montant ajouté avec succès, solde actuel: " + this.solde);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur ajout solde: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erreur inattendue: " + e.getMessage());
        }
    }

    public void virement(double montant, Compte compte) {
        try {
            if (compte == null) {
                throw new NullPointerException("Compte cible est null");
            }
            if (montant <= 0) {
                throw new IllegalArgumentException("Montant must be positive");
            }
            if (montant <= this.solde) {
                this.solde -= montant;
                compte.addSolde(montant);
                System.out.println("Virement effectué, solde actuel: " + this.solde);
            } else {
                throw new IllegalStateException("Montant insuffisant pour le virement");
            }
        } catch (NullPointerException | IllegalArgumentException | IllegalStateException e) {
            System.out.println("Erreur virement: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erreur inattendue: " + e.getMessage());
        }
    }

    @Override
    public double calculerInteret() {
        try {
            this.solde += this.solde * (this.tauxInteret / 100);
        } catch (Exception e) {
            System.out.println("Erreur calcul intérêt: " + e.getMessage());
        }
        return this.solde;
    }

    @Override
    public String toString() {
        return super.toString() + " | CompteEpargne [tauxInteret=" + tauxInteret + "]";
    }
}
