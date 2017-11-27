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
        //Laver adresse
        Adresse staevneadresse = utility.inputAdresse();
        System.out.println("Indtast dato for stævnet (dd/mm/åååå)");
        Date staevneDato = utility.inputDato();

        //Skaber det nye stævne
        Staevne staevne = new Staevne(staevnenavn, staevneadresse, staevneDato);
        staevneliste.add(staevne);
    }

    public void redigerStaevne(Utility utility){

        System.out.println("Hvilket stævne vil du redigere?");



    }
    public void afslutStaevne(Utility utility){

        System.out.println("Hvilket stævne vil du afslutte?");

        String svar = utility.inputString();


        for (int i = 0; i < staevneliste.size(); i++) {
            if (staevneliste.get(i).getStaevnenavn().equalsIgnoreCase(svar)){ staevneliste.remove(i);
            }

        }
    }
    public void printResultater(){}
    public void tilfoejResultater(){}
    public void redigerResultat(){}
    public void fjernResultat(){}
}
