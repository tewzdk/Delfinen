import java.util.Date;

public class Resultat {
    private int tid;
    private Disciplin disciplin;
    private Date dato;
    private int medlemsnummer;
    private String resultattype;

    public String getResultattype() {
        return resultattype;
    }

    public void setResultattype(String resultattype) {
        this.resultattype = resultattype;
    }

    public Disciplin getDisciplin() {
        return disciplin;
    }

    public void setDisciplin(Disciplin disciplin) {
        this.disciplin = disciplin;
    }

    public int getMedlemsnummer() {
        return medlemsnummer;
    }

    public void setMedlemsnummer(int medlemsnummer) {
        this.medlemsnummer = medlemsnummer;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }


    public Date getDato() {
        return dato;
    }


    public void setDato(Date dato) {
        this.dato = dato;
    }

    //@Override
    //public String toString(){

       // return "Medlemsnummer: " + medlemsnummer + ", " + dato + tid


    //}

}
