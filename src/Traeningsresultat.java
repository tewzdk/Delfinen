import java.util.Date;

public class Traeningsresultat extends Resultat{

    public Traeningsresultat(int tid, Disciplin disciplin, Date dato, int medlemsnummer) {

        super.setDisciplin(disciplin);
        super.setTid(tid);
        super.setDato(dato);
        super.setMedlemsnummer(medlemsnummer);
        super.setResultattype("Traeningsresultat");
    }
}
