package wybren_erik.hanzespel.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;

import wybren_erik.hanzespel.Location;
import wybren_erik.hanzespel.R;
import wybren_erik.hanzespel.RoadMap;
import wybren_erik.hanzespel.model.Boat;

public class InterventionDialog extends DialogFragment {
    public String text;
    public int image = R.drawable.img_dock;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (text.isEmpty() || text == null) {
            throw new NullPointerException("Text not declared");
        }
        ImageView image = new ImageView(getContext());
        image.setImageResource(this.image);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(text)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(text.equals("U bent de haven uitgetrapt omdat u te lang bleef treuzelen. U wordt teruggestuurd naar kampen."))
                            Boat.getInstance().goToCity(RoadMap.getInstance().getCity(Location.KAMPEN));
                        dialog.dismiss();
                    }
                })
                .setView(image);
        return builder.create();
    }


}
