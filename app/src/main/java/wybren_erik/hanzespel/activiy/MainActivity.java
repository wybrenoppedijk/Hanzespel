package wybren_erik.hanzespel.activiy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import wybren_erik.hanzespel.City;
import wybren_erik.hanzespel.Product;
import wybren_erik.hanzespel.R;
import wybren_erik.hanzespel.RoadMap;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private RoadMap cityMap;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_status:
                    mTextMessage.setText(R.string.status_string);
                    return true;
                case R.id.navigation_handel:
                    mTextMessage.setText(R.string.handel_string);
                    return true;
                case R.id.navigation_map:
                    String allCities = "";
                    for(City c : cityMap.getCities()) {
                        allCities += c.getName() + ", ";
                    }
                    mTextMessage.setText(allCities);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMap();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @SuppressWarnings("unchecked")
    private void initMap() {
        cityMap = RoadMap.getInstance();

        City kampen = new City("Kampen", Product.BIER);
        City bergen = new City("Bergen", Product.STOKVIS);
        City lubeck = new City("Lübeck", Product.ZOUT);
        City stralsund = new City("Stralsund", Product.VATEN);
        City riga = new City("Riga", Product.WAS);
        City tallin = new City("Tallin", Product.BONT);
        City visby = new City("Visby", Product.LAKEN);
        City aalborg = new City("Aalborg", Product.VLEES);
        City stockholm = new City("Stockholm", Product.IJZER);
        City danzig = new City("Danzig", Product.GRAAN);
        City turku = new City("Turku", Product.HOUT);

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
        cityMap.addEdge(bergen, aalborg, 10);
        cityMap.addEdge(bergen, stockholm, 26);
        cityMap.addEdge(bergen, danzig, 21);
        cityMap.addEdge(bergen, turku, 29);

        cityMap.addEdge(lubeck, kampen, 20);
        cityMap.addEdge(lubeck, bergen, 18);
        cityMap.addEdge(lubeck, stralsund, 4);
        cityMap.addEdge(lubeck, riga, 17);
        cityMap.addEdge(lubeck, tallin, 18);
        cityMap.addEdge(lubeck, visby, 6);
        cityMap.addEdge(lubeck, aalborg, 6);
        cityMap.addEdge(lubeck, stockholm, 15);
        cityMap.addEdge(lubeck, danzig, 9);
        cityMap.addEdge(lubeck, turku, 16);

        cityMap.addEdge(stralsund, kampen, 20);
        cityMap.addEdge(stralsund, bergen, 15);
        cityMap.addEdge(stralsund, lubeck, 4);
        cityMap.addEdge(stralsund, riga, 13);
        cityMap.addEdge(stralsund, tallin, 15);
        cityMap.addEdge(stralsund, visby, 9);
        cityMap.addEdge(stralsund, aalborg, 6);
        cityMap.addEdge(stralsund, stockholm, 12);
        cityMap.addEdge(stralsund, danzig, 8);
        cityMap.addEdge(stralsund, turku, 14);

        cityMap.addEdge(riga, kampen, 32);
        cityMap.addEdge(riga, bergen, 30);
        cityMap.addEdge(riga, lubeck, 17);
        cityMap.addEdge(riga, stralsund, 13);
        cityMap.addEdge(riga, tallin, 5);
        cityMap.addEdge(riga, visby, 6);
        cityMap.addEdge(riga, aalborg, 20);
        cityMap.addEdge(riga, stockholm, 9);
        cityMap.addEdge(riga, danzig, 5);
        cityMap.addEdge(riga, turku, 7);

        cityMap.addEdge(tallin, kampen, 33);
        cityMap.addEdge(tallin, bergen, 28);
        cityMap.addEdge(tallin, lubeck, 18);
        cityMap.addEdge(tallin, riga, 5);
        cityMap.addEdge(tallin, stralsund, 15);
        cityMap.addEdge(tallin, visby, 6);
        cityMap.addEdge(tallin, aalborg, 19);
        cityMap.addEdge(tallin, stockholm, 6);
        cityMap.addEdge(tallin, danzig, 11);
        cityMap.addEdge(tallin, turku, 4);

        cityMap.addEdge(visby, kampen, 24);
        cityMap.addEdge(visby, bergen, 22);
        cityMap.addEdge(visby, lubeck, 6);
        cityMap.addEdge(visby, riga, 6);
        cityMap.addEdge(visby, tallin, 6);
        cityMap.addEdge(visby, stralsund, 9);
        cityMap.addEdge(visby, aalborg, 12);
        cityMap.addEdge(visby, stockholm, 4);
        cityMap.addEdge(visby, danzig, 8);
        cityMap.addEdge(visby, turku, 6);

        cityMap.addEdge(aalborg, kampen, 17);
        cityMap.addEdge(aalborg, bergen, 10);
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
        cityMap.addEdge(stockholm, tallin, 6);
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
        cityMap.addEdge(danzig, stralsund, 8);
        cityMap.addEdge(danzig, turku, 10);

        cityMap.addEdge(turku, kampen, 34);
        cityMap.addEdge(turku, bergen, 29);
        cityMap.addEdge(turku, lubeck, 16);
        cityMap.addEdge(turku, riga, 7);
        cityMap.addEdge(turku, tallin, 4);
        cityMap.addEdge(turku, visby, 6);
        cityMap.addEdge(turku, aalborg, 18);
        cityMap.addEdge(turku, stockholm, 5);
        cityMap.addEdge(turku, danzig, 10);
        cityMap.addEdge(turku, stralsund, 14);
    }

}
