import java.text.SimpleDateFormat;
import java.util.Date;

public class Staevne {
    private String staevnenavn;
    private Adresse adresse;
    private Date dato;
    private int staevnelisteID;

    public Staevne(String staevnenavn, Adresse adresse, Date dato, int staevnelisteNummer) {
        this.staevnenavn = staevnenavn;
        this.adresse = adresse;
        this.dato = dato;
        this.staevnelisteID = staevnelisteNummer;
    }

    public String getStaevnenavn() {
        return staevnenavn;
    }

    public void setStaevnenavn(String staevnenavn) {
        this.staevnenavn = staevnenavn;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
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

//        return gadenavn + " " + husnummer + " " + etage +
//                "\n" + postnummer + " " + by;

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String staevnedato;
        staevnedato = simpleDateFormat.format(dato);
        return staevnedato + ": " + staevnenavn + " (" + adresse + ") ";
    }
}
