import java.util.ArrayList;
import java.util.Date;

public class Medlem {
    private String navn;
    private int medlemsnummer;
    private String emailadresse;
    private Date fødselsaar;
    private int telefonnummer;
    private Adresse adresse = new Adresse();
    private ArrayList<Indbetaling> indbetalinger = new ArrayList<>();

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public int getMedlemsnummer() {
        return medlemsnummer;
    }

    public void setMedlemsnummer(int medlemsnummer) {
        this.medlemsnummer = medlemsnummer;
    }

    public Date getFødselsaar() {
        return fødselsaar;
    }

    public void setFødselsaar(Date fødselsaar) {
        this.fødselsaar = fødselsaar;
    }

    public int getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(int telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getEmailadresse() {
        return emailadresse;
    }

    public void setEmailadresse(String emailadresse) {
        this.emailadresse = emailadresse;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public ArrayList<Indbetaling> getIndbetalinger() {
        return indbetalinger;
    }

    public void setIndbetalinger(ArrayList<Indbetaling> indbetalinger) {
        this.indbetalinger = indbetalinger;
    }
}
