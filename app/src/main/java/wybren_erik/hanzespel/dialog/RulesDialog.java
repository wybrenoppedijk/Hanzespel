package wybren_erik.hanzespel.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class RulesDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        String message = "Het spel loopt als volgt:\n" +
                "U heeft anderhalf uur om te spelen. Het doel van het spel is om zoveel mogelijk geld te verzamelen.\n" +
                "U kunt handelen in twaalf steden, elk met een eigen product. Elk product heeft een andere waarde in een andere stad.\n" +
                "Een tabel van verkoopfactoren is te vinden bij de informatie knop.\n" +
                "Als u tien minuten in dezelfde haven blijft rondhangen wordt u teruggestuurd naar Kampen!\n" +
                "Succes!";
        builder.setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setTitle("Hanzespel");
        return builder.create();
    }

}
