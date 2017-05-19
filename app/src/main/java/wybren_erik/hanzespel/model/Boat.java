package wybren_erik.hanzespel.model;

import wybren_erik.hanzespel.City;
import wybren_erik.hanzespel.Location;

public class Boat {

    private Inventory inventory;
    private City location;
    private String name;

    public Boat(Inventory inventory, City location, String name) {
        this.inventory = inventory;
        this.location = location;
        this.name = name;
    }

    public Boat(Inventory inventory, String name) {
        this.inventory = inventory;
        this.location = new City(Location.KAMPEN);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public City getLocation() {
        return location;
    }

    public void goToCity(City location) {
        // TODO
    }
}
