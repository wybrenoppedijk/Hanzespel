package wybren_erik.hanzespel.model;

import wybren_erik.hanzespel.ProductEnum;

/**
 * Created by wybrenoppedijk on 21/05/2017.
 */

public class Product {
    private ProductEnum productEnum;
    private int amount;

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

    public void setProductEnum(ProductEnum productEnum) {
        this.productEnum = productEnum;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
