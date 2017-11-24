public class UI {

    public void start() {
        Utility utility = new Utility();

        boolean programmetKoerer = true;
        while (programmetKoerer) {
            programmetKoerer = loginMenu(utility);
        }
    }

    public boolean loginMenu(Utility utility) {
        System.out.println("Indtast loginkode / Tast '0' for at afslutte programmet:");
        boolean aktiv;
        int svar;
        svar = utility.validerIntegerSvar();
        switch (svar) {
            case 0:
                System.out.println("[Programmet afsluttes]");
                return false;

            case 1:
                aktiv = true;
                while (aktiv) {
                    aktiv = hovedMenuFormand(utility);
                }
                break;

            case 2:
                aktiv = true;
                while (aktiv) {
                    aktiv = hovedMenuKasserer(utility);
                }
                break;

            case 3:
                aktiv = true;
                while (aktiv) {
                    aktiv = hovedMenuTraener(utility);
                }
                break;

            default:
                System.out.println("'" + svar + "' er ikke en korrekt kode");
                System.out.println();
                loginMenu(utility);
                break;
        }
        return true;
    }

    //Formand
    private boolean hovedMenuFormand(Utility utility) {
        Medlemshaandtering medlemshaandtering = new Medlemshaandtering();
        System.out.println("Vælg en handling:");
        System.out.println("1. Tilgå medlemsinformationer");
        System.out.println("2. Se alle medlemmer");
        System.out.println("3. Opret nyt medlem");
        System.out.println("4. Rediger medlem");
        System.out.println("5. Fjern medlem");
        System.out.println("0. Log ud");
        int svar;
        svar = utility.validerIntegerSvar();

        switch (svar) {
            case 0:
                return false;

            case 1:
                //medlemshaandtering.tilgaaMedlemsinformationer();
                break;

            case 2:
                //medlemshaandtering.printMedlemsliste();
                break;

            case 3:
                //medlemshaandtering.tilfoejMedlem();
                break;

            case 4:
                //medlemshaandtering.redigerMedlem();
                break;

            case 5:
                //medlemshaandtering.fjernMedlem();
                break;
        }

        return true;
    }

    //Kasserer
    private boolean hovedMenuKasserer(Utility utility) {
        Indbetalingsliste indbetalingsliste = new Indbetalingsliste();

        System.out.println("Vælg en handling:");
        System.out.println("1. Indberet betaling");
        System.out.println("2. Oversigt over restance");
        System.out.println("3. Ret indbetaling");
        System.out.println("0. Log ud");

        int svar;
        svar = utility.validerIntegerSvar();
        switch (svar) {
            case 0:
                return false;

            case 1:
                //indbetalingsliste.tilfoejIndbetaling();
                break;

            case 2:
                //indbetalingsliste.printRestanceOversigt();
                break;

            case 3:
                //indbetalingsliste.redigerIndbetaling();
                break;
        }
        return true;
    }

    //Traener
    private boolean hovedMenuTraener(Utility utility) {
        Konkurrencesvoemning konkurrencesvoemning = new Konkurrencesvoemning();

        System.out.println("Vælg en handling:");
        System.out.println("1. Se holdoversigt"); //submenu
        System.out.println("2. Administrer resultater"); //submenu se, tilføj, rediger, slet
        System.out.println("3. Administrer stævner"); //submenu
        System.out.println("0. Log ud");

        int svar;
        svar = utility.validerIntegerSvar();

        switch (svar) {
            case 0:
                return false;

            case 1:
                holdoversigtMenu(utility, konkurrencesvoemning);
                break;

            case 2:
                resultatMenu(utility, konkurrencesvoemning);
                break;

            case 3:
                staevneMenu(utility, konkurrencesvoemning);
                break;
        }
        return true;
    }

    private void holdoversigtMenu(Utility utility, Konkurrencesvoemning konkurrencesvoemning) {
        System.out.println("Vælg en handling");
        System.out.println("1. Se juniorhold");
        System.out.println("2. Se seniorhold");
        System.out.println("0. Gå tilbage");

        int svar;
        svar = utility.validerIntegerSvar();

        switch (svar) {
            case 0:
                break;
            case 1:
                konkurrencesvoemning.seJuniorHoldliste();
                break;

            case 2:
                konkurrencesvoemning.seSeniorHoldliste();
                break;
        }
    }

    private void resultatMenu(Utility utility, Konkurrencesvoemning konkurrencesvoemning) {
        System.out.println("Vælg en handling:");
        System.out.println("1. Se resultater");
        System.out.println("2. Tilføj resultat");
        System.out.println("3. Rediger resultat");
        System.out.println("4. Fjern resultat");
        System.out.println("0. Gå tilbage");
        int svar;
        svar = utility.validerIntegerSvar();

        switch (svar) {
            case 0:
                break;

            case 1:
                konkurrencesvoemning.seResultater();
                break;

            case 2:
                konkurrencesvoemning.tilfoejResultater();
                break;

            case 3:
                konkurrencesvoemning.redigerResultat();
                break;

            case 4:
                konkurrencesvoemning.fjernResultat();
                break;
        }

    }

    private void staevneMenu(Utility utility, Konkurrencesvoemning konkurrencesvoemning) {

        System.out.println("Vælg en handling");
        System.out.println("1. Se aktive staevner");
        System.out.println("2. Se afsluttede staevner");
        System.out.println("3. Tilfoej staevne");
        System.out.println("4. Rediger staevne");
        System.out.println("5. Afslut stævne");
        System.out.println("0. Gå tilbage");
        int svar;
        svar = utility.validerIntegerSvar();

        switch (svar) {

            case 0:
                break;

            case 1:
                konkurrencesvoemning.seAktiveStaevner();
                break;

            case 2:
                konkurrencesvoemning.seAfsluttedeStaevner();
                break;

            case 3:
                konkurrencesvoemning.tilfoejStaevne();
                break;

            case 4:
                konkurrencesvoemning.redigerStaevne();
                break;

            case 5:
                konkurrencesvoemning.afslutStaevne();

        }

    }
}