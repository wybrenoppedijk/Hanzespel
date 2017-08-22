package wybren_erik.hanzespel.controller;

import android.annotation.SuppressLint;
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

import wybren_erik.hanzespel.ProductEnum;
import wybren_erik.hanzespel.R;
import wybren_erik.hanzespel.SaleFactor;
import wybren_erik.hanzespel.interfaces.ItemTradeHandler;
import wybren_erik.hanzespel.model.Boat;
import wybren_erik.hanzespel.model.Product;

@SuppressLint("SetTextI18n")
public class SellAdapter extends ArrayAdapter<Product> {

    public static ArrayList<Product> productsToBeSold = new ArrayList<>();
    private static ItemTradeHandler listener;
    public static int totalAmountOfTradeValue;

    public SellAdapter(@NonNull Context context, ArrayList<Product> products) {
        super(context, R.layout.list_item, products);
    }

    public static void addListener(ItemTradeHandler itemTradeHandler) {
        listener = itemTradeHandler;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = convertView;
        if (convertView == null) {
            view = inflater.inflate(R.layout.list_item, parent, false);
        }

        final Product productFromInventory = getItem(position);
        final Product product = new Product(productFromInventory.getProductEnum(), 0); // Make local copy of product so that we can modify this one

        // UI init
        // TextViews
        TextView amount = (TextView) view.findViewById(R.id.handel_inventory_amount),
                productName = (TextView) view.findViewById(R.id.handel_inventory_prouct_name);
        final TextView amountOfTradeItemsTV = (TextView) view.findViewById(R.id.amountOfTradeItems);

        // ImageView
        ImageView icon = (ImageView) view.findViewById(R.id.handel_trade_icon);

        // Buttons
        final Button decrease = (Button) view.findViewById(R.id.decreaseTradeItem);
        final Button increase = (Button) view.findViewById(R.id.increaseTradeItem);

        // Define UI
        icon.setImageResource(product.getIcon());
        amount.setText("" + productFromInventory.getAmount()); // Make sure to use correct product here
        productName.setText(productFromInventory.toString());
        amountOfTradeItemsTV.setText("0");
        if (product.getAmount() <= 0) decrease.setEnabled(false);
        if (productFromInventory.getAmount() <= 0) increase.setEnabled(false);

        product.setZero();

        if (Boat.isInDock()) {

            increase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Otherwise do something
                    product.add();
                    totalAmountOfTradeValue += getProductValue(product.getProductEnum());
                    amountOfTradeItemsTV.setText("" + (product.getAmount()));
                    listener.onTotalAmountChangedListener(totalAmountOfTradeValue);
                    productsToBeSold.remove(product);
                    productsToBeSold.add(product);
                    decrease.setEnabled(true);
                    if (product.getAmount() >= productFromInventory.getAmount())
                        increase.setEnabled(false);

                }
            });

            decrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    product.deduct();
                    totalAmountOfTradeValue -= getProductValue(product.getProductEnum());

                    amountOfTradeItemsTV.setText("" + (product.getAmount()));
                    listener.onTotalAmountChangedListener(totalAmountOfTradeValue);

                    productsToBeSold.remove(product);
                    productsToBeSold.add(product);

                    if (product.getAmount() <= 0) decrease.setEnabled(false);
                    increase.setEnabled(true);
                }
            });

        } else {
            decrease.setEnabled(false);
            increase.setEnabled(false);
        }

        return view;
    }

    private int getProductValue(ProductEnum product) {
        return (int) (product.getPrice() * SaleFactor.getFactor(Boat.getInstance().getLocation(), product));
    }

}

