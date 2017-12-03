public class UI {

    public void start() {
        //init classes
        Utility utility = new Utility();

        Medlemshaandtering medlemshaandtering = new Medlemshaandtering();
        Indbetalingsliste indbetalingsliste = new Indbetalingsliste();
        Konkurrencesvoemning konkurrencesvoemning = new Konkurrencesvoemning();

        Disciplinliste disciplinliste = new Disciplinliste();
        Distancer distancer = new Distancer();

        //indlæs txt-filer
        medlemshaandtering.laesMedlemsliste();
        indbetalingsliste.laesIndbetalinger(utility);
        konkurrencesvoemning.laesStaevneliste(utility);
        konkurrencesvoemning.laesResultater(utility);

        boolean programmetKoerer = true;
        while (programmetKoerer) {
            programmetKoerer = loginMenu(utility, medlemshaandtering, indbetalingsliste, konkurrencesvoemning, distancer);
        }
    }

    public boolean loginMenu(Utility utility, Medlemshaandtering medlemshaandtering,
                             Indbetalingsliste indbetalingsliste, Konkurrencesvoemning konkurrencesvoemning, Distancer distancer) {
        System.out.println("Indtast loginkode / Tast '0' for at afslutte programmet:");
        boolean aktiv;
        int svar;
        svar = utility.inputIntegerSvar();
        switch (svar) {
            case 0:
                System.out.println("[Programmet afsluttes]");
                utility.scannerClose();
                return false;

            case 1:
                aktiv = true;
                while (aktiv) {
                    aktiv = hovedMenuFormand(utility, medlemshaandtering,indbetalingsliste);
                }
                break;

            case 2:
                aktiv = true;
                while (aktiv) {
                    aktiv = hovedMenuKasserer(utility, indbetalingsliste,medlemshaandtering);
                }
                break;

            case 3:
                aktiv = true;
                while (aktiv) {
                    aktiv = hovedMenuTraener(utility, konkurrencesvoemning, distancer, medlemshaandtering);
                }
                break;

            default:
                System.out.println("'" + svar + "' er ikke en korrekt kode");
                System.out.println();
                loginMenu(utility, medlemshaandtering, indbetalingsliste, konkurrencesvoemning, distancer);
                break;
        }
        return true;
    }

    //Formand
    private boolean hovedMenuFormand(Utility utility, Medlemshaandtering medlemshaandtering,Indbetalingsliste indbetalingsliste) {
        Konkurrencesvoemning ks = new Konkurrencesvoemning();
        System.out.println("Vælg en handling:");
        System.out.println("1. Tilgå medlemsinformationer");
        System.out.println("2. Se alle medlemmer");
        System.out.println("3. Opret nyt medlem");
        System.out.println("4. Rediger medlem");
        System.out.println("5. Fjern medlem");
        System.out.println("0. Log ud");
        int svar;
        svar = utility.inputIntegerSvar();

        switch (svar) {
            case 0:
                return false;

            case 1:
                medlemshaandtering.tilgaaMedlemsinformationer(utility);
                break;

            case 2:
                medlemshaandtering.printMedlemsliste();
                break;

            case 3:
                medlemshaandtering.tilfoejMedlem(utility);
                break;

            case 4:
                medlemshaandtering.redigerMedlem(utility);
                break;

            case 5:
                medlemshaandtering.fjernMedlem(utility);
                break;

            case 6:
                //ks.printSeniorHoldliste();
                medlemshaandtering.printSeniorHoldliste();
                break;

            case 7:
                indbetalingsMenu(utility, indbetalingsliste,medlemshaandtering);
                break;

        }

        return true;
    }

    //Kasserer
    private boolean hovedMenuKasserer(Utility utility, Indbetalingsliste indbetalingsliste,Medlemshaandtering medlemshaandtering) {
        System.out.println("Vælg en handling:");
        System.out.println("1. Indberet betaling");
        System.out.println("2. Oversigt over restance");
        System.out.println("3. Ret indbetaling");
        System.out.println("0. Log ud");

        int svar;
        svar = utility.inputIntegerSvar();
        switch (svar) {
            case 0:
                return false;

            case 1:
                indbetalingsliste.tilfoejIndbetaling(utility);
                break;

            case 2:
                indbetalingsliste.printRestanceOversigt(utility,medlemshaandtering);
                break;

            case 3:
                indbetalingsliste.redigerIndbetaling(utility);
                break;
        }
        return true;
    }

    //Traener
    private boolean hovedMenuTraener(Utility utility, Konkurrencesvoemning konkurrencesvoemning, Distancer distancer, Medlemshaandtering medlemshaandtering) {
        System.out.println("Vælg en handling:");
        System.out.println("1. Se holdoversigt");
        System.out.println("2. Administrer resultater");
        System.out.println("3. Administrer stævner");
        System.out.println("0. Log ud");

        int svar;
        svar = utility.inputIntegerSvar();

        switch (svar) {
            case 0:
                return false;

            case 1:
                holdoversigtMenu(utility, konkurrencesvoemning, medlemshaandtering);
                break;

            case 2:
                resultatMenu(utility, konkurrencesvoemning, distancer, medlemshaandtering);
                break;

            case 3:
                staevneMenu(utility, konkurrencesvoemning, distancer);
                break;
        }
        return true;
    }

    private void holdoversigtMenu(Utility utility, Konkurrencesvoemning konkurrencesvoemning, Medlemshaandtering medlemshaandtering) {
        System.out.println("Vælg en handling");
        System.out.println("1. Se juniorhold");
        System.out.println("2. Se seniorhold");
        System.out.println("0. Gå tilbage");

        int svar;
        svar = utility.inputIntegerSvar();

        switch (svar) {
            case 0:
                break;
            case 1:
                konkurrencesvoemning.printJuniorHoldliste(medlemshaandtering);
                break;

            case 2:
                konkurrencesvoemning.printSeniorHoldliste(medlemshaandtering);
                break;
        }
    }

    private void resultatMenu(Utility utility, Konkurrencesvoemning konkurrencesvoemning, Distancer distancer, Medlemshaandtering medlemshaandtering) {
        System.out.println("Vælg en handling:");
        System.out.println("1. Se resultater");
        System.out.println("2. Tilføj resultat");
        System.out.println("3. Rediger resultat");
        System.out.println("4. Fjern resultat");
        System.out.println("0. Gå tilbage");
        int svar;
        svar = utility.inputIntegerSvar();

        switch (svar) {
            case 0:
                break;

            case 1:
                konkurrencesvoemning.printResultater(utility, medlemshaandtering);
                break;

            case 2:
                konkurrencesvoemning.tilfoejTraeningsResultat(utility, distancer);
                break;

            case 3:
                konkurrencesvoemning.redigerResultat(utility, medlemshaandtering, distancer);
                break;

            case 4:
                konkurrencesvoemning.fjernResultat(utility, medlemshaandtering);
                break;
        }

    }

    private void staevneMenu(Utility utility, Konkurrencesvoemning konkurrencesvoemning, Distancer distancer) {

        System.out.println("Vælg en handling");
        System.out.println("1. Se aktive staevner");
        System.out.println("2. Se afsluttede staevner");
        System.out.println("3. Tilfoej staevne");
        System.out.println("4. Rediger staevne");
        System.out.println("5. Afslut stævne");
        System.out.println("0. Gå tilbage");
        int svar;
        svar = utility.inputIntegerSvar();

        switch (svar) {

            case 0:
                break;

            case 1:
                konkurrencesvoemning.printAktiveStaevner();
                break;

            case 2:
                konkurrencesvoemning.printAfsluttedeStaevner();
                break;

            case 3:
                konkurrencesvoemning.tilfoejStaevne(utility);
                break;

            case 4:
                konkurrencesvoemning.redigerStaevne(utility);
                break;

            case 5:
                konkurrencesvoemning.afslutStaevne(utility, distancer);

        }

    }

    private void indbetalingsMenu(Utility utility,Indbetalingsliste indbetalingsliste,Medlemshaandtering medlemshaandtering) {
        boolean aktiv = true;
        System.out.print("1: ");
        System.out.println("Tilføj indbetaling");
        System.out.print("2: ");
        System.out.println("Restance oversigt");
        System.out.print("3: ");
        System.out.println("Rediger indbetaling");
        System.out.print("4: ");
        System.out.println("Udskriv bestemt medlems indbetalinger");
        System.out.print("5: ");
        System.out.println("Udskriv alle beralinger");
        System.out.print("6: ");
        System.out.println("Udskriv en bestemt betaling");
        System.out.print("7: ");
        System.out.println("Slet en betaling");
        System.out.print("8: ");
        System.out.println("Betal årlige kontingenter eller komprimer indbetalinger");

        while(aktiv){
            switch (utility.inputIntegerSvar()){
                case 1:
                    indbetalingsliste.tilfoejIndbetaling(utility);break;

                case 2:
                    indbetalingsliste.printRestanceOversigt(utility,medlemshaandtering);break;

                case 3:
                    indbetalingsliste.redigerIndbetaling(utility);break;

                case 4:
                    indbetalingsliste.printMedlemsindbetalinger(utility);break;

                case 5:
                    indbetalingsliste.printAlleBetalinger();break;

                case 6:
                    indbetalingsliste.printEnkeltBetaling(utility);break;

                case 7:
                    indbetalingsliste.sletEnkeltBetaling(utility);break;

                case 8:
                    indbetalingsliste.betalKontingenter(utility,medlemshaandtering);

                case 0: aktiv = false;

                default: break;
            }

        }
    }
}