package wybren_erik.hanzespel.fragments;

import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import wybren_erik.hanzespel.Location;
import wybren_erik.hanzespel.R;
import wybren_erik.hanzespel.RoadMap;
import wybren_erik.hanzespel.interfaces.BoatListener;
import wybren_erik.hanzespel.model.Boat;
import wybren_erik.hanzespel.model.InventoryModel;

public class MapFragment extends Fragment implements BoatListener {

    TextView boatNameTextView;
    TextView boatLocationTextView;
    boolean isInit = false;
    private RoadMap roadMap;
    private Boat boat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!isInit) {
            Boat.addListener(this);
        }
        final View view = inflater.inflate(R.layout.map_fragment, container, false);
        boatNameTextView = (TextView) view.findViewById(R.id.map_menu_boat_name);
        boatLocationTextView = (TextView) view.findViewById(R.id.map_menu_current_location);
        final Button debugButton = (Button) view.findViewById(R.id.DEBUG_BUTTON);
        roadMap = RoadMap.getInstance();

        if (boat == null) boat = new Boat(InventoryModel.getInstance(), "Het Schip der Null");
        boatNameTextView.setText(boat.getName());
        boatLocationTextView.setText(boat.getLocation().getName().toString());

        debugButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boat.goToCity(roadMap.getCity(Location.BERGEN));
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
        // TODO: Put this in MainActivity since stuff with multithreading
    }

    @Override
    public void onArrivalTimeChanged(long newTimeUntilArrival) {
        // Not needed yet, TODO
    }
}
