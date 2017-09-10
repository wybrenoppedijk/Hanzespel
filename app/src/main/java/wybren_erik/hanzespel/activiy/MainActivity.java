package wybren_erik.hanzespel.activiy;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import wybren_erik.hanzespel.City;
import wybren_erik.hanzespel.Location;
import wybren_erik.hanzespel.R;
import wybren_erik.hanzespel.RoadMap;
import wybren_erik.hanzespel.controller.Intervention;
import wybren_erik.hanzespel.dialog.ArrivedDialog;
import wybren_erik.hanzespel.dialog.DockInterventionWarningDialog;
import wybren_erik.hanzespel.dialog.GameAlmostOverDialog;
import wybren_erik.hanzespel.dialog.GameFinishedDialog;
import wybren_erik.hanzespel.dialog.InfoDialog;
import wybren_erik.hanzespel.dialog.InterventionDialog;
import wybren_erik.hanzespel.dialog.RulesDialog;
import wybren_erik.hanzespel.fragments.HandelFragment;
import wybren_erik.hanzespel.fragments.MapFragment;
import wybren_erik.hanzespel.fragments.StatusFragment;
import wybren_erik.hanzespel.interfaces.BoatListener;
import wybren_erik.hanzespel.interfaces.GameListener;
import wybren_erik.hanzespel.interfaces.InterventionListener;
import wybren_erik.hanzespel.model.Boat;
import wybren_erik.hanzespel.model.Game;
import wybren_erik.hanzespel.model.InventoryModel;

public class MainActivity extends AppCompatActivity implements BoatListener, InterventionListener, GameListener {

