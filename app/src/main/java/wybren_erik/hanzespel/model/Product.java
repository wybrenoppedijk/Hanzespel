package wybren_erik.hanzespel.model;

import wybren_erik.hanzespel.ProductEnum;

public class Product {
    private int amount;
    private ProductEnum productEnum;

    public Product(ProductEnum productEnum, int amount) {
        this.productEnum = productEnum;
        this.amount = amount;
    }

    public ProductEnum getProductEnum() {
        return productEnum;
    }

    public int getAmount() {
        return amount;
    }

    public void add() {
        ++amount;
    }

    public void add(int amount) {
        this.amount += amount;
    }

    public void deduct() {
        --amount;
    }

    void deduct(int amount) {
        this.amount -= amount;
    }

    public void setZero() {
        amount = 0;
    }

    @Override
    public String toString() {
        return productEnum.toString();
    }

}
