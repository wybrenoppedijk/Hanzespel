package wybren_erik.hanzespel.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import wybren_erik.hanzespel.City;
import wybren_erik.hanzespel.R;
import wybren_erik.hanzespel.RoadMap;
import wybren_erik.hanzespel.Views.MapsCustomView;
import wybren_erik.hanzespel.dialog.ConfirmDialog;
import wybren_erik.hanzespel.interfaces.GameListener;
import wybren_erik.hanzespel.interfaces.OnDestinationClickHandler;
import wybren_erik.hanzespel.model.Boat;
import wybren_erik.hanzespel.model.Game;

public class MapFragment extends Fragment implements GameListener, OnDestinationClickHandler {

    public static String travelText;
    private Activity activity;

    private TextView totalTimeTextView;
    private ProgressBar totalTimeBar;

    private MapsCustomView maps1, maps2, maps3, maps4, maps5, maps6, maps7, maps8, maps9, maps10, maps11;
    private boolean isInit = false;

    private Boat boat;
    private ConfirmDialog confirmDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        activity = getActivity();

        if (!isInit) {
            Game.addListener(this);
            MapsCustomView.addListener(this);
        }

        final View view = inflater.inflate(R.layout.map_fragment, container, false);
        totalTimeBar = (ProgressBar) view.findViewById(R.id.map_total_time_progressbar);
        totalTimeTextView = (TextView) view.findViewById(R.id.map_total_time_text);
        confirmDialog = new ConfirmDialog();

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

        totalTimeBar.setMax((int) Game.getTotalGameTime());
        long remainingTime = Game.getRemainingGameTime();
        totalTimeTextView.setText(String.format(Locale.ENGLISH, "%02d:%02d", (remainingTime / 1000) / 60, (remainingTime / 1000) % 60));

        initMaps(Boat.isInDock());

        isInit = true;
        return view;
    }

    private void initMaps(boolean enabled) {
        maps1.setEnabled(enabled);
        maps2.setEnabled(enabled);
        maps3.setEnabled(enabled);
        maps4.setEnabled(enabled);
        maps5.setEnabled(enabled);
        maps6.setEnabled(enabled);
        maps7.setEnabled(enabled);
        maps8.setEnabled(enabled);
        maps9.setEnabled(enabled);
        maps10.setEnabled(enabled);
        maps11.setEnabled(enabled);

        ArrayList<City> cities = new ArrayList(RoadMap.getInstance().getCities());
        Log.d("TAG", "Init city locations...");
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
        Log.d("TAG", "Completed!");

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
        if (boat.getLocation().equals(city)) return;
        boat.setDestination(city);
        confirmDialog.show(getFragmentManager(), "travel_confirmation");
    }
}
