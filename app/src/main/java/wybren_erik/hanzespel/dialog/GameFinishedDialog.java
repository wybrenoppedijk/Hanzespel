package wybren_erik.hanzespel.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import wybren_erik.hanzespel.model.Boat;
import wybren_erik.hanzespel.model.InventoryModel;

/**
 * Created by wybrenoppedijk on 23/08/2017.
 */

public class GameFinishedDialog extends DialogFragment {
    private InventoryModel inv = InventoryModel.getInstance();
    private Uri intervention = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    private Ringtone r;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        r = RingtoneManager.getRingtone(getContext(), intervention);
        r.play();
        if (Boat.getInstance().getLocation().toString().equals("Kampen")) {
            builder.setMessage("Het spel is afgelopen. Goed gedaan! U heeft Kampen gehaald met een bedrag van: " + InventoryModel.getInstance().getMoney() + "Ð.").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        } else {
            inv.setMoney(((inv.getMoney() / 5) * 4));
            builder.setMessage("Het spel is afgelopen. Helaas heeft u Kampen niet gehaald. Daarvoor krijgt u een boete. van 1000Ð, Uw eindbedrag is " + InventoryModel.getInstance().getMoney() + "Ð.").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        }
        Boat.getInstance().sink(); // Destroy boat
        return builder.create();
    }
}
