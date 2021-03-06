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
        konkurrencesvoemning.laesAfsluttedeStaevner(utility);
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
                    aktiv = hovedMenuFormand(utility, medlemshaandtering,konkurrencesvoemning);
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
    private boolean hovedMenuFormand(Utility utility, Medlemshaandtering medlemshaandtering, Konkurrencesvoemning konkurrencesvoemning) {
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
                medlemshaandtering.fjernMedlem(utility, konkurrencesvoemning);
                break;

            case 6:
                //ks.printSeniorHoldliste();
                medlemshaandtering.printSeniorHoldliste();
                break;

        }

        return true;
    }

    //Kasserer
    private boolean hovedMenuKasserer(Utility utility, Indbetalingsliste indbetalingsliste,Medlemshaandtering medlemshaandtering) {
        System.out.println("Vælg en handling:");
        System.out.print("1: ");
        System.out.println("Tilføj indbetaling");
        System.out.print("2: ");
        System.out.println("Restance oversigt");
        System.out.print("3: ");
        System.out.println("Rediger indbetaling");
        System.out.print("4: ");
        System.out.println("Udskriv bestemt medlems indbetalinger");
        System.out.print("5: ");
        System.out.println("Udskriv alle betalinger");
        System.out.print("6: ");
        System.out.println("Udskriv en bestemt betaling");
        System.out.print("7: ");
        System.out.println("Slet en betaling");
        System.out.print("8: ");
        System.out.println("Betal årlige kontingenter eller komprimer indbetalinger");
        System.out.print("0: ");
        System.out.println("Log af");


            switch (utility.inputIntegerSvar()){
                case 1:
                    indbetalingsliste.tilfoejIndbetaling(utility);break;

                case 2:
                    indbetalingsliste.printRestanceOversigt(utility,medlemshaandtering);break;

                case 3:
                    indbetalingsliste.redigerIndbetaling(utility);break;

                case 4:
                    indbetalingsliste.printMedlemsindbetalinger(medlemshaandtering,utility);break;

                case 5:
                    indbetalingsliste.printAlleBetalinger();break;

                case 6:
                    indbetalingsliste.printEnkeltBetaling(utility);break;

                case 7:
                    indbetalingsliste.sletEnkeltBetaling(utility);break;

                case 8:
                    indbetalingsliste.betalKontingenter(utility,medlemshaandtering); break;

                case 0: return false;

                default: break;
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
                staevneMenu(utility, konkurrencesvoemning, distancer, medlemshaandtering);
                break;
        }
        return true;
    }

    private void holdoversigtMenu(Utility utility, Konkurrencesvoemning konkurrencesvoemning, Medlemshaandtering medlemshaandtering) {
        System.out.println("Vælg en handling:");
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
        System.out.println("1. Se alle resultater");
        System.out.println("2. Se resultater for enkelt medlem");
        System.out.println("3. Se top5-resultater");
        System.out.println("4. Tilføj resultat");
        System.out.println("5. Rediger resultat");
        System.out.println("6. Fjern resultat");
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
                konkurrencesvoemning.printMedlemsResultater(utility, medlemshaandtering);
                break;

            case 3:
                konkurrencesvoemning.printTop5Resultater(utility, medlemshaandtering);
                break;

            case 4:
                konkurrencesvoemning.tilfoejResultat(utility, distancer);
                break;

            case 5:
                konkurrencesvoemning.redigerResultat(utility, medlemshaandtering, distancer);
                break;

            case 6:
                konkurrencesvoemning.fjernResultat(utility, medlemshaandtering);
                break;
        }

    }

    private void staevneMenu(Utility utility, Konkurrencesvoemning konkurrencesvoemning, Distancer distancer, Medlemshaandtering medlemshaandtering) {

        System.out.println("Vælg en handling");
        System.out.println("1. Se aktive staevner");
        System.out.println("2. Se afsluttede staevner");
        System.out.println("3. Tilføj staevne");
        System.out.println("4. Rediger staevne");
        System.out.println("5. Afslut stævne");
        System.out.println("0. Gå tilbage");
        int svar;
        svar = utility.inputIntegerSvar();

        switch (svar) {

            case 0:
                break;

            case 1:
                konkurrencesvoemning.printAktiveStaevner(utility, medlemshaandtering);
                break;

            case 2:
                konkurrencesvoemning.printAfsluttedeStaevner(utility, medlemshaandtering);
                break;

            case 3:
                konkurrencesvoemning.tilfoejStaevne(utility, medlemshaandtering);
                break;

            case 4:
                konkurrencesvoemning.redigerStaevne(utility);
                break;

            case 5:
                konkurrencesvoemning.afslutStaevne(utility, distancer);

        }

    }
}