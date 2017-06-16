package wybren_erik.hanzespel.model;

import java.util.ArrayList;

/**
 * Created by wybrenoppedijk on 19/05/2017.
 */

public class InventoryModel {
    private static InventoryModel inventoryModel = null;
    private ArrayList<Product> products = new ArrayList<Product>();
    private int money = 500;

    private InventoryModel() {
    }

    public static InventoryModel getInstance() {
        if (inventoryModel == null) {
            inventoryModel = new InventoryModel();
        }
        return inventoryModel;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }


    public ArrayList<Product> getProducts() {
        return products;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public void withdrawMoney(int money) {
        this.money -= money;
    }
}


