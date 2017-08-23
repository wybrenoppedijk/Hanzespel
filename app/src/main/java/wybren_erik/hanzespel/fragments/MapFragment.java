package wybren_erik.hanzespel.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import wybren_erik.hanzespel.City;
import wybren_erik.hanzespel.R;
import wybren_erik.hanzespel.RoadMap;
import wybren_erik.hanzespel.Views.MapsCustomView;
import wybren_erik.hanzespel.dialog.ConfirmDialog;
import wybren_erik.hanzespel.interfaces.BoatListener;
import wybren_erik.hanzespel.interfaces.GameListener;
import wybren_erik.hanzespel.interfaces.OnDestenationClickHandler;
import wybren_erik.hanzespel.model.Boat;
import wybren_erik.hanzespel.model.Game;

public class MapFragment extends Fragment implements BoatListener, GameListener, OnDestenationClickHandler
{

    public static String travelText;
    private static int position;
    private Activity activity;

    private TextView boatNameTextView, boatLocationTextView, totalTimeTextView;
    private ProgressBar totalTimeBar;
    private Button confirmButton;

    private MapsCustomView maps1, maps2,maps3,maps4,maps5,maps6,maps7,maps8,maps9,maps10, maps11;
    private boolean isInit = false;

    private Boat boat;
    private Spinner travelList;
    private ConfirmDialog confirmDialog;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        MapsCustomView.addListener(this);

        if (!isInit) {
            Boat.addListener(this);
            Game.addListener(this);
            activity = getActivity();
        }

        final View view = inflater.inflate(R.layout.map_fragment, container, false);
        totalTimeBar = (ProgressBar) view.findViewById(R.id.map_total_time_progressbar);
        totalTimeTextView = (TextView) view.findViewById(R.id.map_total_time_text);

        maps1 = (MapsCustomView) view.findViewById(R.id.maps1);
        maps2 = (MapsCustomView) view.findViewById(R.id.maps2);
        maps3 = (MapsCustomView) view.findViewById(R.id.maps3);
        maps4 = (MapsCustomView) view.findViewById(R.id.maps4);
        maps5 = (MapsCustomView) view.findViewById(R.id.maps5);
        maps6 = (MapsCustomView) view.findViewById(R.id.maps6);
        maps7 = (MapsCustomView) view.findViewById(R.id.maps7);
        maps8 = (MapsCustomView) view.findViewById(R.id.maps8);
        maps9 = (MapsCustomView) view.findViewById(R.id.maps9);
        maps10 = (MapsCustomView) view.findViewById(R.id.maps10);
        maps11 = (MapsCustomView) view.findViewById(R.id.maps11);

        maps1.init();
        maps2.init();
        maps3.init();
        maps4.init();
        maps5.init();
        maps6.init();
        maps7.init();
        maps8.init();
        maps9.init();
        maps10.init();
        maps11.init();

        if (boat == null) boat = Boat.getInstance();


        //boatNameTextView.setText(boat.getName());
        totalTimeBar.setMax((int) Game.getTotalGameTime());
        long remainingTime = Game.getRemainingGameTime();
        totalTimeTextView.setText(String.format(Locale.ENGLISH, "%02d:%02d", (remainingTime / 1000) / 60, (remainingTime / 1000) % 60));

        if (Boat.isInDock()) {
            //boatLocationTextView.setText(boat.getLocation().getName().toString());
            maps1.setEnabled(true);
            maps2.setEnabled(true);
            maps3.setEnabled(true);
            maps4.setEnabled(true);
            maps5.setEnabled(true);
            maps6.setEnabled(true);
            maps7.setEnabled(true);
            maps8.setEnabled(true);
            maps9.setEnabled(true);
            maps10.setEnabled(true);
            maps11.setEnabled(true);

            ArrayList<City> cities = new ArrayList(RoadMap.getInstance().getCities());
            maps1.setDestination(cities.get(0));
            maps2.setDestination(cities.get(1));
            maps3.setDestination(cities.get(2));
            maps4.setDestination(cities.get(3));
            maps5.setDestination(cities.get(4));
            maps6.setDestination(cities.get(5));
            maps7.setDestination(cities.get(6));
            maps8.setDestination(cities.get(7));
            maps9.setDestination(cities.get(8));
            maps10.setDestination(cities.get(9));
            maps11.setDestination(cities.get(10));

            maps1.setTravelTime(boat.getTimeTO(cities.get(0)));
            maps2.setTravelTime(boat.getTimeTO(cities.get(1)));
            maps3.setTravelTime(boat.getTimeTO(cities.get(2)));
            maps4.setTravelTime(boat.getTimeTO(cities.get(3)));
            maps5.setTravelTime(boat.getTimeTO(cities.get(4)));
            maps6.setTravelTime(boat.getTimeTO(cities.get(5)));
            maps7.setTravelTime(boat.getTimeTO(cities.get(6)));
            maps8.setTravelTime(boat.getTimeTO(cities.get(7)));
            maps9.setTravelTime(boat.getTimeTO(cities.get(8)));
            maps10.setTravelTime(boat.getTimeTO(cities.get(9)));
            maps11.setTravelTime(boat.getTimeTO(cities.get(10)));
        } else {
            boatLocationTextView.setText(travelText);
            travelList.setSelection(position);
            travelList.setEnabled(false);
            maps1.setEnabled(false);
            maps2.setEnabled(false);
            maps3.setEnabled(false);
            maps4.setEnabled(false);
            maps5.setEnabled(false);
            maps6.setEnabled(false);
            maps7.setEnabled(false);
            maps8.setEnabled(false);
            maps9.setEnabled(false);
            maps10.setEnabled(false);
            maps11.setEnabled(false);
        }

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.cities_vector, android.R.layout.simple_spinner_dropdown_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        travelList.setAdapter(adapter);



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

    @Override
    public void onClickListener(City city) {
        if (!Boat.isInDock()) return;
        if (boat.getLocation().equals(travelList.getSelectedItem())) return;
        boat.setDestination(city);
        confirmDialog.show(getFragmentManager(), "travel_confirmation");

        position = travelList.getSelectedItemPosition();
    }
}
