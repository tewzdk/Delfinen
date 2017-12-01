public class Disciplin {
    private Svoemmestil svoemmestil;
    private int distance;

    public Disciplin(Svoemmestil svoemmestil, int distance){
        this.svoemmestil = svoemmestil;
        this.distance = distance;
    }

    public Svoemmestil getSvoemmestil() {
        return svoemmestil;
    }

    public void setSvoemmestil(Svoemmestil svoemmestil) {
        this.svoemmestil = svoemmestil;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString(){
        return distance + "m " + svoemmestil;
    }

    //blev nød til at lave en override equals for at kunne sammeligne discipliner i Top5menuen.
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Disciplin)) {
            return false;
        }

        Disciplin that = (Disciplin) other;

        // Custom equality check here.
        return this.getSvoemmestil().equals(that.getSvoemmestil())
                && this.getDistance() == (that.getDistance());
    }
}
