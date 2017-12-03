import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Konkurrencesvoemning {
    private ArrayList<Staevne> staevneliste = new ArrayList<>();
    private ArrayList<Staevne> afsluttedeStaevner = new ArrayList<>();
    private ArrayList<Resultat> resultater = new ArrayList<>();

    public void printMedlemsliste() {
        for (int i = 0; i < staevneliste.size(); i++) {
            System.out.println("[" + staevneliste.get(i).getDato() + "] " + staevneliste.get(i).getAdresse());
        }
    }

    public void printSeniorHoldliste(Medlemshaandtering medlemshaandtering) {

        for (int i = 0; i < medlemshaandtering.getMedlemsliste().size(); i++) {
            Date foedselsdag = medlemshaandtering.getMedlemsliste().get(i).getFoedselsdato();
            LocalDate date = foedselsdag.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long yearsDelta = date.until(LocalDate.now(), ChronoUnit.YEARS);
            if (yearsDelta > 17) {

                medlemshaandtering.getMedlemsliste().get(i).getMedlemsnummer();
                System.out.println("[" + medlemshaandtering.getMedlemsliste().get(i).getMedlemsnummer() + "] "
                        + medlemshaandtering.getMedlemsliste().get(i).getNavn() +
                        ", " + yearsDelta);
            }
        }
        System.out.println("");

    }

    public void printJuniorHoldliste(Medlemshaandtering medlemshaandtering) {

        for (int i = 0; i < medlemshaandtering.getMedlemsliste().size(); i++) {
            Date foedselsdag = medlemshaandtering.getMedlemsliste().get(i).getFoedselsdato();
            LocalDate date = foedselsdag.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long yearsDelta = date.until(LocalDate.now(), ChronoUnit.YEARS);
            if (yearsDelta < 18) {

                medlemshaandtering.getMedlemsliste().get(i).getMedlemsnummer();
                System.out.println("[" + medlemshaandtering.getMedlemsliste().get(i).getMedlemsnummer() + "] "
                        + medlemshaandtering.getMedlemsliste().get(i).getNavn() +
                        ", " + yearsDelta);
            }
        }
        System.out.println("");

    }

    public void printAktiveStaevner() {

        if (staevneliste.size() > 0) {
            for (int i = 0; i < staevneliste.size(); i++) {
                System.out.println(staevneliste.get(i));
            }
        } else {
            System.out.println("[Der er ingen planlagt stævner");
        }
        System.out.println("");
    }

    public void printAfsluttedeStaevner() {

        if (afsluttedeStaevner.size() > 0) {
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

        int staevnelisteID;

        if (staevneliste.size() > 0) {
            staevnelisteID = staevneliste.get(staevneliste.size() - 1).getStaevnelisteID() + 1;
        } else {
            staevnelisteID = 0;
        }

        //Skaber det nye stævne
        Staevne staevne = new Staevne(staevnenavn, staevneadresse, staevneDato, staevnelisteID);
        staevneliste.add(staevne);
    }

    public void redigerStaevne(Utility utility) {


        if (staevneliste.size() > 0) {
            for (int j = 0; j < staevneliste.size(); j++) {
                System.out.println(staevneliste.get(j).getStaevnelisteID() + ". " + staevneliste.get(j).getStaevnenavn());
            }

            System.out.println("\nHvilket stævne vil du redigere? (Indtast ID)");
            boolean validerSvar = false;
            boolean aktiv = true;

            while (!validerSvar) {
                int svar = utility.inputIntegerSvar();

                for (int i = 0; i < staevneliste.size(); i++) {
                    if (staevneliste.get(i).getStaevnelisteID() == svar) {

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
                        System.out.println("Indtast venligst et korrekt stævneID");
                    }
                }
            }
        } else {
            System.out.println("Der er ingen planlagte stævner\n");
        }

        System.out.println("[Ændringerne er blevet gemt");
        // gemStaevner();
    }

    public void afslutStaevne(Utility utility, Distancer distancer) {

        boolean validerSvar = false;
        boolean validerSvar2 = false;
        boolean boolNytResultat = false;
        int[] anArray = new int[20];
        int nummer = 0;

        if (staevneliste.size() > 0) {
            for (int j = 0; j < staevneliste.size(); j++) {
                System.out.println(staevneliste.get(j).getStaevnelisteID() + ". " + staevneliste.get(j).getStaevnenavn());
            }
            System.out.println("Hvilket stævne vil du afslutte?");
            while (!validerSvar2) {
                int svar = utility.inputIntegerSvar();
                for (int i = 0; i < staevneliste.size(); i++) {
                    if (staevneliste.get(i).getStaevnelisteID() == svar) {
                        System.out.println("Er du sikker på, at du vil slette: (Tast: 'JA'/'NEJ')");
                        System.out.println(staevneliste.get(i));
                        while (!validerSvar) {
                            String svar2 = utility.inputString();
                            if (svar2.equalsIgnoreCase("JA")) {
                                while (!boolNytResultat) {
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
                                    System.out.println(tid); //FJERN

                                    //RET PLEASE
                                    int id = 4;

                                    Staevneresultat staevneresultat = new Staevneresultat(tid, disciplin, date, medlemsnummer, id);
                                    resultater.add(staevneresultat);
                                    System.out.println();


                                    System.out.println("1. Tilføj nyt resultat");
                                    System.out.println("2. Afslut");
                                    int svar3 = utility.inputIntegerSvar();
                                    if (svar3 == 1) {

                                    } else if (svar3 == 2) {

                                        boolNytResultat = true;
                                    } else {
                                        System.out.println("Indtast 1 for at tilføje et nyt resultat eller 2 for at afslutte");
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
                        System.out.println("Stævne: " + svar + " eksisterer ikke. Indtast venligst et korrekt stævneID.");
                    }
                }
            }
        }
    }

    public void laesResultater(Utility utility) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("resources/resultater")).useDelimiter(";");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextInt()) {
            int resultatID = scanner.nextInt();
            int medlemsnummer = scanner.nextInt();
            String svoemmestilString = scanner.next();
            int distance = scanner.nextInt();
            int tid = scanner.nextInt();
            String resultattype = scanner.next();
            String datoString = scanner.next();
            Date date = null;
            try {
                date = utility.simpleDateFormat.parse(datoString);
            } catch (ParseException e) {
            }

            Svoemmestil svoemmestilValideret = null;
            for (Svoemmestil svoemmestil : Svoemmestil.values()) {
                if (svoemmestilString.equalsIgnoreCase(svoemmestil.toString())) {
                    svoemmestilValideret = svoemmestil;
                }
            }
            Disciplin disciplin = new Disciplin(svoemmestilValideret, distance);
            if (resultattype.equalsIgnoreCase("Traeningsresultat")) {
                Traeningsresultat traeningsresultat = new Traeningsresultat(tid, disciplin, date, medlemsnummer, resultatID);
                resultater.add(traeningsresultat);
            } else if (resultattype.equalsIgnoreCase("Staevneresultat")) {
                Staevneresultat staevneresultat = new Staevneresultat(tid, disciplin, date, medlemsnummer, resultatID);
                resultater.add(staevneresultat);
            }
            scanner.nextLine();
        }
    }


    private void gemResultater(Utility utility) {
        try {
            PrintWriter outputStream = new PrintWriter(new File("resources/resultater"));
            for (int i = 0; i < resultater.size(); i++) {
                String datoString = utility.simpleDateFormat.format(resultater.get(i).getDato());
                outputStream.println(
                        resultater.get(i).getResultatID() + ";" +
                                resultater.get(i).getMedlemsnummer() + ";" +
                                resultater.get(i).getDisciplin().getSvoemmestil() + ";" +
                                resultater.get(i).getDisciplin().getDistance() + ";" +
                                resultater.get(i).getTid() + ";" +
                                resultater.get(i).getResultattype() + ";" +
                                datoString + ";"
                );
            }
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void printResultater(Utility utility, Medlemshaandtering medlemshaandtering) {
        for (int i = 0; i < resultater.size(); i++) {
            String navn = utility.navnFraMedlemsnummer(medlemshaandtering, resultater.get(i).getMedlemsnummer());
            System.out.println("[ResultatsID: " + resultater.get(i).getResultatID() + " | Disciplin: " + resultater.get(i).getDisciplin() +
                    " | Tid: " + utility.omregnTid(resultater.get(i).getTid()) + " | " + navn + "]");
        }
        System.out.println();

        //skal slettes
        printTop5Resultater(utility, medlemshaandtering);
    }

    public void printTop5Resultater(Utility utility, Medlemshaandtering medlemshaandtering) {
        int femEllerResterende = 5;
        if (resultater.size() == 1) {
            System.out.println(resultater.get(0).getDisciplin() + ":");
            String navn = utility.navnFraMedlemsnummer(medlemshaandtering, resultater.get(0).getMedlemsnummer());
            System.out.println("(" + 1 + ") " + navn + " [" + utility.omregnTid(resultater.get(0).getTid()) + "]");
            System.out.println();
        } else if (resultater.size() > 1) {
            System.out.println(resultater.get(0).getDisciplin() + ":");
            String navn = utility.navnFraMedlemsnummer(medlemshaandtering, resultater.get(0).getMedlemsnummer());
            System.out.println("(" + 1 + ") " + navn + " [" + utility.omregnTid(resultater.get(0).getTid()) + "]");
            if (resultater.size() < 5) {
                femEllerResterende = resultater.size();
            }
            for (int i = 1; i < femEllerResterende; i++) {
                if (resultater.get(i).getDisciplin().equals(resultater.get(0).getDisciplin())) {
                    navn = utility.navnFraMedlemsnummer(medlemshaandtering, resultater.get(i).getMedlemsnummer());
                    System.out.println("(" + (i + 1) + ") " + navn + " [" + utility.omregnTid(resultater.get(i).getTid()) + "]");
                }
            }
            System.out.println();
            for (int d = 1; d < resultater.size(); d++) {
                if (!resultater.get(d).getDisciplin().equals(resultater.get(d - 1).getDisciplin())) {
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
                            navn = utility.navnFraMedlemsnummer(medlemshaandtering, resultater.get(d + j).getMedlemsnummer());
                            System.out.println("(" + (j + 1) + ") " + navn + " [" + utility.omregnTid(resultater.get(d + j).getTid()) + "]");
                        }
                    }
                    System.out.println();
                }
            }
        } else {
            System.out.println("Der er ingen resultater at fremvise.");
        }
    }

    public void sorterResultater() {
        Comparator<Resultat> resultatComparator = Comparator.comparing(disciplin -> disciplin.getDisciplin().getSvoemmestil());
        resultatComparator = resultatComparator.thenComparing(disciplin -> disciplin.getDisciplin().getDistance());
        resultatComparator = resultatComparator.thenComparing(Resultat::getTid);

        Collections.sort(resultater, resultatComparator);

    }

    public void tilfoejTraeningsResultat(Utility utility, Distancer distancer) {

        System.out.println("Indtast medlemsnummer");
        int medlemsnummer = utility.inputIntegerSvar();

        System.out.println("Indtast din nye tid:");
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
        int resultatID;

        if (resultater.size() > 0) {
            resultatID = resultater.get(resultater.size() - 1).getResultatID() + 1;
        } else {
            resultatID = 0;
        }

        Traeningsresultat traeningsresultat = new Traeningsresultat(tid, disciplin, date, medlemsnummer, resultatID);
        resultater.add(traeningsresultat);
        System.out.println();

        sorterResultater();
        gemResultater(utility);
    }

    public void redigerResultat(Utility utility, Medlemshaandtering medlemshaandtering, Distancer distancer) {
        boolean aktiv = true;
        boolean aendreResultat = true;
        boolean validerSvar = true;

        while (validerSvar) {
            System.out.println("Indtast medlemsnummer for den du vil redigere resultater fra");
            int svar2 = utility.inputIntegerSvar();
            for (int k = 0; k < resultater.size(); k++) {
                if (svar2 == resultater.get(k).getMedlemsnummer()) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
                    String dato;
                    dato = simpleDateFormat.format(resultater.get(k).getDato());
                    int tid = resultater.get(k).getTid();

                    System.out.println("Træningsresultat " + dato);
                    System.out.println(utility.navnFraMedlemsnummer(medlemshaandtering, resultater.get(k).getMedlemsnummer()));
                    System.out.println("ID: " + resultater.get(k).getResultatID() + ", " + resultater.get(k).getDisciplin());
                    System.out.println("Tid: " + tid);
                    System.out.println("");
                    validerSvar = false;
                }
            }
            if (validerSvar == true) {
                System.out.println("Der findes ingen resultater med dette medlemsnummer");
                System.out.println("Tast 1 for at prøve igen eller tast 2 for at stoppe");
                int svar10 = utility.inputIntegerSvar();
                if (svar10 == 1) {

                } else if (svar10 == 2) {
                    validerSvar = false;
                    aktiv = false;
                } else {

                }
            }
        }


        while (aktiv) {
            System.out.println("Indtast resultatID: ");
            int svar = utility.inputIntegerSvar();
            for (int i = 0; i < resultater.size(); i++) {
                if (svar == resultater.get(i).getResultatID()) {

                    System.out.println("Hvad ønsker du at ændre?");
                    System.out.println("1. Medlemsnummer");
                    System.out.println("2. Disciplin");
                    System.out.println("3. Tid");
                    System.out.println("0. Fortryd");

                    while (aendreResultat) {
                        switch (utility.inputIntegerSvar()) {
                            case 0:
                                aendreResultat = false;
                                break;
                            case 1:
                                int nyMedlemsnummer = utility.inputIntegerSvar();
                                resultater.get(i).setMedlemsnummer(nyMedlemsnummer);
                                aendreResultat = false;
                                break;
                            case 2:
                                Disciplin nyDisciplin = utility.inputDisciplin(distancer);
                                resultater.get(i).setDisciplin(nyDisciplin);
                                aendreResultat = false;
                                break;
                            case 3:
                                System.out.println("Indtast minutter");
                                int nyMinut = utility.inputIntegerSvar();
                                System.out.println("Indtast sekunder");
                                int nySekund = utility.inputIntegerSvar();
                                System.out.println("Indtast hundrededelesekunder");
                                int nyHundrededeleSekund = utility.inputIntegerSvar();
                                int nyTid = (nyMinut * 6000) + (nySekund * 100) + nyHundrededeleSekund;
                                resultater.get(i).setTid(nyTid);
                                aendreResultat = false;
                                break;
                            default:
                                System.out.println("Indtast et gyldigt tal");
                                aendreResultat = true;
                                break;
                        }
                    }
                    aktiv = false;
                }
            }
            if (aktiv == true) {
                System.out.println("Der findes ingen resultater med dette resultatID");
                System.out.println("Tast 1 for at prøve igen eller tast 2 for at stoppe");
                int svar3 = utility.inputIntegerSvar();
                if (svar3 == 1) {

                } else if (svar3 == 2) {
                    validerSvar = false;
                    aktiv = false;
                } else {

                }
            }
        }
        System.out.println("[Resultaterne er gemt]\n");
        gemResultater(utility);
    }

    public void fjernResultat(Utility utility, Medlemshaandtering medlemshaandtering) {
        boolean aktiv = true;

        boolean validerSvar = true;
        while (validerSvar) {
            System.out.println("Indtast medlemsnummer for den du vil fjerne resultater fra");
            int svar2 = utility.inputIntegerSvar();
            for (int p = 0; p < resultater.size(); p++) {
                if (svar2 == resultater.get(p).getMedlemsnummer()) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
                    String dato;
                    dato = simpleDateFormat.format(resultater.get(p).getDato());
                    int tidd = resultater.get(p).getTid();

                    System.out.println("Træningsresultat " + dato + "");
                    System.out.println(utility.navnFraMedlemsnummer(medlemshaandtering, resultater.get(p).getMedlemsnummer()));
                    System.out.println("ID: " + resultater.get(p).getResultatID() + ", " + resultater.get(p).getDisciplin());
                    System.out.println("Tid: " + tidd);
                    System.out.println("");
                    validerSvar = false;
                }
            }
            if (validerSvar == true) {
                System.out.println("Der findes ingen resultater med dette medlemsnummer");
                System.out.println("Tast 1 for at prøve igen eller tast 2 for at stoppe" + "");
                int check = utility.inputIntegerSvar();
                if (check == 1) {
                } else if (check == 2) {
                    validerSvar = false;
                    aktiv = false;
                } else {
                }
            }
        }

        while (aktiv) {
            System.out.println("Indtast resultatID: ");
            int svar = utility.inputIntegerSvar();
            for (int i = 0; i < resultater.size(); i++) {

                if (svar == resultater.get(i).getResultatID()) {
                    System.out.println("Er du sikker?  [JA/NEJ]");
                    String check = utility.inputString();
                    if (check.equalsIgnoreCase("JA")) {
                        resultater.remove(i);
                    }
                } //IF SÆTNING
            } //FORLYKKE
            aktiv = false;
        }
        System.out.println("[Resultaterne er gemt/slettet]\n");
        gemResultater(utility);
    }
}
