package wybren_erik.hanzespel.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import wybren_erik.hanzespel.City;
import wybren_erik.hanzespel.model.Boat;

public class ConfirmDialog extends DialogFragment {

    private Boat boat;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.boat = Boat.getInstance();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final City destination = boat.getDestination();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setMessage("Bestemming: " + destination.toString() + ".\nNu vertrekken?");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boat.goToCity(destination);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Annuleren", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder.create();
    }


}
