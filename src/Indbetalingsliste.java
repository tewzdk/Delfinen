import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Indbetalingsliste {
    private ArrayList<Indbetaling> indbetalinger = new ArrayList<>();
    private Utility utility = new Utility();


    public void tilfoejIndbetaling() {
        int beloeb;
        int medlemsnummer;
        int betalingsID = 0;
        Date date = new Date();
        date.getTime();
        if (indbetalinger.size() > 0) {
            betalingsID = indbetalinger.get(indbetalinger.size() - 1).getBetalingsID() + 1;
        } else {
            betalingsID = 0;
        }
        System.out.println("Indtast medlemsnummer: ");
        medlemsnummer = utility.inputIntegerSvar();
        System.out.println("Indtast beløb: ");
        beloeb = utility.inputIntegerSvar();

        Indbetaling indbetaling = new Indbetaling(medlemsnummer, beloeb, date, betalingsID);
        indbetalinger.add(indbetaling);
        gemIndbetalinger();
        System.out.println(
                "Ny inbetaling tilføjet:" +
                "\nMedlemsnummber: " + medlemsnummer +
                "\nDato: " + date +
                "\nBeløb: " + beloeb +
                "\nBetalingsID: " + betalingsID);
    }

    public void printRestanceOversigt() {



    }

    public void sletEnkeltBetaling() {
        System.out.println("Angiv betalingsID for betaling der skal slettes:");
        int betalingsID = utility.inputIntegerSvar();

        for (int i = 0; i < indbetalinger.size(); i++) {
            if (indbetalinger.get(i).getBetalingsID() == betalingsID) {
                indbetalinger.remove(i);
            }
        }
        gemIndbetalinger();
    }

    public void printEnkeltBetaling() {
        System.out.println("Angiv betalingsID:");
        int medlemsnummer = utility.inputIntegerSvar();

        for (int i = 0; i < indbetalinger.size(); i++) {
            if (indbetalinger.get(i).getMedlemsnummer() == medlemsnummer) {
                System.out.println(
                        "Ny inbetaling tilføjet:" +
                                "\nMedlemsnummber: " + indbetalinger.get(i).getMedlemsnummer() +
                                "\nDato: " + indbetalinger.get(i).getDato() +
                                "\nBeløb: " + indbetalinger.get(i).getBeloeb() +
                                "\nBetalingsID: " + indbetalinger.get(i).getBetalingsID());
            }
        }


    }

    public void printMedlemsindbetalinger() {
        System.out.println("Angiv medlemsnummer:");
        int medlemsnummer = utility.inputIntegerSvar();

        for (int i = 0; i < indbetalinger.size(); i++) {
            if (indbetalinger.get(i).getMedlemsnummer() == medlemsnummer) {
                System.out.println("ID: " + indbetalinger.get(i).getBetalingsID() +
                        " nr.: " + indbetalinger.get(i).getMedlemsnummer() +
                        " kr.: " + indbetalinger.get(i).getBeloeb());
            }
        }

    }

    public void printAlleBetalinger() {

        for (int i = 0; i < indbetalinger.size(); i++) {
            System.out.println("ID: " + indbetalinger.get(i).getBetalingsID() +
                    " nr.: "+ indbetalinger.get(i).getMedlemsnummer() +
                    " kr.: " + indbetalinger.get(i).getBeloeb());
        }

    }

    public void redigerIndbetaling() {
        Indbetaling indbetaling;
        int betalingsID;
        boolean aktiv = true;

        System.out.println("Indtast betalings nr. du vil redigere:");
        betalingsID = utility.inputIntegerSvar();
        for (int i = 0; i < indbetalinger.size(); i++) {


            if (indbetalinger.get(i).getBetalingsID() == betalingsID) {
                System.out.println("1: Ændre Medlemsnummer" +
                        "\n2: Ændre Beløb" +
                        "\n0: Afslut");
                while (aktiv) {

                    aktiv = indbetalingsswitch(i);

                }

            }
        }
        gemIndbetalinger();

        if (aktiv){
            System.out.println("Kunne ikke finde betalingsID");
        }

    }
    public void laesIndbetalinger(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("resources/indbetalinger")).useDelimiter(";").useLocale(Locale.US);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(scanner.hasNextInt()){ //Denne scanner går igennem txt-filen, og lægger hver scannet del ind som variabel
            //indbetalingsoplysninger
            int betalingsID = scanner.nextInt();
            int medlemsnummer = scanner.nextInt();
            double beloeb = scanner.nextDouble();
            String datoString = scanner.next();
            Date dato = null;
            try {
                dato = utility.simpleDateFormat.parse(datoString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Indbetaling indbetaling = new Indbetaling(medlemsnummer, beloeb, dato, betalingsID);
            indbetalinger.add(indbetaling);

            scanner.nextLine(); //Scanneren fungerer som en cursor, og skal dirigeres til næste linje efter hver menu er indlæst.

        }
        scanner.close();
    }
    private void gemIndbetalinger(){
        try {
            PrintWriter outputStream = new PrintWriter(new File("resources/indbetalinger"));
            for (int i = 0; i < indbetalinger.size(); i++) {
                String datoString = utility.simpleDateFormat.format(indbetalinger.get(i).getDato());
                outputStream.println(
                        indbetalinger.get(i).getBetalingsID() + ";" +
                                indbetalinger.get(i).getMedlemsnummer() + ";" +
                                indbetalinger.get(i).getBeloeb() + ";" +
                                datoString
                );
            }
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    private boolean indbetalingsswitch(int i) {


        switch (utility.inputIntegerSvar()) {
            case 0:
                return false;

            case 1:
                System.out.println("Indtast nyt medlemsnummer:");
                indbetalinger.get(i).setMedlemsnummer(utility.inputIntegerSvar());
                System.out.println("Medlemsnummer ændret.");
                return false;

            case 2:
                System.out.println("Indtast ny betaling:");
                indbetalinger.get(i).setBeloeb(utility.inputIntegerSvar());
                System.out.println("Betaling ændret.");
                return false;

            default:
                return true;
        }
    }
}
