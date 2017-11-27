import java.util.ArrayList;
import java.util.Date;

public class Konkurrencesvoemning {
    private ArrayList<Staevne> staevneliste = new ArrayList<>();

    public void printMedlemsliste(){
        for (int i = 0; i < staevneliste.size(); i++) {
            System.out.println("[" + staevneliste.get(i).getDato() + "] " + staevneliste.get(i).getAdresse());
        }
    }

    public void printSeniorHoldliste(){}
    public void printJuniorHoldliste(){}
    public void printAktiveStaevner(){}
    public void printAfsluttedeStaevner(){}
    public void tilfoejStaevne(Utility utility){

        //Laver stævnenavn
        System.out.println("Indtast stævne");
        String staevnenavn = utility.inputString();
       // System.out.println("Indtast adresse");
        Adresse staevneadresse = utility.inputAdresse();
        System.out.println("Indtast dato for stævnet (dd/mm/åååå)");
        Date staevneDato = utility.inputDato();

        Staevne staevne = new Staevne(staevnenavn, staevneadresse, staevneDato);

        staevneliste.add(staevne);

        System.out.println(staevneliste);

    }

    public void redigerStaevne(){}
    public void afslutStaevne(){}
    public void printResultater(){}
    public void tilfoejResultater(){}
    public void redigerResultat(){}
    public void fjernResultat(){}
}
