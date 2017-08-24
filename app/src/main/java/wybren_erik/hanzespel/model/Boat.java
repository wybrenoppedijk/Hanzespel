package wybren_erik.hanzespel.model;

import java.util.ArrayList;
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
import wybren_erik.hanzespel.controller.Intervention;
import wybren_erik.hanzespel.interfaces.BoatListener;
import wybren_erik.hanzespel.interfaces.InterventionListener;

public class Boat implements InterventionListener {

    private static boolean inDock = true;
    private static Set<BoatListener> listeners = new HashSet<>();
    private static Boat instance;
    private static ScheduledFuture<?> arrivalFuture;
    private final Runnable updateTask = new Runnable() {
        @Override
        public void run() {
            try {
                for (BoatListener l : listeners) {
                    l.onArrivalTimeChanged(arrivalFuture.getDelay(TimeUnit.SECONDS));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private int additionalTime = 0;
    private int lessTime = 0;
    private InventoryModel model = InventoryModel.getInstance();
    private ScheduledExecutorService updateExecutor;
    private ScheduledExecutorService arrivalExecutor;
    private final Runnable arrivalTask = new Runnable() {
        @Override
        public void run() {
            try {
                for (BoatListener l : listeners) {
                    l.onArrive();
                    inDock = true;
                    updateExecutor.shutdown();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private City location = RoadMap.getInstance().getCity(Location.KAMPEN);
    private String name;
    private City destination;

    private Boat(String name) {
        Intervention.addListener(this);
        this.location = RoadMap.getInstance().getCity(Location.KAMPEN);
        this.destination = RoadMap.getInstance().getCity(Location.KAMPEN);
        this.name = name;
    }

    public static Boat getInstance() {
        if (instance == null)
            instance = new Boat("Het schip der null");
        return instance;
    }

    public static void make(String name) {
        instance = new Boat(name);
    }

    public static void addListener(BoatListener l) {
        listeners.add(l);
    }

    public static boolean isInDock() {
        return inDock;
    }

    public static int timeUntilArrival() {
        return (int) arrivalFuture.getDelay(TimeUnit.SECONDS);
    }

    public String getName() {
        return name;
    }

    public City getLocation() {
        return location;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    public int getTimeTO(City destination){
        int travelTime = 0;
        Set<Road> roads = RoadMap.getInstance().getEdges(this.location);
        for (Road r : roads) {
            if (r.toNode().equals(destination)) {
                travelTime = r.getWeight() * 10;
            }
        }
        return travelTime;
    }

    @SuppressWarnings("unchecked")
    public void goToCity(City location) {

        inDock = false;

        new Intervention();

        arrivalExecutor = Executors.newSingleThreadScheduledExecutor();
        updateExecutor = Executors.newSingleThreadScheduledExecutor();
        int travelTime = 0;

        Set<Road> roads = RoadMap.getInstance().getEdges(this.location);
        for (Road r : roads) {
            if (r.toNode().equals(location)) {
                travelTime = r.getWeight() * 10 + additionalTime - lessTime;
            }
        }

        arrivalFuture = arrivalExecutor.schedule(arrivalTask, travelTime, TimeUnit.SECONDS);
        System.out.println("Travel time: " + travelTime);
        updateExecutor.scheduleAtFixedRate(updateTask, 1, 1, TimeUnit.SECONDS);
        this.location = location;
        for (BoatListener l : listeners) {
            l.onDepart(travelTime);
        }
        lessTime = 0;
        additionalTime = 0;
    }

    public void sink() {
        updateExecutor.shutdownNow();
        arrivalExecutor.shutdownNow();
        arrivalFuture.cancel(true);
    }
    // 0 = nothing
    // 1 = negative
    // 3 = positive


    @Override
    public void pirateship() {
        InventoryModel inv = InventoryModel.getInstance();
        inv.removeHalf();
    }

    @Override
    public void boatLeak() {
        additionalTime = 30;
    }

    @Override
    public void crewOverboard() {
        additionalTime = 40;
    }

    @Override
    public void badWeather() {
        additionalTime = 20;
    }

    @Override
    public void bandit() {

        if (model.getMoney() < 300) {
            model.addMoney(200);
        } else if (model.getMoney() <= 500) {
            model.setMoney(300);
        } else if (model.getMoney() > 500) {
            model.setMoney(model.getMoney() / 5 * 4);
        }
    }

    @Override
    public void goodWeather() {
        lessTime = 20;
    }

    @Override
    public void foundHiddenChest() {
        model.addMoney(500);
    }

    @Override
    public void defeatPirateShip() {
        model.addMoney(1000);
    }

    @Override
    public void shortCut() {
        lessTime = 30;
    }
}
