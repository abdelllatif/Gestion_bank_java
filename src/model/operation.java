package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class operation {
    protected static int numberOfOperation =0;
    protected double montant;
    protected String dateOperation;

    public operation(double montant){
        this.montant = montant;
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.dateOperation = currentDate.format(formatter);
        numberOfOperation++;
    }
    public double getMontant() {
        return montant;
    }
    public String getDateOperation() {
        return dateOperation;
    }
    public static int getNumberOfOperation() {
        return numberOfOperation;
    }
    @Override
    public String toString() {
        return "operation [montant=" + montant + ", dateOperation=" + dateOperation + "]";
    }
}
