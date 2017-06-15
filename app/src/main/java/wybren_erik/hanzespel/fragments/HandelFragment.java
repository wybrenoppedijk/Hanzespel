package wybren_erik.hanzespel.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import wybren_erik.hanzespel.ProductEnum;
import wybren_erik.hanzespel.R;
import wybren_erik.hanzespel.controller.InventoryAdapter;
import wybren_erik.hanzespel.dialog.ArrivedDialog;
import wybren_erik.hanzespel.interfaces.BoatListener;
import wybren_erik.hanzespel.interfaces.ItemTradeHandler;
import wybren_erik.hanzespel.model.Boat;
import wybren_erik.hanzespel.model.InventoryModel;
import wybren_erik.hanzespel.model.Product;

public class HandelFragment extends Fragment implements ItemTradeHandler, BoatListener {

    private boolean isInit = false;
    private int totalValue = 0;
    TextView totalAmountTV;
    private ArrivedDialog arrivedDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_handel, container, false);
        InventoryAdapter.addListener(this);

        if(!isInit) {
            InventoryModel.getInstance().addProduct(new Product(ProductEnum.ZOUT, 3)); //For debug
            InventoryModel.getInstance().addProduct(new Product(ProductEnum.STOKVIS, 3)); //For debug
            InventoryModel.getInstance().addProduct(new Product(ProductEnum.BONT, 3)); //For debug
            Boat.addListener(this);
            isInit = true;
        }

        InventoryAdapter adapter = new InventoryAdapter(getContext(), InventoryModel.getInstance().getProducts());
        arrivedDialog = new ArrivedDialog();

        ListView listView = (ListView) view.findViewById(R.id.handel_inventory_products);
        listView.setAdapter(adapter);
        totalAmountTV = (TextView) view.findViewById(R.id.TradeProfitMoney);
        System.out.println("DEBUG!");
        return view;
    }

    @Override
    public void onTotalAmountChangedListener(int totalAmount) {
        System.out.println(totalAmount);
        totalAmountTV.setText("Totale Waarde: " + totalAmount);
    }

    @Override
    public void onArrive() {
        arrivedDialog.show(getFragmentManager(), "arrivedDialog");
    }

    @Override
    public void onArrivalTimeChanged(long timeUntilArrival) {
        // Ignored
    }
}
