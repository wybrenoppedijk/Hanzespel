package wybren_erik.hanzespel.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import wybren_erik.hanzespel.ProductEnum;
import wybren_erik.hanzespel.R;
import wybren_erik.hanzespel.interfaces.ItemTradeHandler;
import wybren_erik.hanzespel.model.Boat;
import wybren_erik.hanzespel.model.Product;

/**
 * Created by wybrenoppedijk on 21/05/2017.
 */

public class SellAdapter extends ArrayAdapter<Product> {

    private static ItemTradeHandler listener;
    private int totalAmountOfTradeValue;


    //private int amountOfTradeItems = 0;
    private HashMap<Integer, Integer> amountOfTradeItems = new HashMap<>(); //ToDo Reset this one to null if dataset has changed.

    public SellAdapter(@NonNull Context context, ArrayList<Product> products) {
        super(context, R.layout.list_item, products);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = convertView;
        if (convertView == null) {
            view = inflater.inflate(R.layout.list_item, parent, false);
        }
        final Product product = getItem(position);

        TextView amount = (TextView) view.findViewById(R.id.handel_inventory_amount);
        TextView productName = (TextView) view.findViewById(R.id.handel_inventory_prouct_name);
        ImageView icon = (ImageView) view.findViewById(R.id.handel_trade_icon);

        Button decrease = (Button) view.findViewById(R.id.decreaseTradeItem);
        final TextView amountOfTradeItemsTV = (TextView) view.findViewById(R.id.amountOfTradeItems);
        Button increase = (Button) view.findViewById(R.id.increaseTradeItem);

        icon.setImageResource(getProperImage(product));
        amount.setText("" + product.getAmount());
        productName.setText(getProductName(product));

        amountOfTradeItemsTV.setText("" + 0);


        if (Boat.isInDock()) {

            decrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (hasmapDefaultValue(amountOfTradeItems, position) > 0) {
                        int temp =hasmapDefaultValue(amountOfTradeItems, position);
                        temp--;
                        amountOfTradeItems.put(position, temp);
                        amountOfTradeItemsTV.setText("" + hasmapDefaultValue(amountOfTradeItems, position));
                        totalAmountOfTradeValue -= getProductValue(product.getProductEnum());
                        listener.onTotalAmountChangedListener(totalAmountOfTradeValue, translateToProductsHashMap(amountOfTradeItems));
                    }
                }
            });
            increase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (hasmapDefaultValue(amountOfTradeItems, position) < product.getAmount()) {
                        int temp =hasmapDefaultValue(amountOfTradeItems, position);
                        temp++;
                        amountOfTradeItems.put(position, temp);
                        amountOfTradeItemsTV.setText("" + hasmapDefaultValue(amountOfTradeItems, position));
                        totalAmountOfTradeValue += getProductValue(product.getProductEnum());
                        listener.onTotalAmountChangedListener(totalAmountOfTradeValue, translateToProductsHashMap(amountOfTradeItems));
                    }
                }
            });
        } else {
            decrease.setVisibility(Button.GONE);
            amountOfTradeItemsTV.setVisibility(TextView.GONE);
            increase.setVisibility(Button.GONE);
        }

        return view;
    }
    public static void addListener(ItemTradeHandler itemTradeHandler) {
        listener = itemTradeHandler;
    }

    public int getProductValue(ProductEnum product){
        return product.getPrice();
    }

    private int hasmapDefaultValue(HashMap<Integer, Integer> hashMap, int key){
        if(!hashMap.containsKey(key)){
            return 0;
        } else {
            return hashMap.get(key);
        }
    }

    private int getProperImage(Product product) {
        switch (product.getProductEnum()){
            case BIER: return R.mipmap.beer;
            case STOKVIS: return R.mipmap.fish;
            case ZOUT: return R.mipmap.salt;
            case VATEN: return R.mipmap.barrel;
            case LAKEN: return R.mipmap.blanket;
            case WAS: return R.mipmap.wax;
            case BONT: return R.mipmap.fur;
            case IJZER: return R.mipmap.iron_bar;
            case GRAAN: return R.mipmap.wheet;
            case HOUT: return R.mipmap.wood;
            default: return R.mipmap.ic_launcher;
        }
    }

    private String getProductName(Product product) {
        switch (product.getProductEnum()){
            case BIER: return "Bier";
            case STOKVIS: return "Stokvis";
            case ZOUT: return "Zout";
            case VATEN: return "Vate";
            case LAKEN: return "Laken";
            case WAS: return "Was";
            case BONT: return "Bont";
            case IJZER: return "IJzer";
            case GRAAN: return "Graan";
            case HOUT: return "Hout";
            default: return "Error";
        }
    }
    
    private HashMap<Product, Integer> translateToProductsHashMap(HashMap<Integer, Integer> hashMap) {
        HashMap<Product, Integer> productHasmap = new HashMap<>();
        for (HashMap.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            int position = entry.getKey();
            int value = entry.getValue();
            productHasmap.put(getItem(position), value);
        }
        return productHasmap;
    }


}
