public class UI {

    public void start(){
        Utility utility = new Utility();

        boolean programmetKoerer = true;
        while (programmetKoerer) {
            programmetKoerer = loginMenu(utility);
        }
    }

    public boolean loginMenu(Utility utility){
        System.out.println("Indtast loginkode / Tast '0' for at afslutte programmet:");
        boolean aktiv;
        int svar;
        svar = utility.validerIntegerSvar();
        switch(svar){
            case 0:
                System.out.println("[Programmet afsluttes]");
                return false;

            case 1:
                aktiv = true;
                while(aktiv) {
                    aktiv = hovedMenuFormand(utility);
                }
                break;

            case 2:
                aktiv = true;
                while(aktiv){
                    aktiv = hovedMenuKasserer(utility);
                }
                break;

            case 3:
                aktiv = true;
                while(aktiv) {
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
    private boolean hovedMenuFormand(Utility utility){
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

        switch(svar){
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
    private boolean hovedMenuKasserer(Utility utility){
        Indbetalingsliste indbetalingsliste = new Indbetalingsliste();

        System.out.println("Vælg en handling:");
        System.out.println("1. Indberet betaling");
        System.out.println("2. Oversigt over restance");
        System.out.println("3. Ret indbetaling");
        System.out.println("0. Log ud");
        int svar;
        svar = utility.validerIntegerSvar();
        switch(svar){
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
    private boolean hovedMenuTraener(Utility utility){
        Konkurrencesvoemning konkurrencesvoemning = new Konkurrencesvoemning();
        System.out.println("Vælg en handling:");
        System.out.println("1. Se holdoversigt"); //submenu
        System.out.println("2. Se resultater"); //submenu
        System.out.println("3. Tilføj resultat");
        System.out.println("4. Rediger resultat");
        System.out.println("5. Slet resultat");
        System.out.println("6. Administrer stævner"); //submenu
        System.out.println("0. Log ud");
        int svar;
        svar = utility.validerIntegerSvar();

        switch(svar){
            case 0:
                return false;

            case 1:
                //holdoversigtMenu();
                break;

            case 2:
                //resultatMenu();
                break;

            case 3:
                //konkurrencesvoemning.tilfoejResultat();
                break;

            case 4:
                //konkurrencesvoemning.redigerResultat();
                break;

            case 5:
                //konkurrencesvoemning.fjernResultat();
                break;

            case 6:
                //staevneMenu();
                break;
        }
        return true;
    }

}
