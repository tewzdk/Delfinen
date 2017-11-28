import java.text.SimpleDateFormat;
import java.util.Date;

public class Medlem {
    private int medlemsnummer;
    private String medlemstype;
    private String navn;
    private Date foedselsdato;
    private String emailadresse;
    private int telefonnummer;
    private Adresse adresse;

    public int getMedlemsnummer() {
        return medlemsnummer;
    }

    public void setMedlemsnummer(int medlemsnummer) {
        this.medlemsnummer = medlemsnummer;
    }

    public String getMedlemstype() {
        return medlemstype;
    }

    public void setMedlemstype(String medlemstype) {
        this.medlemstype = medlemstype;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Date getFoedselsdato() {
        return foedselsdato;
    }

    public void setFoedselsdato(Date foedselsdato) {
        this.foedselsdato = foedselsdato;
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

    @Override
    public String toString(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fødselsdatoString;
        fødselsdatoString = simpleDateFormat.format(foedselsdato);
        return "________________________________________" +
                "\nMedlemsnummer: " + medlemsnummer +
                "\nMedlemstype: " + medlemstype +
                "\nNavn: " + navn +
                "\nFødselsdato: " + fødselsdatoString +
                "\nE-mailadresse: " + emailadresse +
                "\nTelefonnummer: " + telefonnummer +
                "\nAdresse: " + adresse +
                "\n̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅̅";

    }
}
