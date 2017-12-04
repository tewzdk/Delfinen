import java.text.SimpleDateFormat;
import java.util.Date;

public class Staevne {
    private String staevnenavn;
    private Date dato;
    private int staevnelisteID;
    private String staevnebeskrivels;

    public Staevne(String staevnenavn, Date dato, int staevnelisteNummer, String staevnebeskrivels) {
        this.staevnenavn = staevnenavn;
        this.dato = dato;
        this.staevnelisteID = staevnelisteNummer;
        this.staevnebeskrivels = staevnebeskrivels;
    }

    public String getStaevnebeskrivels() {
        return staevnebeskrivels;
    }

    public void setStaevnebeskrivels(String staevnebeskrivels) {
        this.staevnebeskrivels = staevnebeskrivels;
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

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String staevnedato;
        staevnedato = simpleDateFormat.format(dato);
        return staevnedato + ": " + staevnenavn + " (" + staevnebeskrivels + ") ";
    }
}
