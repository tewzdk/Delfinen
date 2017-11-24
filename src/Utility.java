import java.util.Scanner;
import java.util.InputMismatchException;

public class Utility {
    private Scanner in = new Scanner(System.in);

    public int validerIntegerSvar(){
            int svar = -1;

                try {
                    svar = in.nextInt();
                } catch (InputMismatchException exception) {
                    System.out.println("Indtast et nummer.");
                    in.nextLine();
                }

            return svar;
        }

    public void validerDateSvar(){}

    public void scannerClose(){
    in.close();
    }
}
