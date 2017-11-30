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
}
