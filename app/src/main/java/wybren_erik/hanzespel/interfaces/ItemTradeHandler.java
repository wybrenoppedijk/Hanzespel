package wybren_erik.hanzespel.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

import wybren_erik.hanzespel.ProductEnum;
import wybren_erik.hanzespel.model.Product;

/**
 * Created by wybrenoppedijk on 14/06/2017.
 */

public interface ItemTradeHandler {

    void onTotalAmountChangedListener(int totalAmount, HashMap<Product, Integer> hashMap);
}
