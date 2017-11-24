import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Utility {
    private Scanner in = new Scanner(System.in).useDelimiter("\\n");

    public int inputIntegerSvar(){
            int svar = -1;

                try {
                    svar = in.nextInt();
                } catch (InputMismatchException exception) {
                    System.out.println("Indtast et nummer.");
                    in.nextLine();
                }

            return svar;
        }

    public Date inputFoedselsdato(){
        System.out.println("Indtast fødselsdato (dd/mm/åååå)");
        String svar;
        svar = in.next();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date foedselsdato = null;
        try {
            foedselsdato = dateFormat.parse(svar);
        } catch (ParseException e) {
        }

        return foedselsdato;
    }

    //public Adresse inputAdresse(){}

    //public Staevne inputStaevne(){}

    public void scannerClose(){
    in.close();
    }
}
