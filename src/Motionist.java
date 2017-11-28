import java.util.Date;

public class Motionist extends Medlem {

    public Motionist(int medlemsnummer, String navn, Date foedselsdato, String emailadresse, int telefonnummer, Adresse adresse) {
        super.setMedlemsnummer(medlemsnummer);
        super.setMedlemstype("Motionist");
        super.setNavn(navn);
        super.setFoedselsdato(foedselsdato);
        super.setEmailadresse(emailadresse);
        super.setTelefonnummer(telefonnummer);
        super.setAdresse(adresse);
    }
}
