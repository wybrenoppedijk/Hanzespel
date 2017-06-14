package wybren_erik.hanzespel.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import wybren_erik.hanzespel.interfaces.BoatListener;
import wybren_erik.hanzespel.model.Boat;
import wybren_erik.hanzespel.model.InventoryModel;

public class MapFragment extends Fragment implements BoatListener {

    TextView boatNameTextView;
    TextView boatLocationTextView;
    Button confirmButton;
    boolean isInit = false;
    private RoadMap roadMap;
    private Boat boat;
    private static City destination;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!isInit) {
            Boat.addListener(this);
        }
        final View view = inflater.inflate(R.layout.map_fragment, container, false);
        boatNameTextView = (TextView) view.findViewById(R.id.map_menu_boat_name);
        boatLocationTextView = (TextView) view.findViewById(R.id.map_menu_current_location);
        final Spinner travelList = (Spinner) view.findViewById(R.id.map_menu_travel_list);
        confirmButton = (Button) view.findViewById(R.id.map_menu_button_go);

        roadMap = RoadMap.getInstance();

        if (boat == null) boat = new Boat(InventoryModel.getInstance(), "Het Schip der Null");
        boatNameTextView.setText(boat.getName());
        boatLocationTextView.setText(boat.getLocation().getName().toString());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.cities_vector, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        travelList.setAdapter(adapter);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object selectedLocation = travelList.getSelectedItem();
                City destination = RoadMap.getInstance().getCity(Location.fromString((String) selectedLocation));
                if(boat.getLocation().equals(travelList.getSelectedItem())) return;
                boat.goToCity(destination);
                boatLocationTextView.setText("Aan het reizen van " + boatLocationTextView.getText() + " naar " + (String) selectedLocation);
                MapFragment.destination = destination;
            }
        });

        isInit = true;
        return view;
    }

    public void assignBoat(Boat boat) {
        this.boat = boat;
    }

    @Override
    public void onArrive() {
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                boatLocationTextView.setText(destination.getName().toString());
            }
        });
    }

    @Override
    public void onArrivalTimeChanged() {
        // Not needed yet, TODO
    }
}
