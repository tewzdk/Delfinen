import java.text.SimpleDateFormat;
import java.util.Date;

public class Staevne {
    private String staevnenavn;
    private Date dato;
    private int staevnelisteID;
    private String staevnebeskrivelse;
    private int[] deltagere = new int[31];


    public Staevne(String staevnenavn, Date dato, int staevnelisteNummer, String staevnebeskrivelse, int[] deltagere) {
        this.staevnenavn = staevnenavn;
        this.dato = dato;
        this.staevnelisteID = staevnelisteNummer;
        this.staevnebeskrivelse = staevnebeskrivelse;
        this.deltagere = deltagere;
    }

    public String getStaevnebeskrivelse() {
        return staevnebeskrivelse;
    }

    public void setStaevnebeskrivelse(String staevnebeskrivelse) {
        this.staevnebeskrivelse = staevnebeskrivelse;
    }

    public String getStaevnenavn() {
        return staevnenavn;
    }

    public void setStaevnenavn(String staevnenavn) {
        this.staevnenavn = staevnenavn;
    }

    public Date getDato() {
        return dato;
    }

    public void setDato(Date dato) {
        this.dato = dato;
    }

    public int getStaevnelisteID() {
        return staevnelisteID;
    }

    public void setStaevnelisteID(int staevnelisteID) {
        this.staevnelisteID = staevnelisteID;
    }

    public int[] getDeltagere() {
        return deltagere;
    }

    public void setDeltagere(int[] deltagere) {
        this.deltagere = deltagere;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String staevnedato;
        staevnedato = simpleDateFormat.format(dato);
        return staevnedato + ": " + staevnenavn + " (" + staevnebeskrivelse + ") ";
    }
}
