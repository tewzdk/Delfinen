public class Disciplinliste {

    private Disciplin[] disciplinliste = new Disciplin[30];

    public Disciplinliste(){
        int arrayPlads = -1;
        Distancer distancer = new Distancer();
        for (Svoemmestil svoemmestil : Svoemmestil.values()) {
            for (int i = 0; i < distancer.distanceliste.length; i++) {
                arrayPlads++;
                Disciplin disciplin = new Disciplin(svoemmestil, distancer.distanceliste[i]);
                disciplinliste[arrayPlads] = disciplin;
            }

        }
    }

    public Disciplin[] getDisciplinliste() {
        return disciplinliste;
    }

    public void setDisciplinliste(Disciplin[] disciplinliste) {
        this.disciplinliste = disciplinliste;
    }

    public void printDisciplinliste(){
        for (int i = 0; i < disciplinliste.length; i++) {
            System.out.println(disciplinliste[i]);
        }
    }
}
