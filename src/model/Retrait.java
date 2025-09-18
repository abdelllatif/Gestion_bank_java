package model;

public class Retrait extends operation{
    private String destination;
    public Retrait(double montant,String destination){
        super(montant);
        this.destination = destination;

    }

    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
}
