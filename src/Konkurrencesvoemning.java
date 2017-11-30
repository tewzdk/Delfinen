import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Konkurrencesvoemning {
    private ArrayList<Staevne> staevneliste = new ArrayList<>();
    private ArrayList<Staevne> afsluttedeStaevner = new ArrayList<>();
    private ArrayList<Resultat> resultater = new ArrayList<>();

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
            System.out.println("Der er ingen afsluttede stævner");
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
        boolean validerSvar = false;
        boolean aktiv = true;


        while (!validerSvar) {
            String svar = utility.inputString();
            for (int i = 0; i < staevneliste.size(); i++) {
                if (staevneliste.get(i).getStaevnenavn() == svar) {
                    System.out.println(staevneliste.get(i));
                    System.out.println("Hvad ønsker du at ændre?");
                    System.out.println("1. Stævnenavn");
                    System.out.println("2. Afholdningsdato");
                    System.out.println("3. Adresse");
                    System.out.println("0. Gå tilbage");
                    while (aktiv) {
                        aktiv = false;
                        switch (utility.inputIntegerSvar()) {
                            case 0:
                                break;
                            case 1:
                                System.out.println("Indtast et nyt nanvn");
                                String nyNavn = utility.inputString();
                                staevneliste.get(i).setStaevnenavn(nyNavn);
                                break;
                            case 2:
                                System.out.println("Indtast ny afholdningsdato (dd/mm/åååå)");
                                Date nyDato = utility.inputDato();
                                staevneliste.get(i).setDato(nyDato);
                                break;
                            case 3:
                                System.out.println("Indtast ny afholdningsadresse");
                                Adresse adresse = utility.inputAdresse();
                                staevneliste.get(i).setAdresse(adresse);
                                break;
                        }
                    }
                    validerSvar = true;
                } else {
                    System.out.println("Indtast venlighst et korrekt stævnenavn");
                }
            }
        }
    }

    public void afslutStaevne(Utility utility, Distancer distancer) {

        boolean validerSvar = false;
        boolean validerSvar2 = false;
        boolean validerSvar3 = false;
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
                            while (!validerSvar3) {
                                System.out.println("Tilføj nyt resultat? (Ja = 1' 'Nej = 2)");
                                int svar3 = utility.inputIntegerSvar();
                                if (svar3 == 1){

                                    System.out.println("---------------------");
                                    System.out.println("Indtast medlemsnummer");
                                    int medlemsnummer = utility.inputIntegerSvar();

                                    System.out.println("Indtast din nye tid: ");
                                    System.out.print("Minut: ");
                                    int minut = utility.inputIntegerSvar();
                                    System.out.print("Sekund: ");
                                    int sekund = utility.inputIntegerSvar();
                                    System.out.print("Hundrededelesekund: ");
                                    int hundrededeleSekund = utility.inputIntegerSvar();
                                    int tid = (minut * 6000) + (sekund * 100) + hundrededeleSekund;

                                    Disciplin disciplin = utility.inputDisciplin(distancer);

                                    Date date = new Date();
                                    date.getTime();

                                    System.out.println(tid);
                                    Staevneresultat staevneresultat = new Staevneresultat(tid, disciplin, date, medlemsnummer);
                                    resultater.add(staevneresultat);
                                    System.out.println();
                                } else if (svar3 == 2){

                                    validerSvar3 = true;
                                } else {
                                    System.out.println("Indtast enten 1 eller 2");
                                }
                            }
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

    public void printResultater(Utility utility, Medlemshaandtering medlemshaandtering) {
        for (int i = 0; i < resultater.size(); i++) {
            String navn = "";
            for (int j = 0; j < medlemshaandtering.getMedlemsliste().size(); j++) {
                if(resultater.get(i).getMedlemsnummer() == medlemshaandtering.getMedlemsliste().get(j).getMedlemsnummer()){
                    navn = medlemshaandtering.getMedlemsliste().get(j).getNavn();
                }
            }
            String navn = utility.navnFraMedlemsnummer(medlemshaandtering, resultater.get(i).getMedlemsnummer());
            System.out.println("[Disciplin: " + resultater.get(i).getDisciplin() +
                    " | Tid: " + utility.omregnTid(resultater.get(i).getTid()) + " | " + navn + "]");
        }
        System.out.println();
        printTop5Resultater(utility, medlemshaandtering);
    }
    public void printTop5Resultater(Utility utility, Medlemshaandtering medlemshaandtering) {
        int femEllerResterende = 5;
        if (resultater.size() == 1) {
            System.out.println(resultater.get(0).getDisciplin() + ":");
            String navn = utility.navnFraMedlemsnummer(medlemshaandtering, resultater.get(0).getMedlemsnummer());
            System.out.println("(" + 1 + ") " + navn + " [" + utility.omregnTid(resultater.get(0).getTid()) + "]");
            System.out.println();
        }
        else if (resultater.size() > 1) {
            System.out.println(resultater.get(0).getDisciplin() + ":");
            String navn = utility.navnFraMedlemsnummer(medlemshaandtering, resultater.get(0).getMedlemsnummer());
            System.out.println("(" + 1 + ") " + navn + " [" + utility.omregnTid(resultater.get(0).getTid()) + "]");
            if (resultater.size() < 5) {
                femEllerResterende = resultater.size();
            }
            for (int i = 1; i < femEllerResterende; i++) {
                if (resultater.get(i).getDisciplin().equals(resultater.get(0).getDisciplin())){
                    navn = utility.navnFraMedlemsnummer(medlemshaandtering, resultater.get(i).getMedlemsnummer());
                    System.out.println("(" + (i + 1) + ") " + navn + " [" + utility.omregnTid(resultater.get(i).getTid()) + "]");
                }
            }
            System.out.println();
            for (int d = 1; d < resultater.size(); d++) {
                if(!resultater.get(d).getDisciplin().equals(resultater.get(d-1).getDisciplin())) {
                    System.out.println(resultater.get(d).getDisciplin() + ":");
                    navn = utility.navnFraMedlemsnummer(medlemshaandtering, resultater.get(d).getMedlemsnummer());
                    System.out.println("(" + 1 + ") " + navn + " [" + utility.omregnTid(resultater.get(d).getTid()) + "]");
                    if ((resultater.size() - d) < 5)
                        femEllerResterende = resultater.size() - d;
                    else {
                        femEllerResterende = 5;
                    }
                    for (int j = 1; j < femEllerResterende; j++) {
                        if (resultater.get(d + j).getDisciplin().equals(resultater.get(d).getDisciplin())) {
                            navn = utility.navnFraMedlemsnummer(medlemshaandtering, resultater.get(d+j).getMedlemsnummer());
                            System.out.println("(" + (j + 1) + ") " + navn + " [" + utility.omregnTid(resultater.get(d + j).getTid())+ "]");
                        }
                    }
                    System.out.println();
                }
            }
        }
        else{
            System.out.println("Der er ingen resultater at fremvise.");
        }
    }

    public void sorterResultater(){
        Comparator<Resultat> resultatComparator = Comparator.comparing(disciplin -> disciplin.getDisciplin().getSvoemmestil());
        resultatComparator = resultatComparator.thenComparing(disciplin -> disciplin.getDisciplin().getDistance());
        resultatComparator = resultatComparator.thenComparing(Resultat::getTid);

        Collections.sort(resultater, resultatComparator);

    }

    public void tilfoejTraeningsResultat(Utility utility, Distancer distancer) {

        System.out.println("Indtast medlemsnummer");
        int medlemsnummer = utility.inputIntegerSvar();

        System.out.println("Indtast din nye tid: ");
        System.out.print("Minut: ");
        int minut = utility.inputIntegerSvar();
        System.out.print("Sekund: ");
        int sekund = utility.inputIntegerSvar();
        System.out.print("Hundrededelesekund: ");
        int hundrededeleSekund = utility.inputIntegerSvar();
        int tid = (minut * 6000) + (sekund * 100) + hundrededeleSekund;

        Disciplin disciplin = utility.inputDisciplin(distancer);

        Date date = new Date();
        date.getTime();

        Traeningsresultat traeningsresultat = new Traeningsresultat(tid, disciplin, date, medlemsnummer);
        resultater.add(traeningsresultat);
        System.out.println();

        sorterResultater();
    }

    public void redigerResultat() {
    }

    public void fjernResultat() {
    }
}
