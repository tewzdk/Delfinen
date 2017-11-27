public class Adresse {
    private String gadenavn;
    private int husnummer;
    private String etage;
    private int postnummer;
    private String by;

    public Adresse(String gadenavn, int husnummer, String etage, int postnummer, String by) {
        this.gadenavn = gadenavn;
        this.husnummer = husnummer;
        this.etage = etage;
        this.postnummer = postnummer;
        this.by = by;
    }

    public String getGadenavn() {
        return gadenavn;
    }

    public void setGadenavn(String gadenavn) {
        this.gadenavn = gadenavn;
    }

    public int getHusnummer() {
        return husnummer;
    }

    public void setHusnummer(int husnummer) {
        this.husnummer = husnummer;
    }

    public String getEtage() {
        return etage;
    }

    public void setEtage(String etage) {
        this.etage = etage;
    }

    public int getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(int postnummer) {
        this.postnummer = postnummer;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString(){
        return "Adresse: " + gadenavn + " " + husnummer + " " + etage + ", " + postnummer + " " + by;
    }
}
