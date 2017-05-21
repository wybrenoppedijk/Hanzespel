package wybren_erik.hanzespel.model;

import wybren_erik.hanzespel.City;
import wybren_erik.hanzespel.Location;

public class Boat {

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
        this.location = new City(Location.KAMPEN);
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
        this.location = location;
    }
}
