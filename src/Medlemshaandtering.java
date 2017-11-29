import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Medlemshaandtering {
    private ArrayList<Medlem> medlemsliste = new ArrayList<>();
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public void printSeniorHoldliste() {

        for (int i = 0; i < medlemsliste.size(); i++) {
            //medlemsliste.get(i).getFoedselsdato();
            Date person = medlemsliste.get(i).getFoedselsdato();
            LocalDate date = person.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long yearsDelta = date.until(LocalDate.now(), ChronoUnit.YEARS);
            if (yearsDelta > 17) {

                medlemsliste.get(i).getMedlemsnummer();
                System.out.println("[" + medlemsliste.get(i).getMedlemsnummer() + "] " + medlemsliste.get(i).getNavn() +
                        ", " + yearsDelta);
            }
        }
    }

    public void laesMedlemsliste(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("resources/medlemsliste")).useDelimiter(";");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(scanner.hasNextInt()){ //Denne scanner går igennem txt-filen, og lægger hver scannet del ind som variabel
            //medlemsoplysninger
            int medlemsnummer = scanner.nextInt();
            String medlemstype = scanner.next();
            String navn = scanner.next();
            String foedselsdatoString = scanner.next();
            Date foedselsdato = null;
            try {
                foedselsdato = format.parse(foedselsdatoString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String emailadresse = scanner.next();
            int telefonnummer = scanner.nextInt();

            //adresseoplysninger
            String gadenavn = scanner.next();
            int husnummer = scanner.nextInt();
            String etage = scanner.next();
            int postnummer = scanner.nextInt();
            String by = scanner.next();

            Adresse adresse = new Adresse(gadenavn,husnummer,etage,postnummer,by);
            if(medlemstype.equalsIgnoreCase("Konkurrencesvømmer")){
                Konkurrencesvoemmer konkurrencesvoemmer =
                        new Konkurrencesvoemmer(medlemsnummer, navn, foedselsdato, emailadresse, telefonnummer, adresse);
                medlemsliste.add(konkurrencesvoemmer);
            }
            else if(medlemstype.equalsIgnoreCase("Motionist")){
                Motionist motionist =
                        new Motionist(medlemsnummer, navn, foedselsdato, emailadresse, telefonnummer, adresse);
                medlemsliste.add(motionist);
            }
            else if(medlemstype.equalsIgnoreCase("Passiv")) {
                Passiv passiv =
                        new Passiv(medlemsnummer, navn, foedselsdato, emailadresse, telefonnummer, adresse);
                medlemsliste.add(passiv);
            }
            else{
                System.out.println("Medlemstype eksisterer ikke");
            }


            scanner.nextLine(); //Scanneren fungerer som en cursor, og skal dirigeres til næste linje efter hver menu er indlæst.
        }
        scanner.close();

    }
    private void gemMedlemsliste(){
        try {
            PrintWriter outputStream = new PrintWriter(new File("resources/medlemsliste"));
            for (int i = 0; i < medlemsliste.size(); i++) {
                String foedselsdatoString = format.format(medlemsliste.get(i).getFoedselsdato());
                outputStream.println(
                        medlemsliste.get(i).getMedlemsnummer() + ";" +
                                medlemsliste.get(i).getMedlemstype() + ";" +
                                medlemsliste.get(i).getNavn() + ";" +
                                foedselsdatoString + ";" +
                                medlemsliste.get(i).getEmailadresse() + ";" +
                                medlemsliste.get(i).getTelefonnummer() + ";" +
                                medlemsliste.get(i).getAdresse().getGadenavn() + ";" +
                                medlemsliste.get(i).getAdresse().getHusnummer() + ";" +
                                medlemsliste.get(i).getAdresse().getEtage() + ";" +
                                medlemsliste.get(i).getAdresse().getPostnummer() + ";" +
                                medlemsliste.get(i).getAdresse().getBy() + ";"
                    );
            }
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void tilgaaMedlemsinformationer(Utility utility){
        System.out.println("Indtast medlemsnummer:");
        int svar = utility.inputIntegerSvar();
        for (int i = 0; i < medlemsliste.size(); i++) {
            if(medlemsliste.get(i).getMedlemsnummer() == svar){
                System.out.println(medlemsliste.get(i));
            }
        }
    }
    public void printMedlemsliste(){
        for (int i = 0; i < medlemsliste.size(); i++) {
            System.out.println("[" + medlemsliste.get(i).getMedlemsnummer() + "] " + medlemsliste.get(i).getNavn());
        }
        System.out.println();
    }
    public void tilfoejMedlem(Utility utility){
        int medlemsnummer = medlemsliste.size() + 1;

        //navn
        System.out.println("Indtast navn:");
        String navn = utility.inputString();

        //foedselsdato
        System.out.println("Indtast fødselsdato (dd/mm/åååå):");
        Date foedselsdato = utility.inputDato();

        //emailadresse
        System.out.println("Indtast emailadresse");
        String emailadresse = utility.inputEmailadresse();

        //telefonnummer
        System.out.println("Indtast telefonnummer:");
        int telefonnummer = utility.inputTelefonnummer();

        //adresse
        Adresse adresse = utility.inputAdresse();

        //type
        System.out.println("Hvilken kategori tilhører medlemmet:");
        System.out.println("1. Konkurrencesvømmer");
        System.out.println("2. Motionist");
        System.out.println("3. Passiv");
        int svar;
        svar = utility.inputIntegerSvar();
        switch(svar){
            case 1:
                Konkurrencesvoemmer konkurrencesvoemmer =
                        new Konkurrencesvoemmer(medlemsnummer, navn, foedselsdato, emailadresse, telefonnummer, adresse);
                medlemsliste.add(konkurrencesvoemmer);
                break;

            case 2:
                Motionist motionist =
                        new Motionist(medlemsnummer, navn, foedselsdato, emailadresse, telefonnummer, adresse);
                medlemsliste.add(motionist);
                break;

            case 3:
                Passiv passiv =
                        new Passiv(medlemsnummer, navn, foedselsdato, emailadresse, telefonnummer, adresse);
                medlemsliste.add(passiv);
                break;
        }
        gemMedlemsliste();
    }
    public void redigerMedlem(Utility utility){
        System.out.println("Indtast medlemsnummer:");
        boolean validerSvar = false;
        boolean aktiv = true;

        while(!validerSvar){
            int svar = utility.inputIntegerSvar();
            for (int i = 0; i < medlemsliste.size(); i++) {
                if(medlemsliste.get(i).getMedlemsnummer() == svar){
                    System.out.println(medlemsliste.get(i));
                    System.out.println("Hvad ønsker du at ændre?");
                    System.out.println("1. Medlemstype");
                    System.out.println("2. Navn");
                    System.out.println("3. Fødselsdato");
                    System.out.println("4. E-mailadresse");
                    System.out.println("5. Telefonnummer");
                    System.out.println("6. Adresse");
                    System.out.println("0. Gå tilbage");


                    while (aktiv) {
                        aktiv = false;
                        switch (utility.inputIntegerSvar()) {
                            case 0:
                                break;

                            case 1:
                                System.out.println("Vælg medlemstype:");
                                System.out.println("1. Konkurrencesvømmer");
                                System.out.println("2. Motionist");
                                System.out.println("3. Passiv");
                                switch(utility.inputIntegerSvar()){
                                    case 1:
                                        medlemsliste.get(i).setMedlemstype("Konkurrencesvømmer");
                                        break;

                                    case 2:
                                        medlemsliste.get(i).setMedlemstype("Motionist");
                                        break;

                                    case 3:
                                        medlemsliste.get(i).setMedlemstype("Passiv");
                                        break;
                                }
                                break;

                            case 2:
                                System.out.println("Indtast nyt navn:");
                                String nytNavn = utility.inputString();
                                medlemsliste.get(i).setNavn(nytNavn);
                                gemMedlemsliste();
                                break;

                            case 3:
                                System.out.println("Indtast ny fødselsdato: (dd/mm/åååå)");
                                Date nyFoedselsdato = utility.inputDato();
                                medlemsliste.get(i).setFoedselsdato(nyFoedselsdato);
                                gemMedlemsliste();
                                break;

                            case 4:
                                System.out.println("Indtast ny e-mailadresse:");
                                String nyEmailadresse = utility.inputEmailadresse();
                                medlemsliste.get(i).setEmailadresse(nyEmailadresse);
                                gemMedlemsliste();
                                break;

                            case 5:
                                System.out.println("Indtast nyt telefonnummer:");
                                int nytTelefonnummer = utility.inputTelefonnummer();
                                medlemsliste.get(i).setTelefonnummer(nytTelefonnummer);
                                gemMedlemsliste();
                                break;

                            case 6:
                                Adresse nyAdresse = utility.inputAdresse();
                                medlemsliste.get(i).setAdresse(nyAdresse);
                                gemMedlemsliste();
                                break;

                            default:
                                System.out.println("Indtast venligst en korrekt handling:");
                                aktiv = true;

                        }
                    }
                    validerSvar = true;
                    }
                    else{
                    System.out.println("Indtast venligst et korrekt medlemsnummer:");
                    }
                }
            }
        }
    public void fjernMedlem(Utility utility){
        boolean validerSvar = false;
        boolean validerSvar2 = false;
        System.out.println("Indtast medlemsnummer:");
        while(!validerSvar) {
            int svar = utility.inputIntegerSvar();
            for (int i = 0; i < medlemsliste.size(); i++) {
                if (medlemsliste.get(i).getMedlemsnummer() == svar) {
                    System.out.println("Er du sikker på, at du vil slette: (Tast: 'JA'/'NEJ')");
                    System.out.println(medlemsliste.get(i));
                    while(!validerSvar2) {
                        String svar2 = utility.inputString();
                        if (svar2.equalsIgnoreCase("JA")) {
                            medlemsliste.remove(i);
                            System.out.println("[Medlemmet er blevet fjernet]");
                            System.out.println();
                            validerSvar2 = true;
                        }
                        else if (svar2.equalsIgnoreCase("NEJ")) {
                            validerSvar2 = true;
                        }
                        else{
                            System.out.println("Indtast venligst 'JA' eller 'NEJ':");
                        }
                    }
                    validerSvar = true;
                }
                else{
                    System.out.println("Medlemsnummer " + svar + " eksisterer ikke. Indtast venligst et korrekt medlemsnummer:");
                }
            }
        }

    }
}
