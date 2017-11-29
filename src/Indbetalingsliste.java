import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Indbetalingsliste {
    private ArrayList<Indbetaling> indbetalinger = new ArrayList<>();


    public void tilfoejIndbetaling(Utility utility) {
        double beloeb;
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
        gemIndbetalinger(utility);
        System.out.println(
                "Ny inbetaling tilføjet:" +
                "\nMedlemsnummber: " + medlemsnummer +
                "\nDato: " + date +
                "\nBeløb: " + beloeb +
                "\nBetalingsID: " + betalingsID);
    }

    public void printRestanceOversigt(Utility utility,Medlemshaandtering medlemshaandtering) {

        ArrayList<Medlem> medlemer = medlemshaandtering.getMedlemsliste();

        double kontingent;
        double restance;


        for (int i = 0; i < medlemer.size(); i++) {

            Date person = medlemer.get(i).getFoedselsdato();
            LocalDate date = person.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long yearsDelta = date.until(LocalDate.now(), ChronoUnit.YEARS);

            if (!medlemer.get(i).getMedlemstype().equalsIgnoreCase("Passiv")){
                if (yearsDelta > 17 && yearsDelta < 60) {
                    //mellem 18 og 60 år
                   kontingent = 1600;

                } else if (yearsDelta > 59) {
                    //over 60 år
                    kontingent = 1600 - 1600*0.25;

                }
                else {
                    //under 18
                    kontingent = 1000;
                    }
                }else{

                //passiv
                kontingent = 500;
            }
            restance = kontingent;

            for (int j = 0; j < indbetalinger.size(); j++) {

                if (indbetalinger.get(j).getMedlemsnummer() == medlemer.get(i).getMedlemsnummer()){
                    restance = restance - indbetalinger.get(j).getBeloeb();
                }

            }
            System.out.println("MedlemNr.: " + medlemer.get(i).getMedlemsnummer() + " Kontingent: " + kontingent + " Restance: " + restance);

        }



    }

    public void sletEnkeltBetaling(Utility utility) {
        System.out.println("Angiv betalingsID for betaling der skal slettes:");
        int betalingsID = utility.inputIntegerSvar();

        for (int i = 0; i < indbetalinger.size(); i++) {
            if (indbetalinger.get(i).getBetalingsID() == betalingsID) {
                indbetalinger.remove(i);
            }
        }
        gemIndbetalinger(utility);
    }

    public void printEnkeltBetaling(Utility utility) {
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

    public void printMedlemsindbetalinger(Utility utility) {
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

    public void redigerIndbetaling(Utility utility) {
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

                    aktiv = indbetalingsswitch(i, utility);

                }

            }
        }
        gemIndbetalinger(utility);

        if (aktiv){
            System.out.println("Kunne ikke finde betalingsID");
        }

    }
    public void laesIndbetalinger(Utility utility){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("resources/indbetalinger")).useDelimiter(";").useLocale(Locale.US);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(scanner.hasNextInt()){
            int betalingsID = scanner.nextInt();
            int medlemsnummer = scanner.nextInt();
            double beloeb = scanner.nextDouble();
            String datoString = scanner.next();
            Date date = null;
            try{
                date = utility.simpleDateFormat.parse(datoString);
            } catch (ParseException e){

            }
            Indbetaling indbetaling = new Indbetaling(medlemsnummer,beloeb,date,betalingsID);
            indbetalinger.add(indbetaling);
            scanner.nextLine();
        }

    }
    private void gemIndbetalinger(Utility utility){
        try {
            PrintWriter outputStream = new PrintWriter(new File("resources/indbetalinger"));
            for (int i = 0; i < indbetalinger.size(); i++) {
                String datoString = utility.simpleDateFormat.format(indbetalinger.get(i).getDato());
                outputStream.println(
                        indbetalinger.get(i).getBetalingsID() + ";" +
                                indbetalinger.get(i).getMedlemsnummer() + ";" +
                                indbetalinger.get(i).getBeloeb() + ";" +
                                datoString + ";"
                );
            }
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    private boolean indbetalingsswitch(int i, Utility utility) {


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
