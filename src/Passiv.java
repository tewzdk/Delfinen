import java.util.Date;

public class Passiv extends Medlem {

    public Passiv(int medlemsnummer, String navn, Date foedselsdato, String emailadresse, int telefonnummer, Adresse adresse) {
        super.setMedlemsnummer(medlemsnummer);
        super.setMedlemstype("Passiv");
        super.setNavn(navn);
        super.setFoedselsdato(foedselsdato);
        super.setEmailadresse(emailadresse);
        super.setTelefonnummer(telefonnummer);
        super.setAdresse(adresse);
    }
}
