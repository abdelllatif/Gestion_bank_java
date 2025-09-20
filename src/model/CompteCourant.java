package model;

public class CompteCourant extends Compte {
    private double decouvert;

    public CompteCourant(int numeroCompte, double solde, String nom, String prenom, String numero, String dateCreation, double decouvert) {
        super(numeroCompte, solde, nom, prenom, numero, dateCreation);
        if (decouvert < 0) {
            throw new IllegalArgumentException("Découvert cannot be negative");
        }
        this.decouvert = decouvert;
    }

    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        if (decouvert < 0) {
            throw new IllegalArgumentException("Découvert cannot be negative");
        }
        this.decouvert = decouvert;
    }

    public void retrait(double montant) {
        try {
            if (montant <= 0) {
                throw new IllegalArgumentException("Montant must be positive");
            }

            if (montant <= this.solde + this.decouvert) {
                if (montant <= this.solde) {
                    this.solde -= montant;
                } else {
                    this.decouvert -= (montant - this.solde);
                    this.solde = 0;
                }
                System.out.println("Retrait effectué, solde actuel: " + this.solde + ", découvert restant: " + this.decouvert);
            } else {
                throw new IllegalStateException("Montant insuffisant pour le retrait");
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
            } else if (montant <= this.solde + this.decouvert) {
                this.decouvert -= (montant - this.solde);
                this.solde = 0;
            } else {
                throw new IllegalStateException("Montant insuffisant pour le virement");
            }

            compte.addSolde(montant);
            System.out.println("Virement effectué, solde actuel: " + this.solde + ", découvert restant: " + this.decouvert);

        } catch (NullPointerException | IllegalArgumentException | IllegalStateException e) {
            System.out.println("Erreur virement: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erreur inattendue: " + e.getMessage());
        }
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
