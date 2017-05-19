package wybren_erik.hanzespel.activiy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import wybren_erik.hanzespel.City;
import wybren_erik.hanzespel.Location;
import wybren_erik.hanzespel.R;
import wybren_erik.hanzespel.model.Boat;
import wybren_erik.hanzespel.model.Inventory;

public class MapFragment extends Fragment {

    private Boat boat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment, container, false);
        TextView boatNameTextView = (TextView) view.findViewById(R.id.map_menu_boat_name);
        TextView boatLocationTextView = (TextView) view.findViewById(R.id.map_menu_current_location);
        Button debugButton = (Button) view.findViewById(R.id.DEBUG_BUTTON);

        if(boat == null) boat = new Boat(new Inventory(), "Het Schip der Null");
        boatNameTextView.setText(boat.getName());
        boatLocationTextView.setText(boat.getLocation().getName().toString());

        debugButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boat.goToCity(new City(Location.BERGEN));
            }
        });

        return view;
    }

    public void assignBoat(Boat boat) {
        this.boat = boat;
    }

}
