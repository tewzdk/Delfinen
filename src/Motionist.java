import java.util.Date;

public class Motionist extends Medlem {

    public Motionist(String navn, Date foedselsdato, int medlemsnummer, String emailadresse, int telefonnummer, Adresse adresse) {
        super.setNavn(navn);
        super.setFoedselsdato(foedselsdato);
        super.setMedlemsnummer(medlemsnummer);
        super.setEmailadresse(emailadresse);
        super.setTelefonnummer(telefonnummer);
        super.setAdresse(adresse);
    }
}
