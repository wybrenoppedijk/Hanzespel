package wybren_erik.hanzespel.activiy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import wybren_erik.hanzespel.City;
import wybren_erik.hanzespel.Location;
import wybren_erik.hanzespel.R;
import wybren_erik.hanzespel.RoadMap;
import wybren_erik.hanzespel.dialog.ArrivedDialog;
import wybren_erik.hanzespel.dialog.RulesDialog;
import wybren_erik.hanzespel.fragments.HandelFragment;
import wybren_erik.hanzespel.fragments.MapFragment;
import wybren_erik.hanzespel.fragments.StatusFragment;
import wybren_erik.hanzespel.interfaces.BoatListener;
import wybren_erik.hanzespel.model.Boat;

public class MainActivity extends AppCompatActivity implements BoatListener {

    private StatusFragment statusFragment;
    private HandelFragment handelFragment;
    private MapFragment mapFragment;
    private Fragment currentFragment;
    private ArrivedDialog arrivedDialog;
    private RulesDialog rulesDialog;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            Fragment newFragment;
            switch (item.getItemId()) {
                case R.id.navigation_status:
                    newFragment = statusFragment;
                    transaction.replace(R.id.main_fragment, newFragment);
                    if (currentFragment != newFragment) {
                        transaction.remove(currentFragment);
                        currentFragment = newFragment;
                    }
                    break;
                case R.id.navigation_handel:
                    newFragment = handelFragment;
                    transaction.replace(R.id.main_fragment, newFragment);
                    if (currentFragment != newFragment) {
                        transaction.remove(currentFragment);
                        currentFragment = newFragment;
                    }
                    break;
                case R.id.navigation_map:
                    newFragment = mapFragment;
                    transaction.replace(R.id.main_fragment, newFragment);
                    if (currentFragment != newFragment) {
                        transaction.remove(currentFragment);
                        currentFragment = newFragment;
                    }
                    break;
            }
            transaction.commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Boat.addListener(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        statusFragment = new StatusFragment();
        handelFragment = new HandelFragment();
        mapFragment = new MapFragment();
        arrivedDialog = new ArrivedDialog();
        rulesDialog = new RulesDialog();

        rulesDialog.show(getSupportFragmentManager(), "rules_dialog");

        transaction.add(R.id.main_fragment, statusFragment);
        currentFragment = statusFragment;
        transaction.commit();
    }

    @Override
    public void onDepart(long travelTime) {
        //Ignored
    }

    @Override
    public void onArrive() {
        arrivedDialog.show(getSupportFragmentManager(), "arrivedDialog");
    }

    @Override
    public void onArrivalTimeChanged(long timeUntilArrival) {
        // Ignored
    }
}
