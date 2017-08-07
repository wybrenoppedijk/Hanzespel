package wybren_erik.hanzespel.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;

import wybren_erik.hanzespel.ProductEnum;
import wybren_erik.hanzespel.R;
import wybren_erik.hanzespel.SaleFactor;
import wybren_erik.hanzespel.controller.SellAdapter;
import wybren_erik.hanzespel.interfaces.ItemTradeHandler;
import wybren_erik.hanzespel.model.Boat;
import wybren_erik.hanzespel.model.InventoryModel;
import wybren_erik.hanzespel.model.Product;

public class HandelFragment extends Fragment implements ItemTradeHandler {

    private TextView totalAmountTV;
    private InventoryModel model = InventoryModel.getInstance();
    private boolean isInit = false;
    private int totalInventoryValue = 0;
    private int totalBuyValue = 0;
    private int totalBuyItems = 0;
    private HashMap<Product, Integer> productHashmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_handel, container, false);
        SellAdapter.addListener(this);


        final SellAdapter sellAdapter = new SellAdapter(getContext(), InventoryModel.getInstance().getProducts());

        ListView listView = (ListView) view.findViewById(R.id.handel_inventory_products);
        ImageView icon = (ImageView) view.findViewById(R.id.handel_buy_icon);
        TextView itemName = (TextView) view.findViewById(R.id.handel_buy_name);
        final TextView amountBuyItems = (TextView) view.findViewById(R.id.amountOfBuyItems);
        TextView price = (TextView) view.findViewById(R.id.handel_buy_price);
        final TextView totalPrice = (TextView) view.findViewById(R.id.total_buy_amount);
        Button increaseButton = (Button) view.findViewById(R.id.increaseBuyItem);
        Button decreaseButton = (Button) view.findViewById(R.id.decreaseBuyItem);

        icon.setImageResource(getProperImage(Boat.getInstance().getLocation().getName().getProduct()));
        itemName.setText(Boat.getInstance().getLocation().getName().getProduct().toString());
        price.setText("Ð " + Boat.getInstance().getLocation().getName().getProduct().getPrice());

        final Boat boat = Boat.getInstance();

        if (Boat.isInDock()) {
            increaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (totalBuyValue + (boat.getLocation().getName().getProduct().getPrice() * (totalBuyItems + 1)) <= model.getMoney()) {
                        totalBuyItems++;
                        amountBuyItems.setText("" + totalBuyItems);
                        totalPrice.setText("Ð " + boat.getLocation().getName().getProduct().getPrice() * totalBuyItems);
                    }
                }
            });

            decreaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (totalBuyItems > 0) {
                        totalBuyItems--;
                        amountBuyItems.setText("" + totalBuyItems);
                        totalPrice.setText("Ð " + Boat.getInstance().getLocation().getName().getProduct().getPrice() * totalBuyItems);
                    }
                }
            });

            amountBuyItems.setText("" + totalBuyItems);
        } else {
            icon.setVisibility(ImageView.GONE);
            itemName.setVisibility(TextView.GONE);
            amountBuyItems.setVisibility(TextView.GONE);
            price.setVisibility(TextView.GONE);
            increaseButton.setVisibility(Button.GONE);
            decreaseButton.setVisibility(Button.GONE);

            //ToDo Show placeholder later on.
        }

        Button buy = (Button) view.findViewById(R.id.buy_button);

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalBuyItems > 0) {
                    ProductEnum product = Boat.getInstance().getLocation().getName().getProduct();
                    int price = product.getPrice() * totalBuyItems;
                    model.withdrawMoney(price);
                    boolean alreadyInInventory = false;
                    int position = 0;
                    for (int i = 0; i < model.getProducts().size(); i++) {
                        if (model.getProducts().get(i).getProductEnum() == product) {
                            alreadyInInventory = true;
                            position = i;
                            break;
                        }
                    }
                    if (alreadyInInventory) {
                        int amount = model.getProducts().get(position).getAmount();
                        amount += totalBuyItems;
                        model.getProducts().add(position, new Product(product, amount));
                        totalBuyItems = 0;
                        sellAdapter.notifyDataSetChanged();
                    } else {
                        model.getProducts().add(new Product(product, totalBuyItems));
                        totalBuyItems = 0;
                        sellAdapter.notifyDataSetChanged();
                    }
                    amountBuyItems.setText("" + totalBuyItems);
                }
            }
        });


        Button sellButton = (Button) view.findViewById(R.id.sell_button);
        listView.setAdapter(sellAdapter);
        totalAmountTV = (TextView) view.findViewById(R.id.TradeProfitMoney);

        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productHashmap != null) {
                    model.addMoney(totalInventoryValue);
                    updateProductsInventory();
                    sellAdapter.notifyDataSetChanged();
                    totalInventoryValue = 0;
                }
            }
        });

        return view;
    }

    private void updateProductsInventory() {

        for (HashMap.Entry<Product, Integer> entry : productHashmap.entrySet()) {
            for (int i = 0; i < model.getProducts().size(); i++) {
                if (model.getProducts().get(i) == entry.getKey()) {

                    if (model.getProducts().get(i).getAmount() - entry.getValue() == 0) {
                        model.getProducts().remove(i);
                    } else {
                        model.getProducts().add(i, new Product(model.getProducts().get(i).getProductEnum(), model.getProducts().get(i).getAmount() - entry.getValue()));
                    }
                    break;
                }
            }
        }
    }

    private int getProperImage(ProductEnum product) {
        switch (product) {
            case BIER:
                return R.mipmap.beer;
            case STOKVIS:
                return R.mipmap.fish;
            case ZOUT:
                return R.mipmap.salt;
            case VATEN:
                return R.mipmap.barrel;
            case LAKEN:
                return R.mipmap.blanket;
            case WAS:
                return R.mipmap.wax;
            case BONT:
                return R.mipmap.fur;
            case IJZER:
                return R.mipmap.iron_bar;
            case GRAAN:
                return R.mipmap.wheet;
            case HOUT:
                return R.mipmap.wood;
            default:
                return R.mipmap.ic_launcher;
        }
    }

    @Override
    public void onTotalAmountChangedListener(int totalAmount, HashMap<Product, Integer> hashMap) {
        totalInventoryValue = totalAmount;
        productHashmap = hashMap;
        totalAmountTV.setText("Totale Waarde: " + totalAmount);
    }

}
