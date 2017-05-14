package wybren_erik.hanzespel;

import wybren_erik.hanzespel.interfaces.Vertex;

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

    public Product getProduct() {
        return product;
    }

}
