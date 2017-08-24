package wybren_erik.hanzespel.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import wybren_erik.hanzespel.R;
import wybren_erik.hanzespel.interfaces.BoatListener;
import wybren_erik.hanzespel.interfaces.GameListener;
import wybren_erik.hanzespel.model.Boat;
import wybren_erik.hanzespel.model.Game;
import wybren_erik.hanzespel.model.InventoryModel;

public class StatusFragment extends Fragment implements BoatListener, GameListener {

    private static int travelTime;

    private Activity activity;
    private TextView positionTextView;
    private TextView balanceTextView;
    private TextView arrivalTimeTextView;
    private TextView totalTimeTextView;
    private TextView nameTextView;

    private ProgressBar arrivalTimeBar;
    private ProgressBar totalTimeBar;
    private boolean isInit = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!isInit) {
            Boat.addListener(this);
            Game.addListener(this);
            activity = getActivity();
        }

        final View view = inflater.inflate(R.layout.status_fragment, container, false);

        positionTextView = (TextView) view.findViewById(R.id.status_position_text);
        balanceTextView = (TextView) view.findViewById(R.id.status_balance_text);
        arrivalTimeTextView = (TextView) view.findViewById(R.id.status_arrival_text);
        totalTimeTextView = (TextView) view.findViewById(R.id.status_total_time_text);
        nameTextView = (TextView) view.findViewById(R.id.status_name_text);

        arrivalTimeBar = (ProgressBar) view.findViewById(R.id.status_arrival_progressbar);
        totalTimeBar = (ProgressBar) view.findViewById(R.id.status_total_time_progressbar);

        balanceTextView.setText("Ð " + InventoryModel.getInstance().getMoney());
        nameTextView.setText(Boat.getInstance().getName());
        totalTimeBar.setMax((int) Game.getTotalGameTime());
        long remainingTime = Game.getRemainingGameTime();
        totalTimeTextView.setText(String.format(Locale.ENGLISH, "%02d:%02d", (remainingTime / 1000) / 60, (remainingTime / 1000) % 60));

        if (!Boat.isInDock()) {
            positionTextView.setText(MapFragment.travelText);
            arrivalTimeTextView.setText(String.format(Locale.ENGLISH, "%02d:%02d", Boat.timeUntilArrival() / 60, Boat.timeUntilArrival() % 60));
            arrivalTimeBar.setMax(travelTime);
            arrivalTimeBar.setProgress(Boat.timeUntilArrival());
            positionTextView.setText("Ergens midden op de oceaan");
        } else {
            positionTextView.setText(
                    Boat.getInstance().getLocation().toString());
            arrivalTimeBar.setProgress(arrivalTimeBar.getMax());
            arrivalTimeTextView.setText("Gearriveerd op bestemming");
        }

        isInit = true;
        return view;
    }


    @Override
    public void onDepart(final long travelTime) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                arrivalTimeBar.setMax((int) travelTime);
                StatusFragment.travelTime = (int) travelTime;
            }
        });
    }

    @Override
    public void onArrive() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                positionTextView.setText(Boat.getInstance().getLocation().getName().toString());
                arrivalTimeBar.setProgress(arrivalTimeBar.getMax());
                arrivalTimeTextView.setText("Gearriveerd op bestemming");
            }
        });
    }

    @Override
    public void onArrivalTimeChanged(final long timeUntilArrival) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                arrivalTimeTextView.setText(String.format(Locale.ENGLISH, "%02d:%02d", timeUntilArrival / 60, timeUntilArrival % 60));
                arrivalTimeBar.setProgress((int) timeUntilArrival);
            }
        });
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
        // Ignored
    }

    @Override
    public void onWarnGameEnd() {
        // Ignored
    }
}
