import java.util.Date;

public class Traeningsresultat extends Resultat{

    public Traeningsresultat(int tid, Date dato, int medlemsnummer) {

        super.setDato(dato);
        super.setTid(tid);
        super.setMedlemsnummer(medlemsnummer);
    }
}
