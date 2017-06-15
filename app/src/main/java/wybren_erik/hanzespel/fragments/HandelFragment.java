package wybren_erik.hanzespel.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;

import wybren_erik.hanzespel.ProductEnum;
import wybren_erik.hanzespel.R;
import wybren_erik.hanzespel.controller.SellAdapter;
import wybren_erik.hanzespel.dialog.ArrivedDialog;
import wybren_erik.hanzespel.interfaces.BoatListener;
import wybren_erik.hanzespel.interfaces.ItemTradeHandler;
import wybren_erik.hanzespel.model.Boat;
import wybren_erik.hanzespel.model.InventoryModel;
import wybren_erik.hanzespel.model.Product;

public class HandelFragment extends Fragment implements ItemTradeHandler {

    TextView totalAmountTV;
    InventoryModel model = InventoryModel.getInstance();
    private boolean isInit = false;
    private int totalValue = 0;
    private HashMap<Product, Integer> productHashmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_handel, container, false);
        SellAdapter.addListener(this);

        if (!isInit) {
            InventoryModel.getInstance().addProduct(new Product(ProductEnum.ZOUT, 3)); //For debug
            InventoryModel.getInstance().addProduct(new Product(ProductEnum.STOKVIS, 3)); //For debug
            InventoryModel.getInstance().addProduct(new Product(ProductEnum.BONT, 3)); //For debug
            isInit = true;
        }

        final SellAdapter sellAdapter = new SellAdapter(getContext(), InventoryModel.getInstance().getProducts());

        ListView listView = (ListView) view.findViewById(R.id.handel_inventory_products);
        Button sellButton = (Button) view.findViewById(R.id.sell_button);
        listView.setAdapter(sellAdapter);
        totalAmountTV = (TextView) view.findViewById(R.id.TradeProfitMoney);

        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productHashmap != null) {
                    model.addMoney(totalValue);
                    updateProductsInventory();
                    sellAdapter.notifyDataSetChanged();
                }
            }
        });

        return view;
    }

    private void updateProductsInventory() {

        for (HashMap.Entry<Product, Integer> entry : productHashmap.entrySet()) {
            for (int i = 0; i < model.getProducts().size(); i++) {
                if (model.getProducts().get(i) == entry.getKey()) {
                    model.getProducts().add(i, new Product(model.getProducts().get(i).getProductEnum(), model.getProducts().get(i).getAmount() - entry.getValue()));
                    break;
                }
            }
        }
    }

    @Override
    public void onTotalAmountChangedListener(int totalAmount, HashMap<Product, Integer> hashMap) {
        totalValue = totalAmount;
        productHashmap = hashMap;
        totalAmountTV.setText("Totale Waarde: " + totalAmount);
    }

}
