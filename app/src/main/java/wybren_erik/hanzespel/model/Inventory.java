package wybren_erik.hanzespel.model;

import wybren_erik.hanzespel.Product;

/**
 * Created by wybrenoppedijk on 19/05/2017.
 */

public class Inventory {
    private static Inventory inventory = null;
    private Product product;
    private int money;

    public Inventory() {
    }

    public static Inventory getInstance(){
        if (inventory == null) {
            inventory = new Inventory();
        }
        return inventory;
    }

    public static Inventory getInventory() {
        return inventory;
    }

    public Product getProduct() {
        return product;
    }

    public int getMoney() {
        return money;
    }

    public static void setInventory(Inventory inventory) {
        Inventory.inventory = inventory;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
