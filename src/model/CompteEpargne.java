package model;

public class CompteEpargne extends Compte {
    private double tauxInteret;

    public CompteEpargne(int numeroCompte, double solde, String nom, String prenom, String numero, String dateCreation, double tauxInteret) {
        super(numeroCompte, solde, nom, prenom, numero, dateCreation);
        this.tauxInteret = tauxInteret;
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

    public void retrait(double montant) {
        if (montant <= 0) {
            System.out.println("Montant must be positive");
            return;
        }
        if (montant <= this.solde) {
            this.solde -= montant;
        } else {
            System.out.println("Montant not enoght");
        }
    }

    public void addSolde(double montant) {
        this.solde += montant;
    }

    public void virement(double montant, Compte compte) {
        if (montant <= this.solde) {
            this.solde -= montant;
            compte.addSolde(montant);
            System.out.println("Montant retirer avec succes ur solde now is" + this.solde);
        } else {
            System.out.println("Montant not enoght");
        }
    }

      new Thread(() -> {
        while (running) {
            try {
                Thread.sleep(30000);
                double interet = calculerInteret();
                this.solde += interet;
                System.out.println("Interest added: " + interet + " | New solde = " + this.solde);
            } catch (InterruptedException e) {
                e.printStackTrace();
                running = false;
            }
        }
    }).start();
}

    @Override
    public double calculerInteret() {
        return this.solde * (this.tauxInteret / 100);
    }

    @Override
    public String toString() {
        return super.toString() + " | CompteEpargne [tauxInteret=" + tauxInteret + "]";
    }
}


