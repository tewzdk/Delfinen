import java.util.ArrayList;
import java.util.Date;

public class Medlemshaandtering {
    private ArrayList<Medlem> medlemsliste = new ArrayList<>();

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
                    System.out.println("1. Navn");
                    System.out.println("2. Fødselsdato");
                    System.out.println("3. E-mailadresse");
                    System.out.println("4. Telefonnummer");
                    System.out.println("5. Adresse");
                    System.out.println("0. Gå tilbage");


                    while (aktiv) {
                        aktiv = false;
                        switch (utility.inputIntegerSvar()) {
                            case 0:
                                break;

                            case 1:
                                System.out.println("Indtast nyt navn:");
                                String nytNavn = utility.inputString();
                                medlemsliste.get(i).setNavn(nytNavn);
                                break;

                            case 2:
                                System.out.println("Indtast ny fødselsdato: (dd/mm/åååå)");
                                Date nyFoedselsdato = utility.inputDato();
                                medlemsliste.get(i).setFoedselsdato(nyFoedselsdato);
                                break;

                            case 3:
                                System.out.println("Indtast ny e-mailadresse:");
                                String nyEmailadresse = utility.inputEmailadresse();
                                medlemsliste.get(i).setEmailadresse(nyEmailadresse);
                                break;

                            case 4:
                                System.out.println("Indtast nyt telefonnummer:");
                                int nytTelefonnummer = utility.inputTelefonnummer();
                                medlemsliste.get(i).setTelefonnummer(nytTelefonnummer);
                                break;

                            case 5:
                                Adresse nyAdresse = utility.inputAdresse();
                                medlemsliste.get(i).setAdresse(nyAdresse);
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
