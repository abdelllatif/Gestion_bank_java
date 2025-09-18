package model;

public class Versement extends operation {
    private String source;

    public Versement(double montant, String source) {
        super(montant);
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
