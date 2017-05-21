package wybren_erik.hanzespel.Views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wybrenoppedijk on 21/05/2017.
 */

public class InventoryView extends View {
    private int imageRes;


    public InventoryView(Context context) {
        super(context);
    }

    public InventoryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InventoryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

}
