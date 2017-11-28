import java.util.Date;

public class Indbetaling {
    private int medlemsnummer;
    private double beloeb;
    private Date dato;
    private int betalingsID = 0;

    public int getMedlemsnummer() {
        return medlemsnummer;
    }

    public void setMedlemsnummer(int medlemsnummer) {
        this.medlemsnummer = medlemsnummer;
    }

    public double getBeloeb() {
        return beloeb;
    }

    public int getBetalingsID() {
        return betalingsID;
    }

    public void setBetalingsID(int betalingsID) {
        this.betalingsID = betalingsID;
    }

    public void setBeloeb(double beloeb) {
        this.beloeb = beloeb;
    }

    public Date getDato() {
        return dato;
    }

    public void setDato(Date dato) {
        this.dato = dato;
    }
}
