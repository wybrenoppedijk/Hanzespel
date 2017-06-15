package wybren_erik.hanzespel.interfaces;

public interface BoatListener {

    void onDepart(long travelTime);
    void onArrive();
    void onArrivalTimeChanged(long timeUntilArrival);

}
