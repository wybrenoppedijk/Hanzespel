package wybren_erik.hanzespel.model;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import wybren_erik.hanzespel.City;
import wybren_erik.hanzespel.Location;
import wybren_erik.hanzespel.Road;
import wybren_erik.hanzespel.RoadMap;
import wybren_erik.hanzespel.interfaces.BoatListener;

public class Boat {

    private static boolean inDock = true;
    private static Set<BoatListener> listeners = new HashSet<>();
    private static Boat instance;
    private final Runnable updateTask = new Runnable() {
        @Override
        public void run() {
            System.out.println(arrivalFuture.getDelay(TimeUnit.SECONDS));
            for (BoatListener l : listeners) {
                //l.onArrivalTimeChanged(arrivalFuture.getDelay(TimeUnit.SECONDS));
            }
        }
    };
    private ScheduledExecutorService arrivalExecutor;
    private ScheduledExecutorService updateExecutor;
    private final Runnable arrivalTask = new Runnable() {
        @Override
        public void run() {
            for (BoatListener l : listeners) {
                l.onArrive();
                inDock = true;
                updateExecutor.shutdown();
            }
        }
    };
    private ScheduledFuture<?> arrivalFuture;
    private InventoryModel inventoryModel;
    private City location;
    private String name;

    private Boat(InventoryModel inventoryModel, String name) {
        this.inventoryModel = inventoryModel;
        this.location = RoadMap.getInstance().getCity(Location.KAMPEN);
        this.name = name;
    }

    public static Boat getInstance() {
        if (instance == null)
            instance = new Boat(InventoryModel.getInstance(), "Het schip der null");
        return instance;
    }

    public static void make(String name) {
        instance = new Boat(InventoryModel.getInstance(), name);
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

    public City getLocation() {
        return location;
    }

    public void goToCity(City location) {
        inDock = false;
        arrivalExecutor = Executors.newSingleThreadScheduledExecutor();
        updateExecutor = Executors.newSingleThreadScheduledExecutor();
        int travelTime = 0;

        Set<Road> roads = RoadMap.getInstance().getEdges(this.location);
        for (Road r : roads) {
            if (r.toNode().equals(location)) {
                travelTime = r.getWeight() * 10;
            }
        }

        arrivalFuture = arrivalExecutor.schedule(arrivalTask, travelTime, TimeUnit.SECONDS);
        System.out.println("Travel time: " + travelTime);
        updateExecutor.scheduleAtFixedRate(updateTask, 1, 1, TimeUnit.SECONDS);
        this.location = location;
    }

}