    private static ScheduledExecutorService executor;
    private StatusFragment statusFragment;
    private HandelFragment handelFragment;
    private MapFragment mapFragment;
    private Fragment currentFragment;
    // Dialogs...
    private ArrivedDialog arrivedDialog;
    private RulesDialog rulesDialog;
    private InfoDialog infoDialog;
    private InterventionDialog interventionDialog = new InterventionDialog();
    private GameFinishedDialog endGameDialog = new GameFinishedDialog();
    private GameAlmostOverDialog almostEndGameDialog = new GameAlmostOverDialog();
    private DockInterventionWarningDialog dockInterventionWarningDialog = new DockInterventionWarningDialog();
    private final Runnable dockInterventionWarning = new Runnable() {
        @Override
        public void run() {
            dockInterventionWarningDialog.show(getSupportFragmentManager(), "interventionWarningDialog");
        }
    };
    private BottomNavigationView navigation;
    private Uri intervention = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    private Ringtone r;
    private final Runnable dockIntervention = new Runnable() {
        @Override
        public void run() {
            City kampen = RoadMap.getInstance().getCity(Location.KAMPEN);
            if (!Boat.getInstance().getLocation().equals(kampen)) {
                interventionDialog.text = "U bent de haven uitgetrapt omdat u te lang bleef treuzelen. U wordt teruggestuurd naar kampen.";
                interventionDialog.image = R.drawable.img_dock;
                try {
                    r.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                interventionDialog.show(getSupportFragmentManager(), "dockInterventionDialog");
            }
        }
    };
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // Set own menu bar view as menu bar
        inflater.inflate(R.menu.default_bar, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Boat.addListener(this);
        Intervention.addListener(this);
        Game.addListener(this);

        r = RingtoneManager.getRingtone(getApplicationContext(), intervention);
        executor = Executors.newSingleThreadScheduledExecutor();

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        statusFragment = new StatusFragment();
        handelFragment = new HandelFragment();
        mapFragment = new MapFragment();
        arrivedDialog = new ArrivedDialog();
        rulesDialog = new RulesDialog();
        infoDialog = new InfoDialog();
        dockInterventionWarningDialog = new DockInterventionWarningDialog();

        rulesDialog.show(getSupportFragmentManager(), "rules_dialog");

        transaction.add(R.id.main_fragment, statusFragment);
        currentFragment = statusFragment;
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Add actions to menu bar
            case R.id.action_help:
                rulesDialog.show(getSupportFragmentManager(), "rules_dialog");
                break;
            case R.id.action_info:
                infoDialog.show(getSupportFragmentManager(), "rules_dialog");
                break;
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }

    @Override
    public void onDepart(long travelTime) {
        executor.shutdownNow();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        currentFragment = statusFragment;
        transaction.replace(R.id.main_fragment, statusFragment);
        transaction.commit();
    }

    @Override
    public void onArrive() {
        executor = Executors.newScheduledThreadPool(2);
        executor.schedule(dockIntervention, 10, TimeUnit.MINUTES);
        executor.schedule(dockInterventionWarning, 8, TimeUnit.MINUTES);
        try {
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
        arrivedDialog.show(getSupportFragmentManager(), "arrivedDialog");
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        currentFragment = statusFragment;
        t.replace(R.id.main_fragment, handelFragment);
        t.commit();

    }

    @Override
    public void onArrivalTimeChanged(long timeUntilArrival) {
        // Ignored
    }

    @Override
    public void pirateship() {
        try {
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
        interventionDialog.text = "U bent aangevallen door een piratenschip! \nU verliest een deel van uw lading.";
        interventionDialog.image = R.drawable.img_pirate;
        interventionDialog.show(getSupportFragmentManager(), "interventionDialog");
    }

    @Override
    public void boatLeak() {
        try {
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
        interventionDialog.text = "Er zit een gat in uw boot! Gelukkig heeft u het kunnen repareren, maar u heeft wel vertraging opgelopen.";
        interventionDialog.image = R.drawable.img_fire;
        interventionDialog.show(getSupportFragmentManager(), "interventionDialog");
    }

    @Override
    public void crewOverboard() {
        try {
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
        interventionDialog.text = "Een matroos is overboord geslagen. U heeft vertraging opgelopen";
        interventionDialog.image = R.drawable.img_drown;
        interventionDialog.show(getSupportFragmentManager(), "interventionDialog");
    }

    @Override
    public void badWeather() {
        try {
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
        interventionDialog.text = "Er is een sterke tegenwind. U heeft vertraging opgelopen";
        interventionDialog.image = R.drawable.img_storm;
        interventionDialog.show(getSupportFragmentManager(), "interventionDialog");
    }

    @Override
    public void bandit() {
        try {
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
        interventionDialog.image = R.drawable.img_bandit;
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
        try {
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
        interventionDialog.text = "De wind waait vol in de zeilen! U bent eerder op uw bestemming.";
        interventionDialog.image = R.drawable.img_goodweather;
        interventionDialog.show(getSupportFragmentManager(), "interventionDialog");
    }

    @Override
    public void foundHiddenChest() {
        try {
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
        interventionDialog.text = "U vindt een kist met 500 daalders!";
        interventionDialog.image = R.drawable.img_treasure;
        interventionDialog.show(getSupportFragmentManager(), "interventionDialog");
    }

    @Override
    public void defeatPirateShip() {
        try {
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
        interventionDialog.text = "U bent een piratenschip tegengekomen! U hebt het verslagen en ontvangt 1000 daalders!";
        interventionDialog.image = R.drawable.img_pirate;
        interventionDialog.show(getSupportFragmentManager(), "interventionDialog");
    }

    @Override
    public void shortCut() {
        try {
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
        interventionDialog.text = "U heeft een kortere route gevonden, U bent eerder op uw bestemming.";
        interventionDialog.image = R.drawable.img_goodweather;
        interventionDialog.show(getSupportFragmentManager(), "interventionDialog");
    }

    @Override
    public void onGameTimeChanged(long newTime) {
        // Ignored
    }

    @Override
    public void onGameEnd() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                endGameDialog.show(getSupportFragmentManager(), "game_over");
                City kampen = RoadMap.getInstance().getCity(Location.KAMPEN);
                if (!(Boat.getInstance().getLocation().equals(kampen) || Boat.isInDock()))
                    InventoryModel.getInstance().setMoney(InventoryModel.getInstance().getMoney() / 10 * 8);

                // Disable navigation and set active fragment to StatusFragment.
                navigation.setEnabled(false);
                navigation.setOnNavigationItemSelectedListener(null);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                currentFragment = statusFragment;
                transaction.detach(currentFragment);
                transaction.attach(statusFragment);
                transaction.commit();
            }
        });
    }

    @Override
    public void onWarnGameEnd() {
        almostEndGameDialog.show(getSupportFragmentManager(), "game_almost_over");
    }

}
