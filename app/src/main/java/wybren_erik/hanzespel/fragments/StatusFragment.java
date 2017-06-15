package wybren_erik.hanzespel.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;

import wybren_erik.hanzespel.R;
import wybren_erik.hanzespel.dialog.ArrivedDialog;
import wybren_erik.hanzespel.interfaces.BoatListener;
import wybren_erik.hanzespel.model.Boat;
import wybren_erik.hanzespel.model.InventoryModel;

public class StatusFragment extends Fragment implements BoatListener {

    private TextView positionTextView;
    private TextView balanceTextView;
    private TextView arrivalTimeTextView;
    private ProgressBar arrivalTimeBar;
    private boolean isInit = false;
    private ArrivedDialog arrivedDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!isInit) Boat.addListener(this);

        final View view = inflater.inflate(R.layout.status_fragment, container, false);

        positionTextView = (TextView) view.findViewById(R.id.status_position_text);
        balanceTextView = (TextView) view.findViewById(R.id.status_balance_text);
        arrivalTimeTextView = (TextView) view.findViewById(R.id.status_arrival_text);
        arrivalTimeBar = (ProgressBar) view.findViewById(R.id.status_arrival_progressbar);
        arrivedDialog = new ArrivedDialog();

        balanceTextView.setText("ƒ"+InventoryModel.getInstance().getMoney());


        if (!Boat.isInDock()) {
            positionTextView.setText(MapFragment.travelText);
        } else {
            positionTextView.setText(Boat.getInstance().getLocation().getName().toString());
        }

        isInit = true;
        return view;
    }


    @Override
    public void onArrive() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                positionTextView.setText(Boat.getInstance().getLocation().getName().toString());
                arrivedDialog.show(getFragmentManager(), "arrivedDialog");
            }
        });
    }

    @Override
    public void onArrivalTimeChanged(final long timeUntilArrival) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //arrivalTimeTextView.setText(String.format(Locale.ENGLISH, "%02d:%02d", timeUntilArrival / 60, timeUntilArrival % 60));
            }
        });
    }
}
