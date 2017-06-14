package wybren_erik.hanzespel.model;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import wybren_erik.hanzespel.City;
import wybren_erik.hanzespel.Location;
import wybren_erik.hanzespel.Road;
import wybren_erik.hanzespel.RoadMap;
import wybren_erik.hanzespel.interfaces.BoatListener;

public class Boat {

    private static boolean inDock = true;
    private static Set<BoatListener> listeners = new HashSet<>();
    private final Runnable arrivalTask = new Runnable() {
        @Override
        public void run() {
            for (BoatListener l : listeners) {
                l.onArrive();
                inDock = true;
            }
        }
    };
    private final Runnable updateTask = new Runnable() {
        @Override
        public void run() {
            for (BoatListener l : listeners) {
                l.onArrivalTimeChanged();
            }
        }
    };

    private InventoryModel inventoryModel;
    private City location;
    private String name;

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

    public static void addListener(BoatListener l) {
        listeners.add(l);
    }

    public static boolean isInDock() {
        return inDock;
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
        inDock = false;
        final ScheduledExecutorService arrivalExecutor = Executors.newSingleThreadScheduledExecutor();
        final ScheduledExecutorService updateExecutor = Executors.newSingleThreadScheduledExecutor();
        int travelTime = 0;

        Set<Road> roads = RoadMap.getInstance().getEdges(this.location);
        for (Road r : roads) {
            if (r.toNode().equals(location)) {
                travelTime = r.getWeight() * 10;
            }
        }

        arrivalExecutor.schedule(arrivalTask, travelTime, TimeUnit.SECONDS);
        updateExecutor.scheduleAtFixedRate(updateTask, 1, 1, TimeUnit.MILLISECONDS);
    }

}
