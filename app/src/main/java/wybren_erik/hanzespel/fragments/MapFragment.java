package wybren_erik.hanzespel.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

import wybren_erik.hanzespel.City;
import wybren_erik.hanzespel.Location;
import wybren_erik.hanzespel.R;
import wybren_erik.hanzespel.RoadMap;
import wybren_erik.hanzespel.dialog.ConfirmDialog;
import wybren_erik.hanzespel.interfaces.BoatListener;
import wybren_erik.hanzespel.interfaces.GameListener;
import wybren_erik.hanzespel.model.Boat;
import wybren_erik.hanzespel.model.Game;

public class MapFragment extends Fragment implements BoatListener, GameListener {

    public static String travelText;
    private static int position;
    private Activity activity;

    private TextView boatNameTextView;
    private TextView boatLocationTextView;
    private TextView totalTimeTextView;
    private ProgressBar totalTimeBar;
    private Button confirmButton;
    private boolean isInit = false;

    private Boat boat;
    private Spinner travelList;
    private ConfirmDialog confirmDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!isInit) {
            Boat.addListener(this);
            Game.addListener(this);
            activity = getActivity();
        }

        final View view = inflater.inflate(R.layout.map_fragment, container, false);
        boatNameTextView = (TextView) view.findViewById(R.id.map_menu_boat_name);
        boatLocationTextView = (TextView) view.findViewById(R.id.map_menu_current_location);
        travelList = (Spinner) view.findViewById(R.id.map_menu_travel_list);
        confirmButton = (Button) view.findViewById(R.id.map_menu_button_go);
        totalTimeBar = (ProgressBar) view.findViewById(R.id.map_total_time_progressbar);
        totalTimeTextView = (TextView) view.findViewById(R.id.map_total_time_text);

        confirmDialog = new ConfirmDialog();

        if (boat == null) boat = Boat.getInstance();
        boatNameTextView.setText(boat.getName());
        totalTimeBar.setMax((int) Game.getTotalGameTime());
        long remainingTime = Game.getRemainingGameTime();
        totalTimeTextView.setText(String.format(Locale.ENGLISH, "%02d:%02d", (remainingTime / 1000) / 60, (remainingTime / 1000) % 60));

        if (Boat.isInDock()) boatLocationTextView.setText(boat.getLocation().getName().toString());
        else {
            boatLocationTextView.setText(travelText);
            travelList.setSelection(position);
            travelList.setEnabled(false);
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.cities_vector, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        travelList.setAdapter(adapter);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Boat.isInDock()) return;
                Object selectedLocation = travelList.getSelectedItem();
                City destination = RoadMap.getInstance().getCity(Location.fromString((String) selectedLocation));

                if (boat.getLocation().equals(travelList.getSelectedItem())) return;
                boat.setDestination(destination);
                confirmDialog.show(getFragmentManager(), "travel_confirmation");

                position = travelList.getSelectedItemPosition();
            }
        });

        isInit = true;
        return view;
    }

    @Override
    public void onDepart(long travelTime) {
        travelList.setEnabled(false);
        confirmButton.setEnabled(false);
        travelText = "Aan het reizen van " + boatLocationTextView.getText() + " naar " + boat.getDestination().toString();
        boatLocationTextView.setText(MapFragment.travelText);
    }

    @Override
    public void onArrive() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                boatLocationTextView.setText(boat.getDestination().toString());
                travelList.setEnabled(true);
                confirmButton.setEnabled(true);
            }
        });
    }

    @Override
    public void onArrivalTimeChanged(long timeUntilArrival) {
        // Not needed yet, TODO
    }

    @Override
    public void onGameTimeChanged(final long newTime) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                totalTimeTextView.setText(String.format(Locale.ENGLISH, "%02d:%02d", (newTime / 1000) / 60, (newTime / 1000) % 60));
                totalTimeBar.setProgress((int) newTime);
            }
        });
    }

    @Override
    public void onGameEnd() {

    }
}
