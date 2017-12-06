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

    public ArrayList<Resultat> getResultater() {
        return resultater;
    }

    public void printSeniorHoldliste(Medlemshaandtering medlemshaandtering) {

        for (int i = 0; i < medlemshaandtering.getMedlemsliste().size(); i++) {
            Date foedselsdag = medlemshaandtering.getMedlemsliste().get(i).getFoedselsdato();
            LocalDate date = foedselsdag.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long yearsDelta = date.until(LocalDate.now(), ChronoUnit.YEARS);
            if (yearsDelta > 17 && medlemshaandtering.getMedlemsliste().get(i).getMedlemstype() == "Konkurrencesvømmer") {

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
            if (yearsDelta < 18 && medlemshaandtering.getMedlemsliste().get(i).getMedlemstype() == "Konkurrencesvømmer") {

                medlemshaandtering.getMedlemsliste().get(i).getMedlemsnummer();
                System.out.println("[" + medlemshaandtering.getMedlemsliste().get(i).getMedlemsnummer() + "] "
                        + medlemshaandtering.getMedlemsliste().get(i).getNavn() +
                        ", " + yearsDelta);
            }
        }
        System.out.println("");

    }

    public void laesStaevneliste(Utility utility) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("resources/staevneliste")).useDelimiter(";").useLocale(Locale.US);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextInt()) {
            int staevneID = scanner.nextInt();
            String staevnenavn = scanner.next();
            String datoString = scanner.next();
            Date date = null;
            String staevnebeskrivelse = scanner.next();

            //konvertér string til int[]
            String deltagereString = scanner.next();
            String[] brudstykker = deltagereString.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");

            int[] deltagere = new int[brudstykker.length];

            for (int i = 0; i < brudstykker.length; i++) {
                try {
                    deltagere[i] = Integer.parseInt(brudstykker[i]);
                } catch (NumberFormatException nfe) {
                }
            }

            try{
                date = utility.simpleDateFormat.parse(datoString);
            } catch (ParseException e){

            }

            try {
                date = utility.simpleDateFormat.parse(datoString);
            } catch (ParseException e) {

            }
            Staevne staevne = new Staevne(staevnenavn, date, staevneID,staevnebeskrivelse, deltagere);
            staevneliste.add(staevne);
            scanner.nextLine();
        }

    }

    public void laesAfsluttedeStaevner(Utility utility){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("resources/afsluttedestaevner")).useDelimiter(";").useLocale(Locale.US);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(scanner.hasNextInt()){
            int staevneID = scanner.nextInt();
            String staevnenavn = scanner.next();
            String datoString = scanner.next();
            Date date = null;
            String staevnebeskrivelse = scanner.next();

            //konvertér string til int[]
            String deltagereString = scanner.next();
            String[] brudstykker = deltagereString.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");

            int[] deltagere = new int[brudstykker.length];

            for (int i = 0; i < brudstykker.length; i++) {
                try {
                    deltagere[i] = Integer.parseInt(brudstykker[i]);
                } catch (NumberFormatException nfe) {
                }
            }

            try{
                date = utility.simpleDateFormat.parse(datoString);
            } catch (ParseException e){

            }

            Staevne staevne = new Staevne(staevnenavn, date, staevneID,staevnebeskrivelse, deltagere);
            afsluttedeStaevner.add(staevne);
            scanner.nextLine();
        }

    }

    public void gemStaevneliste(Utility utility){
        try {
            PrintWriter outputStream = new PrintWriter(new File("resources/staevneliste"));
            for (int i = 0; i < staevneliste.size(); i++) {
                String datoString = utility.simpleDateFormat.format(staevneliste.get(i).getDato());
                outputStream.println(
                        staevneliste.get(i).getStaevnelisteID() + ";" +
                                staevneliste.get(i).getStaevnenavn() + ";" +
                                datoString + ";" +
                                staevneliste.get(i).getStaevnebeskrivelse() + ";" +
                                Arrays.toString(staevneliste.get(i).getDeltagere()) + ";"
                );
            }
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void gemAfsluttedeStaevner(Utility utility){
        try {
            PrintWriter outputStream = new PrintWriter(new File("resources/afsluttedestaevner"));
            for (int i = 0; i < afsluttedeStaevner.size(); i++) {
                String datoString = utility.simpleDateFormat.format(afsluttedeStaevner.get(i).getDato());
                outputStream.println(
                        afsluttedeStaevner.get(i).getStaevnelisteID() + ";" +
                                afsluttedeStaevner.get(i).getStaevnenavn() + ";" +
                                datoString + ";" +
                                afsluttedeStaevner.get(i).getStaevnebeskrivelse() + ";" +
                                Arrays.toString(afsluttedeStaevner.get(i).getDeltagere()) + ";"

                );
            }
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printAktiveStaevner(Utility utility, Medlemshaandtering medlemshaandtering) {

        if (staevneliste.size() > 0) {
            for (int i = 0; i < staevneliste.size(); i++) {
                System.out.println(staevneliste.get(i));
                System.out.println("Svømmere fra klubben:");
                for (int j = 0; j < staevneliste.get(i).getDeltagere().length; j++) {
                    if(staevneliste.get(i).getDeltagere()[j] != 0){
                        System.out.println(utility.navnFraMedlemsnummer(medlemshaandtering, staevneliste.get(i).getDeltagere()[j]));
                    }
                }
                System.out.println();
            }
        } else {
            System.out.println("[Der er ingen planlagt stævner]\n");
        }
    }

    public void printAfsluttedeStaevner(Utility utility, Medlemshaandtering medlemshaandtering) {

        if (afsluttedeStaevner.size() > 0) {
            for (int i = 0; i < afsluttedeStaevner.size(); i++) {
                System.out.println(afsluttedeStaevner.get(i));
                System.out.println("Svømmere fra klubben:");
                for (int j = 0; j < afsluttedeStaevner.get(i).getDeltagere().length; j++) {
                    if (afsluttedeStaevner.get(i).getDeltagere()[j] != 0) {
                        System.out.println(utility.navnFraMedlemsnummer(medlemshaandtering, afsluttedeStaevner.get(i).getDeltagere()[j]));
                    }
                }
                System.out.println();
            }
        }
         else {
            System.out.println("Der er ingen afsluttede stævner\n");
        }
    }

    public void tilfoejStaevne(Utility utility, Medlemshaandtering medlemshaandtering) {

        //Tilføjer stævnenavn
        System.out.println("Indtast navnet på stævnet:");
        String staevnenavn = utility.inputString();
        //Tilføjer Dato
        System.out.println("Indtast dato for stævnet (dd/mm/åååå)");
        Date staevneDato = utility.inputDato();
        //Tilføjer note
        System.out.println("Indtast stævnebeskrivelse");
        String staevneBeskrivelse = utility.inputString();

        int staevnelisteID;

        if (staevneliste.size() > 0) {
            staevnelisteID = staevneliste.get(staevneliste.size() - 1).getStaevnelisteID() + 1;
        } else {
            staevnelisteID = 1;
        }

        int[] deltagere = new int[31];
        int antalDeltagere = 0;
        boolean validerSvar = false;
        boolean validerSvar2 = false;
        boolean tilfoejFlere = true;

        System.out.println("Ønsker du at tilføje medlemmer til stævnet? (Tast: 'JA'/'NEJ')");

        while(!validerSvar){
            String svar = utility.inputString();
            if(svar.equalsIgnoreCase("JA")){
                if(antalDeltagere < 30){
                    while(!validerSvar2) {
                        validerSvar2 = false;
                        while(tilfoejFlere) {
                            boolean validerSvar3 = false;
                            boolean medlemmetErAlleredeMed = false;
                            System.out.println("Indtast medlemsnummer:");
                            int svar2 = utility.inputIntegerSvar();
                            for (int i = 0; i < deltagere.length; i++) {
                                if(svar2 == deltagere[i]){
                                    medlemmetErAlleredeMed = true;
                                }
                            }
                            for (int i = 0; i < medlemshaandtering.getMedlemsliste().size(); i++) {
                                if (svar2 == medlemshaandtering.getMedlemsliste().get(i).getMedlemsnummer() && !medlemmetErAlleredeMed) {
                                    deltagere[antalDeltagere] = svar2;
                                    antalDeltagere++;
                                    System.out.println("Ønsker du at tilføje flere medlemmer til stævnet? (Tast: 'JA'/'NEJ')");
                                    while(!validerSvar3){
                                        String svar3 = utility.inputString();
                                        if(svar3.equalsIgnoreCase("JA")){
                                            tilfoejFlere = true;
                                            validerSvar3 = true;
                                        }
                                        else if(svar3.equalsIgnoreCase("NEJ")){
                                            tilfoejFlere = false;
                                            validerSvar3 = true;
                                        }
                                        else{
                                            System.out.println("Indtast et korrekt svar: (Tast: 'JA'/'NEJ')");
                                            validerSvar3 = false;
                                        }
                                    }
                                    validerSvar2 = true;
                                }
                            }
                            if(medlemmetErAlleredeMed){
                                System.out.println("Du kan ikke indtaste det samme medlem flere gange.");
                                tilfoejFlere = true;
                            }

                            if(!validerSvar2){
                                System.out.println("Medlem #" + svar2 + " eksisterer ikke.\n" +
                                        "Indtast et korrekt medlemsnummer:");
                            }
                        }
                        if(!validerSvar2){
                            System.out.println("Indtast et korrekt medlemsnummer:");
                        }
                    }
                }
                else{
                    System.out.println("Der er ikke plads til flere deltagere i stævnet");
                }
                validerSvar = true;
            }
            else if(svar.equalsIgnoreCase("NEJ")){
                validerSvar = true;
            }
            else{
                System.out.println("Indtast venligst et korrekt svar: ('JA'/'NEJ')");
                validerSvar = false;
            }
        }


        //Skaber det nye stævne
        Staevne staevne = new Staevne(staevnenavn, staevneDato, staevnelisteID, staevneBeskrivelse, deltagere);
        staevneliste.add(staevne);
        gemStaevneliste(utility);
        System.out.println("[Stævnet er oprettet]");
        System.out.println();
    }

    public void redigerStaevne(Utility utility) {
        boolean validerSvar = false;
        boolean aktiv = true;

        if (staevneliste.size() > 0) {
            while (!validerSvar) {
                System.out.println("Stævner:");
                for (int j = 0; j < staevneliste.size(); j++) {
                    System.out.println(staevneliste.get(j).getStaevnelisteID() + ". " + staevneliste.get(j).getStaevnenavn());
                }
                System.out.println("\nHvilket stævne vil du redigere? (Indtast ID)");
                int svar = utility.inputIntegerSvar();

                for (int i = 0; i < staevneliste.size(); i++) {
                    if (staevneliste.get(i).getStaevnelisteID() == svar) {
                        System.out.println("Hvad ønsker du at ændre?");
                        System.out.println("1. Stævnenavn");
                        System.out.println("2. Afholdningsdato");
                        System.out.println("3. Beskrivelse");
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
                                    System.out.println("Indtast ny beskrivelse");
                                    String nyBeskrivelse = utility.inputString();
                                    staevneliste.get(i).setStaevnebeskrivelse(nyBeskrivelse);
                            }
                        }
                        validerSvar = true;
                    }
                } if (!validerSvar) {
                    System.out.println("Stævne: " + svar + " eksisterer ikke. Indtast venligst et korrekt stævneID.");
                    System.out.println("Tast 1 for at prøve igen eller tast 2 for at stoppe");
                    int check = utility.inputIntegerSvar();
                    if (check == 1) {
                    } else if (check == 2) {
                        validerSvar = true;
                    } else {
                    }
                }
            }
        } else {
            System.out.println("Der er ingen planlagte stævner\n");
        }

        System.out.println("[Ændringerne er blevet gemt");
        gemStaevneliste(utility);
    }

    public void afslutStaevne(Utility utility, Distancer distancer) {

        boolean validerSvar = false;
        boolean validerSvar2 = false;
        boolean boolNytResultat = false;

        if (staevneliste.size() > 0) {
            while (!validerSvar2) {
                System.out.println("Stævner:");
                for (int j = 0; j < staevneliste.size(); j++) {
                    System.out.println(staevneliste.get(j).getStaevnelisteID() + ". " + staevneliste.get(j).getStaevnenavn());
                }
                System.out.println("\nHvilket stævne vil du afslutte? (Indtast ID)");
                int svar = utility.inputIntegerSvar();
                for (int i = 0; i < staevneliste.size(); i++) {
                    if (staevneliste.get(i).getStaevnelisteID() == svar) {
                        System.out.println("Er du sikker på, at du vil afslutte stævnet: (Tast: 'JA'/'NEJ')");
                        System.out.println(staevneliste.get(i));
                        while (!validerSvar) {
                            String svar2 = utility.inputString();
                            if (svar2.equalsIgnoreCase("JA")) {
                                //Skaber ny stævneID så ID fra staevneliste passer til listen der hedder afsluttedeStaevner
                                int staevneID;
                                if (afsluttedeStaevner.size() >0 ){
                                    staevneID = afsluttedeStaevner.get(afsluttedeStaevner.size() - 1).getStaevnelisteID() + 1;
                                } else {
                                    staevneID = 1;
                                }
                                staevneliste.get(i).setStaevnelisteID(staevneID);
                                afsluttedeStaevner.add(staevneliste.get(i));
                                staevneliste.remove(i);
                                System.out.println("[Stævnet er blevet afsluttet]\n");

                                validerSvar = true;
                            } else if (svar2.equalsIgnoreCase("NEJ")) {
                                validerSvar = true;
                            } else {
                                System.out.println("Indtast venligst 'JA' eller 'NEJ':");
                            }
                            validerSvar2 = true;
                        }
                    }
                } if (validerSvar2 == false) {
                    System.out.println("Stævne: " + svar + " eksisterer ikke. Indtast venligst et korrekt stævneID.");
                    System.out.println("Tast 1 for at prøve igen eller tast 2 for at stoppe" + "");
                    int check = utility.inputIntegerSvar();
                    if (check == 1) {
                    } else if (check == 2) {
                        validerSvar2 = true;
                    } else {
                    }
                }
            }
        } else {
            System.out.println("Der er ingen planlagte stævner\n");
        }
        gemStaevneliste(utility);
        gemAfsluttedeStaevner(utility);
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
            System.out.println("[ResultatsID: " + resultater.get(i).getResultatID() +
                    " | Disciplin: " + resultater.get(i).getDisciplin() +
                    " | Tid: " + utility.omregnTid(resultater.get(i).getTid()) + " | " + navn + " | " +
                    utility.simpleDateFormat.format(resultater.get(i).getDato()) + "]");
        }
        System.out.println();
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
    public void printMedlemsResultater(Utility utility, Medlemshaandtering medlemshaandtering){
        System.out.println("Indtast medlemsnummer:");
        boolean validerSvar = false;
        int svar = utility.inputIntegerSvar();
        System.out.println("Svømmer: " + utility.navnFraMedlemsnummer(medlemshaandtering, svar));
        for (int i = 0; i < resultater.size(); i++) {
            if(svar == resultater.get(i).getMedlemsnummer()){
                validerSvar = true;
                System.out.println("[ResultatsID: " + resultater.get(i).getResultatID() +
                        " | Disciplin: " + resultater.get(i).getDisciplin() +
                        " | Tid: " + utility.omregnTid(resultater.get(i).getTid()) + " | " +
                        utility.simpleDateFormat.format(resultater.get(i).getDato()) + "]");
            }
        }
        if(!validerSvar){
            System.out.println("Der eksisterer ingen resultater for medlem #" + svar + ".");
        }
        System.out.println();
    }

    public void sorterResultater() {
        Comparator<Resultat> resultatComparator = Comparator.comparing(disciplin -> disciplin.getDisciplin().getSvoemmestil());
        resultatComparator = resultatComparator.thenComparing(disciplin -> disciplin.getDisciplin().getDistance());
        resultatComparator = resultatComparator.thenComparing(Resultat::getTid);

        Collections.sort(resultater, resultatComparator);
    }

    public void tilfoejResultat(Utility utility, Distancer distancer) {

        System.out.println("Indtast medlemsnummer:");
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

        int findID = 0;
        int resultatsID;

        for (int i = 0; i < resultater.size(); i++) {
            if (resultater.get(i).getResultatID() >= findID) {
                findID = resultater.get(i).getResultatID() + 1;
            }
        }
        resultatsID = findID;

        boolean aktiv = true;
        System.out.println("Hvilken type resultat ønsker du at gemme:");
        System.out.println("1. Stævneresultat");
        System.out.println("2. Træningsresultat");
        while (aktiv) {
            switch (utility.inputIntegerSvar()) {
                case 1:
                    Staevneresultat staevneresultat = new Staevneresultat(tid, disciplin, date, medlemsnummer, resultatsID);
                    resultater.add(staevneresultat);
                    aktiv = false;
                    break;

                case 2:
                    Traeningsresultat traeningsresultat = new Traeningsresultat(tid, disciplin, date, medlemsnummer, resultatsID);
                    resultater.add(traeningsresultat);
                    aktiv = false;
                    break;
            }
        }

        sorterResultater();
        gemResultater(utility);
    }

    public void redigerResultat(Utility utility, Medlemshaandtering medlemshaandtering, Distancer distancer) {
        boolean aktiv = true;
        boolean aendreResultat = true;
        boolean validerSvar = true;

        while (validerSvar) {
            System.out.println("Indtast medlemsnummer:");
            int svar2 = utility.inputIntegerSvar();
            for (int k = 0; k < resultater.size(); k++) {
                if (svar2 == resultater.get(k).getMedlemsnummer()) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
                    String dato;
                    dato = simpleDateFormat.format(resultater.get(k).getDato());
                    int tid = resultater.get(k).getTid();

                    System.out.println("Træningsresultat " + dato);
                    System.out.println(utility.navnFraMedlemsnummer(medlemshaandtering, resultater.get(k).getMedlemsnummer()));
                    System.out.println("ResultatsID: " + resultater.get(k).getResultatID() + "\nDisciplin: " + resultater.get(k).getDisciplin());
                    System.out.println("Tid: " + utility.omregnTid(tid));
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
            System.out.println("Indtast resultatsID: ");
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
        System.out.println("[Resultatet er gemt]\n");
        gemResultater(utility);
    }

    public void fjernResultat(Utility utility, Medlemshaandtering medlemshaandtering) {
        boolean aktiv = true;

        boolean validerSvar = true;
        while (validerSvar) {
            System.out.println("Indtast medlemsnummer:");
            int svar2 = utility.inputIntegerSvar();
            for (int p = 0; p < resultater.size(); p++) {
                if (svar2 == resultater.get(p).getMedlemsnummer()) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
                    String dato;
                    dato = simpleDateFormat.format(resultater.get(p).getDato());
                    int tid = resultater.get(p).getTid();

                    System.out.println("Træningsresultat " + dato + "");
                    System.out.println(utility.navnFraMedlemsnummer(medlemshaandtering, resultater.get(p).getMedlemsnummer()));
                    System.out.println("ResultatsID: " + resultater.get(p).getResultatID() + "\nDisciplin: " + resultater.get(p).getDisciplin());
                    System.out.println("Tid: " + utility.omregnTid(tid));
                    System.out.println("");
                    validerSvar = false;
                }
            }
            if (validerSvar == true) {
                System.out.println("Der findes ingen resultater for dette medlemsnummer");
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
                        aktiv = false;
                    } else {
                        aktiv = false;
                    }
                }
            }
            if (aktiv == true) {
                System.out.println("Der findes ingen resultater med dette resultatsID");
                System.out.println("Tast 1 for at prøve igen eller tast 2 for at stoppe");
                int check = utility.inputIntegerSvar();
                if (check == 1) {
                } else if (check == 2) {
                    aktiv = false;
                } else {

                }
            }
        }
        System.out.println("[Resultatet er gemt/slettet]\n");
        gemResultater(utility);
    }
}
