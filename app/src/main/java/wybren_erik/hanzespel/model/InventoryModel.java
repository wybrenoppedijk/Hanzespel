package wybren_erik.hanzespel.model;

import android.util.Log;

import java.util.ArrayList;

import wybren_erik.hanzespel.ProductEnum;
import wybren_erik.hanzespel.exception.InventoryFullException;

public class InventoryModel {
    private static InventoryModel inventoryModel = null;
    private final String TAG = "Inventory";
    private ArrayList<Product> products = new ArrayList<>();
    private int money;
    private int occupation;

    private InventoryModel() {
        money = 500;
        for (ProductEnum p : ProductEnum.values()) {
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

    public void setMoney(int money) {
        this.money = money;
    }

    public void addMoney(int money) {
        this.money += money;
    }


    public void withdrawMoney(int money) {
        this.money -= money;
    }

    public void push(Product product) throws InventoryFullException {
        boolean didChange = false;

        for (Product p : products) {
            // If product was already in inventory
            if (p.getProductEnum().equals(product.getProductEnum())) {
                int amount = product.getAmount();
                p.add(amount);
                occupation += amount;
                didChange = true;
            } // Else do nothing, probably different product
        } // If it wasn't and it didn't change; do change
        if (!didChange) {
            products.add(product);
        }

        if (occupation > 4) throw new InventoryFullException();
    }

    public void remove(ArrayList<Product> products) {

        for (Product thisProduct : this.products) {
            for (Product newProduct : products) {
                // If product types are the same deduct new from this
                if (thisProduct.getProductEnum().equals(newProduct.getProductEnum())) {
                    int amount = newProduct.getAmount();
                    thisProduct.deduct(amount);
                    occupation -= amount;
                }
            }
        }

    }

    public int getOccupation() {
        return occupation;
    }

    public void removeHalf() {
        ArrayList<Product> toBeRemoved = new ArrayList<>();
        for(Product p : products) {
            toBeRemoved.add(new Product(p.getProductEnum(), p.getAmount() / 2));
        }
        remove(toBeRemoved);
    }

    public void debugInventory() {
        Log.d(TAG, "*****INVENTORY DEBUG PRINT:");
        for (Product p : products) {
            Log.d(TAG, "*****" + p.toString() + ": " + p.getAmount());
        }
    }
}


