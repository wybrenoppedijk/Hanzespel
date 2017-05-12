package wybren_erik.hanzespel;

import wybren_erik.hanzespel.interfaces.Vertex;

/**
 * Created by wybrenoppedijk on 12/05/2017.
 */

public class City implements Vertex<String> {

    private String name;
    private Product product;

    public City(String name, Product product) {
        this.name = name;
        this.product = product;
    }

    @Override
    public String getName() {
        return name;
    }
}
