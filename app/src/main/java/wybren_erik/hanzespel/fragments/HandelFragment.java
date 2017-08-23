package wybren_erik.hanzespel.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;

import wybren_erik.hanzespel.ProductEnum;
import wybren_erik.hanzespel.R;
import wybren_erik.hanzespel.controller.SellAdapter;
import wybren_erik.hanzespel.exception.InventoryFullException;
import wybren_erik.hanzespel.interfaces.GameListener;
import wybren_erik.hanzespel.interfaces.ItemTradeHandler;
import wybren_erik.hanzespel.model.Boat;
import wybren_erik.hanzespel.model.Game;
import wybren_erik.hanzespel.model.InventoryModel;
import wybren_erik.hanzespel.model.Product;

@SuppressLint("SetTextI18n")
public class HandelFragment extends Fragment implements ItemTradeHandler, GameListener { // TODO: Load stuff on background thread. Tablet currently skips 42 frames on load.

    private TextView totalAmountTV, totalGameTimeTV;
    private ProgressBar totalGameTimePB;
    private InventoryModel model = InventoryModel.getInstance();
    private boolean isInit = false;
    private int totalInventoryValue = 0,
            totalAmountOfItemsBought = 0,
            totalMoney = 0;
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_handel, container, false);
        if (!isInit) {
            SellAdapter.addListener(this);
            Game.addListener(this);
            isInit = true;
        }
        activity = getActivity();

        // Model unit init...
        final SellAdapter sellAdapter = new SellAdapter(getContext(), InventoryModel.getInstance().getProducts());
        final Boat boat = Boat.getInstance();
        totalMoney = model.getMoney();

        // UI init
        final ListView sellItemsListView = (ListView) view.findViewById(R.id.handel_inventory_products);
        final ImageView icon = (ImageView) view.findViewById(R.id.handel_buy_icon);
        totalGameTimePB = (ProgressBar) view.findViewById(R.id.handel_total_time_progressbar);

        // TextViews...
        final TextView itemName = (TextView) view.findViewById(R.id.handel_buy_name);
        final TextView amountBuyItems = (TextView) view.findViewById(R.id.amountOfBuyItems);
        final TextView price = (TextView) view.findViewById(R.id.handel_buy_price);
        final TextView totalPrice = (TextView) view.findViewById(R.id.total_buy_amount);
        final TextView balance = (TextView) view.findViewById(R.id.handel_bal_text);
        totalGameTimeTV = (TextView) view.findViewById(R.id.handel_total_time_text);
        totalAmountTV = (TextView) view.findViewById(R.id.TradeProfitMoney);

        // Buttons...
        final Button increaseButton = (Button) view.findViewById(R.id.increaseBuyItem);
        final Button decreaseButton = (Button) view.findViewById(R.id.decreaseBuyItem);
        final Button buyButton = (Button) view.findViewById(R.id.buy_button);
        final Button sellButton = (Button) view.findViewById(R.id.sell_button);

        // Define UI
        icon.setImageResource(Boat.getInstance().getLocation().getName().getProduct().getIcon());
        itemName.setText(Boat.getInstance().getLocation().getName().getProduct().toString());
        balance.setText(totalMoney + " Ð");
        buyButton.setEnabled(false);
        sellItemsListView.setAdapter(sellAdapter);

        // Total game time bar define
        long remainingTime = Game.getRemainingGameTime();
        totalGameTimeTV.setText(String.format(Locale.ENGLISH, "%02d:%02d", (remainingTime / 1000) / 60, (remainingTime / 1000) % 60));
        totalGameTimePB.setMax((int) Game.getTotalGameTime());
        price.setText("Ð " + Boat.getInstance().getLocation().getName().getProduct().getPrice());

        if (Boat.isInDock()) {
            increaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int newTotalPrice = boat.getLocation().getName().getProduct().getPrice() * (totalAmountOfItemsBought + 1);
                    if (newTotalPrice <= totalMoney && model.getOccupation() + totalAmountOfItemsBought < 4) {
                        totalAmountOfItemsBought++;
                        amountBuyItems.setText("" + totalAmountOfItemsBought);
                        totalPrice.setText("Ð " + boat.getLocation().getName().getProduct().getPrice() * totalAmountOfItemsBought);
                        buyButton.setEnabled(true);
                        decreaseButton.setEnabled(true);
                    }
                }
            });

            decreaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (totalAmountOfItemsBought > 0) {
                        totalAmountOfItemsBought--;
                        amountBuyItems.setText("" + totalAmountOfItemsBought);
                        totalPrice.setText("Ð " + Boat.getInstance().getLocation().getName().getProduct().getPrice() * totalAmountOfItemsBought);
                        if (totalAmountOfItemsBought == 0) { // int has already been updated here, hence we can do this
                            buyButton.setEnabled(false);
                            decreaseButton.setEnabled(false);
                        }
                    }
                }
            });

            amountBuyItems.setText("" + totalAmountOfItemsBought);
        } else {
            // Update UI if not in dock
            icon.setVisibility(ImageView.GONE);
            itemName.setVisibility(TextView.GONE);
            amountBuyItems.setVisibility(TextView.GONE);
            price.setVisibility(TextView.GONE);
            increaseButton.setVisibility(Button.GONE);
            decreaseButton.setVisibility(Button.GONE);
            sellButton.setEnabled(false);
            buyButton.setEnabled(false);
        }

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalAmountOfItemsBought > 0) {

                    ProductEnum productEnum = Boat.getInstance().getLocation().getName().getProduct();
                    Product product = new Product(productEnum, totalAmountOfItemsBought);
                    try {
                        model.push(product);
                    } catch (InventoryFullException e) {
                        e.printStackTrace();
                        return;
                    }
                    int price = productEnum.getPrice() * totalAmountOfItemsBought;
                    model.withdrawMoney(price);
                    totalMoney = model.getMoney();

                    // Update UI
                    sellAdapter.notifyDataSetChanged();
                    buyButton.setEnabled(false);
                    decreaseButton.setEnabled(false);
                    totalPrice.setText("0 Ð");
                    amountBuyItems.setText("0");
                    balance.setText(totalMoney + " Ð");
                    totalAmountOfItemsBought = 0;

                    // Debug
                    model.debugInventory();
                }
            }
        });

        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellAdapter.notifyDataSetChanged();
                model.addMoney(totalInventoryValue);
                totalMoney = model.getMoney();
                totalInventoryValue = 0;
                balance.setText(totalMoney + " Ð");
                model.remove(SellAdapter.productsToBeSold);
                SellAdapter.productsToBeSold.clear();
                totalAmountTV.setText("0 Ð");
                SellAdapter.totalAmountOfTradeValue = 0;

                model.debugInventory();
            }
        });

        return view;
    }

    @Override
    public void onGameTimeChanged(final long newTime) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                totalGameTimeTV.setText(String.format(Locale.ENGLISH, "%02d:%02d", (newTime / 1000) / 60, (newTime / 1000) % 60));
                totalGameTimePB.setProgress((int) newTime);
            }
        });
    }

    @Override
    public void onGameEnd() {

    }

    @Override
    public void onTotalAmountChangedListener(int totalAmount) {
        this.totalInventoryValue = totalAmount;
        totalAmountTV.setText(totalAmount + " Ð");
    }

}
