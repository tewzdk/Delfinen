import java.util.ArrayList;
import java.util.Date;

public class Indbetalingsliste {
    private ArrayList<Indbetaling> indbetalinger = new ArrayList<>();
    private Utility utility = new Utility();


    public void tilfoejIndbetaling() {
        int belloeb;
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
        belloeb = utility.inputIntegerSvar();

        Indbetaling indbetaling = new Indbetaling();


        indbetaling.setBeloeb(belloeb);
        indbetaling.setDato(date);
        indbetaling.setMedlemsnummer(medlemsnummer);
        indbetaling.setBetalingsID(betalingsID);
        indbetalinger.add(indbetaling);
        System.out.println(
                "Ny inbetaling tilføjet:" +
                "\nMedlemsnummber: " + medlemsnummer +
                "\nDato: " + date +
                "\nBeløb: " + belloeb +
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

        if (aktiv){
            System.out.println("Kunne ikke finde betalingsID");
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
