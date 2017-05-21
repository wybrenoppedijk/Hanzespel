package wybren_erik.hanzespel.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import wybren_erik.hanzespel.ProductEnum;
import wybren_erik.hanzespel.R;
import wybren_erik.hanzespel.model.Product;

/**
 * Created by wybrenoppedijk on 21/05/2017.
 */

public class InventoryAdapter extends ArrayAdapter<Product> {
    public InventoryAdapter(@NonNull Context context, ArrayList<Product> products) {
        super(context, R.layout.list_item, products);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = convertView;
        if (convertView == null) {
            view = inflater.inflate(R.layout.list_item, parent, false);
        }
        Product product = getItem(position);

        TextView amount = (TextView) view.findViewById(R.id.handel_inventory_amount);
        TextView productName = (TextView) view.findViewById(R.id.handel_inventory_prouct_name);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);

        icon.setImageResource(getProperImage(product));
        amount.setText("" + product.getAmount());
        productName.setText(getProductName(product));

        return view;
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
            case IJZER: return R.mipmap.ironBar;
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

}

