import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Utility {
    private Scanner in = new Scanner(System.in).useDelimiter("\\n");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public String inputString(){
        String string = in.next();
        return string;
    }

    public int inputIntegerSvar(){
            int svar = -1;

                try {
                    svar = in.nextInt();
                } catch (InputMismatchException exception) {
                    System.out.println("Indtast venligst et nummer:");
                    in.nextLine();
                }

            return svar;
        }

    public Date inputDato(){

        boolean validerDatoInput = false;
        String svar;
        Date date = null;
        simpleDateFormat.setLenient(false);

        while(!validerDatoInput) {
            svar = in.next();

            try {
                date = simpleDateFormat.parse(svar);
                validerDatoInput = true;

            } catch (ParseException e) {
                System.out.println("Indtast venligst en korrekt dato (dd/mm/åååå):");
            }

        }
        return date;
    }

    public String inputEmailadresse(){
        boolean validerEmailadresseInput = false;
        String emailadresse = "";
        while(!validerEmailadresseInput){
            emailadresse = inputString();
            if(emailadresse.contains("@")){
                validerEmailadresseInput = true;
            }
            else{
                System.out.println("emailadressen skal indeholde '@':");
            }
        }
        return emailadresse;
    }

    public int inputTelefonnummer(){
        boolean validerSvar = false;
        int telefonnummer = -1;
        while(!validerSvar) {
            telefonnummer = inputIntegerSvar();
            if(telefonnummer > 0 && telefonnummer < 99999999){
                validerSvar = true;
            }
            else{
                System.out.println("Indtast venligst et korrekt telefonnummer:");
            }
        }
        return telefonnummer;
    }

    public Adresse inputAdresse(){
        //gadenavn
        System.out.println("Indtast gadenavn:");
        String gadenavn = in.next();

        //husnummer
        System.out.println("Indtast husnummer:");
        int husnummer = inputIntegerSvar();

        //etage
        System.out.println("Indtast etage:");
        String etage = in.next();

        //postnummer
        boolean validerPostnummerInput = false;
        System.out.println("Indtast postnummer:");
        int postnummer = -1;
        while(!validerPostnummerInput) {
            postnummer = inputIntegerSvar();
            if(postnummer > 9999 || postnummer < 0){
                System.out.println("Indtast venligst et korrekt postnummer:");
            }
            else{
                validerPostnummerInput = true;
            }
        }

        //by
        System.out.println("Indtast by:");
        String by = in.next();

        //skab adresse
        Adresse adresse = new Adresse(gadenavn, husnummer, etage, postnummer, by);

        return adresse;
    }

    public Disciplin inputDisciplin(Distancer distancer) {
        System.out.println("Indtast svømmestil:");
        boolean validerSvar = false;
        Svoemmestil valideretSvoemmestil = null;
        while (!validerSvar) {
            String svarSvoemmestil = in.next();
            for (Svoemmestil svoemmestil : Svoemmestil.values()) {
                if (svarSvoemmestil.equalsIgnoreCase(svoemmestil.toString())) {
                    valideretSvoemmestil = svoemmestil;
                    validerSvar = true;
                }
            }
            if (!validerSvar) {
                System.out.println("Indtast venligst en korrekt svømmestil:");
            }
        }
        System.out.println("Indtast distance");
        boolean validerSvar2 = false;
        int svar2 = -1;
        while (!validerSvar2) {
            svar2 = inputIntegerSvar();
            for (int i = 0; i < distancer.distanceliste.length; i++) {
                if (svar2 == distancer.distanceliste[i]) {
                    validerSvar2 = true;
                }
            }
            if (!validerSvar2) {
                System.out.println("Indtast venligst en korrekt distance:");
            }
        }
        Disciplin disciplin = new Disciplin(valideretSvoemmestil, svar2);

        return disciplin;
    }

    public void scannerClose(){
    in.close();
    }

}
