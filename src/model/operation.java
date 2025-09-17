package model;

public class operation {
    protected static int numberOfOperation =0;
    protected double montant;
    protected String dateOperation;

    public operation(double montant, String dateOperation){
        this.montant = montant;
        this.dateOperation = dateOperation;
        numberOfOperation++;
    }
}
