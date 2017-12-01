import java.util.Date;

public class Staevneresultat extends Resultat {
    private Staevne staevne;

    public Staevneresultat(int tid, Disciplin disciplin, Date dato, int medlemsnummer, int id) {
        super.setDisciplin(disciplin);
        super.setTid(tid);
        super.setDato(dato);
        super.setMedlemsnummer(medlemsnummer);
        super.setResultattype("Staevneresultat");
        super.setResultatID(id);

    }
}
