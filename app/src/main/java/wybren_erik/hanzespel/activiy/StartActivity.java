package wybren_erik.hanzespel.activiy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import wybren_erik.hanzespel.City;
import wybren_erik.hanzespel.Location;
import wybren_erik.hanzespel.R;
import wybren_erik.hanzespel.RoadMap;
import wybren_erik.hanzespel.dialog.RulesDialog;
import wybren_erik.hanzespel.model.Boat;
import wybren_erik.hanzespel.model.Game;

public class StartActivity extends AppCompatActivity {

    private static long TOTAL_GAME_TIME = 0x5265c0; // One and a half hours
    private EditText nameField;
    private Button startButton;
    private RulesDialog rulesDialog;
    private Intent mainActivityIntent;
    //private static long TOTAL_GAME_TIME = 0xEA60; // One minute, for debug

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);

        nameField = (EditText) findViewById(R.id.welcome_name);
        startButton = (Button) findViewById(R.id.welcome_start);
        rulesDialog = new RulesDialog();
        mainActivityIntent = new Intent(this, MainActivity.class);

        startButton.setEnabled(false);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initMap();
                String name = nameField.getText().toString();
                Boat.make(name);
                if (name.endsWith("!;snelspel\"")) TOTAL_GAME_TIME = 0x2710; // Ten seconds
                Game.start(TOTAL_GAME_TIME);
                startActivity(mainActivityIntent);
            }
        });

        nameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 3) startButton.setEnabled(true);
                else startButton.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Do nothing
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initMap() {
        RoadMap cityMap = RoadMap.getInstance();

        City kampen = new City(Location.KAMPEN);
        City bergen = new City(Location.BERGEN);
        City lubeck = new City(Location.LUBECK);
        City stralsund = new City(Location.STRALSUND);
        City riga = new City(Location.RIGA);
        City tallin = new City(Location.TALLIN);
        City visby = new City(Location.VISBY);
        City aalborg = new City(Location.AALBORG);
        City stockholm = new City(Location.STOCKHOLM);
        City danzig = new City(Location.DANZIG);
        City turku = new City(Location.TURKU);

        cityMap.addVertex(kampen);
        cityMap.addVertex(bergen);
        cityMap.addVertex(lubeck);
        cityMap.addVertex(stralsund);
        cityMap.addVertex(riga);
        cityMap.addVertex(tallin);
        cityMap.addVertex(visby);
        cityMap.addVertex(aalborg);
        cityMap.addVertex(stockholm);
        cityMap.addVertex(danzig);
        cityMap.addVertex(turku);

        cityMap.addEdge(kampen, bergen, 18);
        cityMap.addEdge(kampen, lubeck, 20);
        cityMap.addEdge(kampen, stralsund, 20);
        cityMap.addEdge(kampen, riga, 32);
        cityMap.addEdge(kampen, tallin, 33);
        cityMap.addEdge(kampen, visby, 24);
        cityMap.addEdge(kampen, aalborg, 17);
        cityMap.addEdge(kampen, stockholm, 27);
        cityMap.addEdge(kampen, danzig, 25);
        cityMap.addEdge(kampen, turku, 34);

        cityMap.addEdge(bergen, kampen, 18);
        cityMap.addEdge(bergen, lubeck, 18);
        cityMap.addEdge(bergen, stralsund, 15);
        cityMap.addEdge(bergen, riga, 30);
        cityMap.addEdge(bergen, tallin, 28);
        cityMap.addEdge(bergen, visby, 22);
        cityMap.addEdge(bergen, aalborg, 17);
        cityMap.addEdge(bergen, stockholm, 26);
        cityMap.addEdge(bergen, danzig, 21);
        cityMap.addEdge(bergen, turku, 29);

        cityMap.addEdge(lubeck, kampen, 20);
        cityMap.addEdge(lubeck, bergen, 18);
        cityMap.addEdge(lubeck, stralsund, 7);
        cityMap.addEdge(lubeck, riga, 17);
        cityMap.addEdge(lubeck, tallin, 18);
        cityMap.addEdge(lubeck, visby, 6);
        cityMap.addEdge(lubeck, aalborg, 6);
        cityMap.addEdge(lubeck, stockholm, 15);
        cityMap.addEdge(lubeck, danzig, 9);
        cityMap.addEdge(lubeck, turku, 16);

        cityMap.addEdge(stralsund, kampen, 20);
        cityMap.addEdge(stralsund, bergen, 15);
        cityMap.addEdge(stralsund, lubeck, 7);
        cityMap.addEdge(stralsund, riga, 13);
        cityMap.addEdge(stralsund, tallin, 15);
        cityMap.addEdge(stralsund, visby, 9);
        cityMap.addEdge(stralsund, aalborg, 6);
        cityMap.addEdge(stralsund, stockholm, 12);
        cityMap.addEdge(stralsund, danzig, 14);
        cityMap.addEdge(stralsund, turku, 14);

        cityMap.addEdge(riga, kampen, 32);
        cityMap.addEdge(riga, bergen, 30);
        cityMap.addEdge(riga, lubeck, 17);
        cityMap.addEdge(riga, stralsund, 13);
        cityMap.addEdge(riga, tallin, 5);
        cityMap.addEdge(riga, visby, 15);
        cityMap.addEdge(riga, aalborg, 20);
        cityMap.addEdge(riga, stockholm, 9);
        cityMap.addEdge(riga, danzig, 5);
        cityMap.addEdge(riga, turku, 15);

        cityMap.addEdge(tallin, kampen, 33);
        cityMap.addEdge(tallin, bergen, 28);
        cityMap.addEdge(tallin, lubeck, 18);
        cityMap.addEdge(tallin, riga, 5);
        cityMap.addEdge(tallin, stralsund, 15);
        cityMap.addEdge(tallin, visby, 8);
        cityMap.addEdge(tallin, aalborg, 19);
        cityMap.addEdge(tallin, stockholm, 14);
        cityMap.addEdge(tallin, danzig, 11);
        cityMap.addEdge(tallin, turku, 21);

        cityMap.addEdge(visby, kampen, 24);
        cityMap.addEdge(visby, bergen, 22);
        cityMap.addEdge(visby, lubeck, 6);
        cityMap.addEdge(visby, riga, 15);
        cityMap.addEdge(visby, tallin, 8);
        cityMap.addEdge(visby, stralsund, 9);
        cityMap.addEdge(visby, aalborg, 12);
        cityMap.addEdge(visby, stockholm, 4);
        cityMap.addEdge(visby, danzig, 8);
        cityMap.addEdge(visby, turku, 6);

        cityMap.addEdge(aalborg, kampen, 17);
        cityMap.addEdge(aalborg, bergen, 17);
        cityMap.addEdge(aalborg, lubeck, 6);
        cityMap.addEdge(aalborg, riga, 20);
        cityMap.addEdge(aalborg, tallin, 19);
        cityMap.addEdge(aalborg, visby, 12);
        cityMap.addEdge(aalborg, stralsund, 6);
        cityMap.addEdge(aalborg, stockholm, 18);
        cityMap.addEdge(aalborg, danzig, 12);
        cityMap.addEdge(aalborg, turku, 18);

        cityMap.addEdge(stockholm, kampen, 27);
        cityMap.addEdge(stockholm, bergen, 26);
        cityMap.addEdge(stockholm, lubeck, 15);
        cityMap.addEdge(stockholm, riga, 9);
        cityMap.addEdge(stockholm, tallin, 14);
        cityMap.addEdge(stockholm, visby, 4);
        cityMap.addEdge(stockholm, aalborg, 18);
        cityMap.addEdge(stockholm, stralsund, 12);
        cityMap.addEdge(stockholm, danzig, 11);
        cityMap.addEdge(stockholm, turku, 5);

        cityMap.addEdge(danzig, kampen, 25);
        cityMap.addEdge(danzig, bergen, 21);
        cityMap.addEdge(danzig, lubeck, 9);
        cityMap.addEdge(danzig, riga, 5);
        cityMap.addEdge(danzig, tallin, 11);
        cityMap.addEdge(danzig, visby, 8);
        cityMap.addEdge(danzig, aalborg, 12);
        cityMap.addEdge(danzig, stockholm, 11);
        cityMap.addEdge(danzig, stralsund, 14);
        cityMap.addEdge(danzig, turku, 19);

        cityMap.addEdge(turku, kampen, 34);
        cityMap.addEdge(turku, bergen, 29);
        cityMap.addEdge(turku, lubeck, 16);
        cityMap.addEdge(turku, riga, 15);
        cityMap.addEdge(turku, tallin, 21);
        cityMap.addEdge(turku, visby, 6);
        cityMap.addEdge(turku, aalborg, 18);
        cityMap.addEdge(turku, stockholm, 5);
        cityMap.addEdge(turku, danzig, 19);
        cityMap.addEdge(turku, stralsund, 14);
    }

}
