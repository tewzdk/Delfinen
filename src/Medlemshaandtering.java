import java.util.ArrayList;
import java.util.Date;

public class Medlemshaandtering {
    private ArrayList<Medlem> medlemsliste = new ArrayList<>();

    public void tilgaaMedlemsinformationer(){
    }
    public void printMedlemsliste(){
        for (int i = 0; i < medlemsliste.size(); i++) {
            System.out.println("[" + medlemsliste.get(i).getMedlemsnummer() + "] " + medlemsliste.get(i).getNavn());
        }
    }
    public void tilfoejMedlem(Utility utility){

        //navn
        System.out.println("Indtast navn:");
        String navn = utility.inputString();

        //foedselsdato
        System.out.println("Indtast fødselsdato (dd/mm/åååå):");
        Date foedselsdato = utility.inputDato();

        //medlemsnummer
        System.out.println("Indtast medlemsnummer:");
        int medlemsnummer = utility.inputIntegerSvar();

        //emailadresse
        boolean validerEmailadresseInput = false;
        System.out.println("Indtast emailadresse");
        String emailadresse = "";
        while(!validerEmailadresseInput){
            emailadresse = utility.inputString();
            if(emailadresse.contains("@")){
                validerEmailadresseInput = true;
            }
            else{
                System.out.println("emailadressen skal indeholde '@':");
            }
        }

        //telefonnummer
        System.out.println("Indtast telefonnummer:");
        int telefonnummer = utility.inputIntegerSvar();

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
    public void redigerMedlem(){}
    public void fjernMedlem(){}
}
