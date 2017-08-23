package wybren_erik.hanzespel.activiy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import wybren_erik.hanzespel.R;
import wybren_erik.hanzespel.controller.Intervention;
import wybren_erik.hanzespel.dialog.ArrivedDialog;
import wybren_erik.hanzespel.dialog.InterventionDialog;
import wybren_erik.hanzespel.dialog.RulesDialog;
import wybren_erik.hanzespel.fragments.HandelFragment;
import wybren_erik.hanzespel.fragments.MapFragment;
import wybren_erik.hanzespel.fragments.StatusFragment;
import wybren_erik.hanzespel.interfaces.BoatListener;
import wybren_erik.hanzespel.interfaces.InterventionListener;
import wybren_erik.hanzespel.model.Boat;
import wybren_erik.hanzespel.model.InventoryModel;

public class MainActivity extends AppCompatActivity implements BoatListener, InterventionListener {

    private StatusFragment statusFragment;
    private HandelFragment handelFragment;
    private MapFragment mapFragment;
    private Fragment currentFragment;
    private ArrivedDialog arrivedDialog;
    private RulesDialog rulesDialog;
    private InterventionDialog interventionDialog = new InterventionDialog();

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
        Intervention.addListener(this);

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
    public void onBackPressed() {
        // Do nothing
    }

    @Override
    public void onDepart(long travelTime) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        currentFragment = statusFragment;
        transaction.add(R.id.main_fragment, statusFragment);
        transaction.commit();
    }

    @Override
    public void onArrive() {
        arrivedDialog.show(getSupportFragmentManager(), "arrivedDialog");
    }

    @Override
    public void onArrivalTimeChanged(long timeUntilArrival) {
        // Ignored
    }

    @Override
    public void pirateship() {
        interventionDialog.text = "U bent aangevallen door een piratenschip! \n " +
                "U verliest een deel van uw lading.";
        interventionDialog.show(getSupportFragmentManager(), "interventionDialog");
    }

    @Override
    public void boatLeak() {
        interventionDialog.text = "Er zit een gat in uw boot! Gelukkig heeft u het kunnen repareren, maar u heeft wel vertraging opgelopen.";
        interventionDialog.show(getSupportFragmentManager(), "interventionDialog");
    }

    @Override
    public void crewOverboard() {
        interventionDialog.text = "Een matroos is overboord geslagen. U heeft vertraging opgelopen";
        interventionDialog.show(getSupportFragmentManager(), "interventionDialog");
    }

    @Override
    public void badWeather() {
        interventionDialog.text = "Er is een sterke tegenwind. U heeft vertraging opgelopen";
        interventionDialog.show(getSupportFragmentManager(), "interventionDialog");
    }

    @Override
    public void bandit() {
        if (InventoryModel.getInstance().getMoney() < 300) {
            interventionDialog.text = "U bent aangevallen door een bandiet, maar de bandiet heeft medelijden gekregen dat u zo weinig geld heeft. " +
                    "Hij laat 200 daalders achter voor een bed vannacht.";
            interventionDialog.show(getSupportFragmentManager(), "interventionDialog");
        } else {
            interventionDialog.text = "Een bandiet steelt een groot gedeelte van uw geld!";
            interventionDialog.show(getSupportFragmentManager(), "interventionDialog");
        }
    }

    @Override
    public void goodWeather() {
        interventionDialog.text = "De wind waait vol in de zeilen! U bent eerder op uw bestemming.";
        interventionDialog.show(getSupportFragmentManager(), "interventionDialog");
    }

    @Override
    public void foundHiddenChest() {
        interventionDialog.text = "U vindt een kist met 500 daalders!";
        interventionDialog.show(getSupportFragmentManager(), "interventionDialog");
    }

    @Override
    public void defeatPirateShip() {
        interventionDialog.text = "U bent een piratenschip tegengekomen! U hebt het verslagen en ontvangt 1000 daalders!";
        interventionDialog.show(getSupportFragmentManager(), "interventionDialog");
    }

    @Override
    public void shortCut() {
        interventionDialog.text = "U heeft een kortere route gevonden, U bent eerder op uw bestemming.";
        interventionDialog.show(getSupportFragmentManager(), "interventionDialog");
    }
}
