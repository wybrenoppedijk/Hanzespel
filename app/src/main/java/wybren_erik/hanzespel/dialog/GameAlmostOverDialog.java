package wybren_erik.hanzespel.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by wybrenoppedijk on 23/08/2017.
 */

public class GameAlmostOverDialog extends DialogFragment {
    private Uri intervention = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    private Ringtone r;
    @NonNull
    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        r = RingtoneManager.getRingtone(getContext(), intervention);
        r.play();
        builder.setMessage("U heeft nog 10 minuten. Vergeet niet om voor het einde van het spel in kampen te eindigen.\n" +
                "Anders verliest u 20% daalders"
        )
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }
}
