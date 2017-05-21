package wybren_erik.hanzespel;

/**
 * Created by wybrenoppedijk on 21/05/2017.
 */

public class PlayerInventory {
    private Product product;
    private int amount;

    public PlayerInventory(Product product, int amount) {
        this.product = product;
        this.amount = amount;

    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
