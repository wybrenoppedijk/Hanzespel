package wybren_erik.hanzespel;

import wybren_erik.hanzespel.interfaces.Vertex;

public class City implements Vertex<Location> {

    private Location location;
    private ProductEnum productEnum;

    public City(Location name) {
        this.location = name;
        this.productEnum = name.getProduct();
    }

    @Override
    public Location getName() {
        return location;
    }

    @Override
    public String toString() {
        return location.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Location)) return super.equals(o);
        return super.equals(RoadMap.getInstance().getCity((Location) o));
    }

}
