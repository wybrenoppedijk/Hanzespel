package wybren_erik.hanzespel.model;

import android.util.Log;

import java.util.ArrayList;

import wybren_erik.hanzespel.ProductEnum;

public class InventoryModel {
    private static InventoryModel inventoryModel = null;
    private ArrayList<Product> products = new ArrayList<>();
    private int money;
    private final String TAG = "Inventory";

    private InventoryModel() {
        money = 500;
        for(ProductEnum p : ProductEnum.values()) {
            products.add(new Product(p, 0));
        }
    }

    public static InventoryModel getInstance() {
        if (inventoryModel == null) {
            inventoryModel = new InventoryModel();
        }
        return inventoryModel;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public int getMoney() {
        return money;
    }

    void setMoney(int money) {
        this.money = money;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public void withdrawMoney(int money) {
        this.money -= money;
    }

    public void push(Product product) {
        boolean didChange = false;

        for(Product p : products) {
            // If product was already in inventory
            if(p.getProductEnum().equals(product.getProductEnum())) {
                p.add(product.getAmount());
                didChange = true;
            } // Else do nothing, probably different product
        } // If it wasn't and it didn't change; do change
        if(!didChange) {
            products.add(product);
        }
    }

    public void remove(ArrayList<Product> products) {

        for(Product thisProduct : this.products) {
            for(Product newProduct : products) {
                // If product types are the same deduct new from this
                if(thisProduct.getProductEnum().equals(newProduct.getProductEnum())) {
                    thisProduct.deduct(newProduct.getAmount());
                }
            }
        }

    }

    public void debugInventory() {
        Log.d(TAG, "*****INVENTORY DEBUG PRINT:");
        for(Product p : products) {
            Log.d(TAG, "*****" + p.toString() + ": " + p.getAmount());
        }
    }
}


