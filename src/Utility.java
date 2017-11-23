import java.util.Date;
import java.util.InputMismatchException;

public class Utility {
    public void validerIntegerSvar(){

        //utility
        public static int intSvar(){ //undgår exceptions, når brugeren indtaster andet end integers.
            int intSvar = -1;
            int n = 0;
            for(int x=0;x<=n;x++) {
                try {
                    intSvar = in.nextInt();
                } catch (InputMismatchException exception) {

                    System.out.println("Indtast et nummer.");
                    in.nextLine();
                }
            }
            return intSvar;
        }

    }
    public void validerDateSvar(){}
}
