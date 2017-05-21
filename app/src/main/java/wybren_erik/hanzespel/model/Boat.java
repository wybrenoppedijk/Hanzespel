package wybren_erik.hanzespel.model;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import wybren_erik.hanzespel.City;
import wybren_erik.hanzespel.Location;
import wybren_erik.hanzespel.RoadMap;
import wybren_erik.hanzespel.interfaces.BoatListener;

public class Boat {

    private InventoryModel inventoryModel;
    private City location;
    private String name;
    private static Set<BoatListener> listeners = new HashSet<>();

    public Boat(InventoryModel inventoryModel, City location, String name) {
        this.inventoryModel = inventoryModel;
        this.location = location;
        this.name = name;
    }

    public Boat(InventoryModel inventoryModel, String name) {
        this.inventoryModel = inventoryModel;
        this.location = RoadMap.getInstance().getCity(Location.KAMPEN);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public InventoryModel getInventoryModel() {
        return inventoryModel;
    }

    public City getLocation() {
        return location;
    }

    public void goToCity(City location) {
        Timer timer = new Timer();
        final long startTime = System.currentTimeMillis();
        final long delay = this.location.getName().getTravelTime(location.getName());

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(System.currentTimeMillis() - startTime >= delay) {
                    for(BoatListener l : listeners) {
                        l.onArrive();
                    }
                    cancel();
                }
                for(BoatListener l : listeners) {
                    l.onArrivalTimeChanged(delay - (System.currentTimeMillis() - startTime));
                }
            }
        };

        timer.schedule(task, 0, 1000);
    }

    public static void addListener(BoatListener l) {
        listeners.add(l);
    }
}
