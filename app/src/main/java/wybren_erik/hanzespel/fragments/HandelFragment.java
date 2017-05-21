package wybren_erik.hanzespel.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import wybren_erik.hanzespel.ProductEnum;
import wybren_erik.hanzespel.R;
import wybren_erik.hanzespel.controller.InventoryAdapter;
import wybren_erik.hanzespel.model.InventoryModel;
import wybren_erik.hanzespel.model.Product;

public class HandelFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_handel, container, false);
        InventoryModel.getInstance().addProduct(new Product(ProductEnum.ZOUT, 3));
        InventoryModel.getInstance().addProduct(new Product(ProductEnum.STOKVIS, 3));
        InventoryModel.getInstance().addProduct(new Product(ProductEnum.BONT, 3));

        InventoryAdapter adapter = new InventoryAdapter(getContext(), InventoryModel.getInstance().getProducts());

        ListView listView = (ListView) view.findViewById(R.id.handel_inventory_products);
        listView.setAdapter(adapter);

        return view;
    }

}
