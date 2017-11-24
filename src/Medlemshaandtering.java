import java.util.ArrayList;

public class Medlemshaandtering {
    ArrayList<Medlem> medlemsliste = new ArrayList<>();

    public void tilgaaMedlemsinformationer(){
    }
    public void printMedlemsliste(){
        for (int i = 0; i < medlemsliste.size(); i++) {
            System.out.println(medlemsliste.get(i));
        }
    }
    public void tilfoejMedlem(){
    }
    public void redigerMedlem(){}
    public void fjernMedlem(){}
}
