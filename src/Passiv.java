import java.util.Date;

public class Passiv extends Medlem {

    public Passiv(String navn, int medlemsnummer, String emailadresse, Date foedselsdato, int telefonnummer, Adresse adresse) {
        super.setNavn(navn);
        super.setMedlemsnummer(medlemsnummer);
        super.setEmailadresse(emailadresse);
        super.setFoedselsdato(foedselsdato);
        super.setTelefonnummer(telefonnummer);
        super.setAdresse(adresse);
    }
}
