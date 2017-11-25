import java.util.Date;

public class Motionist extends Medlem {

    public Motionist(String navn, int medlemsnummer, String emailadresse, Date foedselsdato, int telefonnummer, Adresse adresse) {
        super.setNavn(navn);
        super.setMedlemsnummer(medlemsnummer);
        super.setEmailadresse(emailadresse);
        super.setFoedselsdato(foedselsdato);
        super.setTelefonnummer(telefonnummer);
        super.setAdresse(adresse);
    }
}
