package menuStartPackage.player;

public class TourCounter {
    private int tour = 0;

    public void incrementTour() {
        this.tour++;
    }

    public int getTour() {
        return tour;
    }
}
