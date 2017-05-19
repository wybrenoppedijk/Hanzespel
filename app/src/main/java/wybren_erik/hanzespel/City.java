package wybren_erik.hanzespel;

import wybren_erik.hanzespel.interfaces.Vertex;

public class City implements Vertex<Location> {

    private Location location;
    private Product product;

    public City(Location name) {
        this.location = name;
        this.product = name.getProduct();
    }

    @Override
    public Location getName() {
        return location;
    }

    public Product getProduct() {
        return product;
    }

}
