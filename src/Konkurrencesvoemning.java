import java.util.ArrayList;
import java.util.Date;

public class Konkurrencesvoemning {
    private ArrayList<Staevne> staevneliste = new ArrayList<>();
    private ArrayList<Staevne> afsluttedeStaevner = new ArrayList<>();

    public void printMedlemsliste() {
        for (int i = 0; i < staevneliste.size(); i++) {
            System.out.println("[" + staevneliste.get(i).getDato() + "] " + staevneliste.get(i).getAdresse());
        }
    }

    public void printSeniorHoldliste() {


        Date date;
        Medlemshaandtering medlemshaandtering = new Medlemshaandtering();

    }

    public void printJuniorHoldliste() {
    }

    public void printAktiveStaevner() {

        if (staevneliste.size() > 0){
            for (int i = 0; i < staevneliste.size(); i++) {
                System.out.println(staevneliste.get(i));
            }
        } else {
            System.out.println("[Der er ingen planlagt stævner");
        }
        System.out.println("");
    }

    public void printAfsluttedeStaevner() {

        if (afsluttedeStaevner.size() > 0){
            for (int i = 0; i < afsluttedeStaevner.size(); i++) {
                System.out.println(afsluttedeStaevner.get(i));
            }
        } else {
            System.out.println("[Der er ingen afsluttede stævner");
        }
        System.out.println("");

    }

    public void tilfoejStaevne(Utility utility) {

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

    public void redigerStaevne(Utility utility) {

        System.out.println("Hvilket stævne vil du redigere?");


    }

    public void afslutStaevne(Utility utility) {

        boolean validerSvar = false;
        boolean validerSvar2 = false;
        int[] anArray = new int[20];
        int nummer = 0;

        for (int i = 0; i < staevneliste.size(); i++) {
            nummer++;
            System.out.println(nummer + ". " + staevneliste.get(i).getStaevnenavn());
        }

        System.out.println("Hvilket stævne vil du afslutte?");
        while (!validerSvar2) {
            String svar = utility.inputString();
            for (int i = 0; i < staevneliste.size(); i++) {
                if (staevneliste.get(i).getStaevnenavn().equalsIgnoreCase(svar)) {
                    System.out.println("Er du sikker på, at du vil slette: (Tast: 'JA'/'NEJ')");
                    System.out.println(staevneliste.get(i));
                    while (!validerSvar) {
                        String svar2 = utility.inputString();
                        if (svar2.equalsIgnoreCase("JA")) {
                            afsluttedeStaevner.add(staevneliste.get(i));
                            staevneliste.remove(i);
                            System.out.println("[Stævnet er blevet afsluttet]\n");

                            validerSvar = true;
                        } else if (svar2.equalsIgnoreCase("NEJ")) {
                            validerSvar = true;
                        } else {
                            System.out.println("Indtast venligst 'JA' eller 'NEJ':");
                        }
                    }
                    validerSvar2 = true;
                } else {
                    System.out.println("Stævne: " + svar + " eksisterer ikke. Indtast venligst et korrekt stævne.");
                }
            }

        }
    }

    public void printResultater() {
    }

    public void tilfoejResultater() {
    }

    public void redigerResultat() {
    }

    public void fjernResultat() {
    }
}
