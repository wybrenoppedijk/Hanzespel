package wybren_erik.hanzespel.model;

import wybren_erik.hanzespel.City;
import wybren_erik.hanzespel.Location;

public class Boat {

    private Inventory inventory;
    private City location;

    public Boat(Inventory inventory, City location) {
        this.inventory = inventory;
        this.location = location;
    }

    public Boat(Inventory inventory) {
        this.inventory = inventory;
        this.location = new City(Location.KAMPEN);
    }

}
