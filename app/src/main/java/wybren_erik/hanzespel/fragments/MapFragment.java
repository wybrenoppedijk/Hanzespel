package wybren_erik.hanzespel.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import wybren_erik.hanzespel.City;
import wybren_erik.hanzespel.Location;
import wybren_erik.hanzespel.R;
import wybren_erik.hanzespel.RoadMap;
import wybren_erik.hanzespel.dialog.ArrivedDialog;
import wybren_erik.hanzespel.interfaces.BoatListener;
import wybren_erik.hanzespel.model.Boat;

public class MapFragment extends Fragment implements BoatListener {

    public static String travelText;
    private static City destination;
    private static int position;
    private static Activity activity;
    TextView boatNameTextView;
    TextView boatLocationTextView;
    Button confirmButton;
    boolean isInit = false;
    private Boat boat;
    private Spinner travelList;
    private ArrivedDialog arrivedDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!isInit) {
            Boat.addListener(this);
            activity = getActivity();
        }

        final View view = inflater.inflate(R.layout.map_fragment, container, false);
        boatNameTextView = (TextView) view.findViewById(R.id.map_menu_boat_name);
        boatLocationTextView = (TextView) view.findViewById(R.id.map_menu_current_location);
        travelList = (Spinner) view.findViewById(R.id.map_menu_travel_list);
        confirmButton = (Button) view.findViewById(R.id.map_menu_button_go);
        arrivedDialog = new ArrivedDialog();

        RoadMap roadMap = RoadMap.getInstance();

        if (boat == null) boat = Boat.getInstance();
        boatNameTextView.setText(boat.getName());
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
                boat.goToCity(destination);

                travelText = "Aan het reizen van " + boatLocationTextView.getText() + " naar " + selectedLocation;
                boatLocationTextView.setText(MapFragment.travelText);
                MapFragment.destination = destination;
                position = travelList.getSelectedItemPosition();
                travelList.setEnabled(false);
            }
        });

        isInit = true;
        return view;
    }

    public void assignBoat(Boat boat) {
        this.boat = boat;
    }

    @Override
    public void onDepart(long travelTime) {
        // Ignored
    }

    @Override
    public void onArrive() {
        MapFragment.activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                boatLocationTextView.setText(destination.getName().toString());
                travelList.setEnabled(true);
            }
        });
    }

    @Override
    public void onArrivalTimeChanged(long timeUntilArrival) {
        // Not needed yet, TODO
    }
}
