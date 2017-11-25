import java.util.Date;

public class Konkurrencesvoemmer extends Medlem {
    public Konkurrencesvoemmer (int medlemsnummer, String navn, Date foedselsdato, String emailadresse, int telefonnummer, Adresse adresse){
        super.setMedlemsnummer(medlemsnummer);
        super.setNavn(navn);
        super.setFoedselsdato(foedselsdato);
        super.setEmailadresse(emailadresse);
        super.setTelefonnummer(telefonnummer);
        super.setAdresse(adresse);
    }
}
