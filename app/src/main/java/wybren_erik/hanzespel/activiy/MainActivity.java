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
import wybren_erik.hanzespel.interfaces.Graph;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RoadMap roadMap = new RoadMap();


        City kampen = new City("Kampen", Product.BIER);
        City bergen =new City("Bergen", Product.STOKVIS);
        City lubeck =new City("Lübeck", Product.ZOUT);
        City stralsund =new City("Stralsund", Product.VATEN);
        City riga =new City("Riga", Product.WAS);
        City tallin =new City("Tallin", Product.BONT);
        City visby =new City("Visby", Product.LAKEN);
        City aalborg =new City("Aalborg", Product.VLEES);
        City stockholm =new City("Stockholm", Product.IJZER);
        City danzig =new City("Danzig", Product.GRAAN);
        City turku =new City("Turku", Product.HOUT);

        roadMap.addVertex(kampen);
        roadMap.addVertex(bergen);
        roadMap.addVertex(lubeck);
        roadMap.addVertex(stralsund);
        roadMap.addVertex(riga);
        roadMap.addVertex(tallin);
        roadMap.addVertex(visby);
        roadMap.addVertex(aalborg);
        roadMap.addVertex(stockholm);
        roadMap.addVertex(danzig);
        roadMap.addVertex(turku);

        roadMap.addEdge(kampen, bergen, 18);
        roadMap.addEdge(kampen, lubeck, 20);
        roadMap.addEdge(kampen, stralsund, 20);
        roadMap.addEdge(kampen, riga, 32);
        roadMap.addEdge(kampen, tallin, 33);
        roadMap.addEdge(kampen, visby, 24);
        roadMap.addEdge(kampen, aalborg, 17);
        roadMap.addEdge(kampen, stockholm, 27);
        roadMap.addEdge(kampen, danzig, 25);
        roadMap.addEdge(kampen, turku, 34);

        roadMap.addEdge(bergen, kampen, 18);
        roadMap.addEdge(bergen, lubeck, 18);
        roadMap.addEdge(bergen, stralsund, 15);
        roadMap.addEdge(bergen, riga, 30);
        roadMap.addEdge(bergen, tallin, 28);
        roadMap.addEdge(bergen, visby, 22);
        roadMap.addEdge(bergen, aalborg, 10);
        roadMap.addEdge(bergen, stockholm, 26);
        roadMap.addEdge(bergen, danzig, 21);
        roadMap.addEdge(bergen, turku, 29);

        roadMap.addEdge(lubeck, kampen, 20);
        roadMap.addEdge(lubeck, bergen, 18);
        roadMap.addEdge(lubeck, stralsund, 4);
        roadMap.addEdge(lubeck, riga, 17);
        roadMap.addEdge(lubeck, tallin, 18);
        roadMap.addEdge(lubeck, visby, 6);
        roadMap.addEdge(lubeck, aalborg, 6);
        roadMap.addEdge(lubeck, stockholm, 15);
        roadMap.addEdge(lubeck, danzig, 9);
        roadMap.addEdge(lubeck, turku, 16);

        roadMap.addEdge(stralsund, kampen, 20);
        roadMap.addEdge(stralsund, bergen, 15);
        roadMap.addEdge(stralsund, lubeck, 4);
        roadMap.addEdge(stralsund, riga, 13);
        roadMap.addEdge(stralsund, tallin, 15);
        roadMap.addEdge(stralsund, visby, 9);
        roadMap.addEdge(stralsund, aalborg, 6);
        roadMap.addEdge(stralsund, stockholm, 12);
        roadMap.addEdge(stralsund, danzig, 8);
        roadMap.addEdge(stralsund, turku, 14);

        roadMap.addEdge(riga, kampen, 32);
        roadMap.addEdge(riga, bergen, 30);
        roadMap.addEdge(riga, lubeck, 17);
        roadMap.addEdge(riga, stralsund, 13);
        roadMap.addEdge(riga, tallin, 5);
        roadMap.addEdge(riga, visby, 6);
        roadMap.addEdge(riga, aalborg, 20);
        roadMap.addEdge(riga, stockholm, 9);
        roadMap.addEdge(riga, danzig, 5);
        roadMap.addEdge(riga, turku, 7);

        roadMap.addEdge(tallin, kampen, 33);
        roadMap.addEdge(tallin, bergen, 28);
        roadMap.addEdge(tallin, lubeck, 18);
        roadMap.addEdge(tallin, riga, 5);
        roadMap.addEdge(tallin, stralsund, 15);
        roadMap.addEdge(tallin, visby, 6);
        roadMap.addEdge(tallin, aalborg, 19);
        roadMap.addEdge(tallin, stockholm, 6);
        roadMap.addEdge(tallin, danzig, 11);
        roadMap.addEdge(tallin, turku,4);

        roadMap.addEdge(visby, kampen, 24);
        roadMap.addEdge(visby, bergen, 22);
        roadMap.addEdge(visby, lubeck, 6);
        roadMap.addEdge(visby, riga, 6);
        roadMap.addEdge(visby, tallin, 6);
        roadMap.addEdge(visby, stralsund, 9);
        roadMap.addEdge(visby, aalborg, 12);
        roadMap.addEdge(visby, stockholm, 4);
        roadMap.addEdge(visby, danzig, 8);
        roadMap.addEdge(visby, turku, 6);

        roadMap.addEdge(aalborg, kampen, 17);
        roadMap.addEdge(aalborg, bergen, 10);
        roadMap.addEdge(aalborg, lubeck, 6);
        roadMap.addEdge(aalborg, riga, 20);
        roadMap.addEdge(aalborg, tallin, 19);
        roadMap.addEdge(aalborg, visby, 12);
        roadMap.addEdge(aalborg, stralsund, 6);
        roadMap.addEdge(aalborg, stockholm, 18);
        roadMap.addEdge(aalborg, danzig, 12);
        roadMap.addEdge(aalborg, turku, 18);

        roadMap.addEdge(stockholm, kampen, 27);
        roadMap.addEdge(stockholm, bergen, 26);
        roadMap.addEdge(stockholm, lubeck, 15);
        roadMap.addEdge(stockholm, riga, 9);
        roadMap.addEdge(stockholm, tallin, 6);
        roadMap.addEdge(stockholm, visby, 4);
        roadMap.addEdge(stockholm, aalborg, 18);
        roadMap.addEdge(stockholm, stralsund, 12);
        roadMap.addEdge(stockholm, danzig, 11);
        roadMap.addEdge(stockholm, turku, 5);

        roadMap.addEdge(danzig, kampen, 25);
        roadMap.addEdge(danzig, bergen, 21);
        roadMap.addEdge(danzig, lubeck, 9);
        roadMap.addEdge(danzig, riga, 5);
        roadMap.addEdge(danzig, tallin, 11);
        roadMap.addEdge(danzig, visby, 8);
        roadMap.addEdge(danzig, aalborg, 12);
        roadMap.addEdge(danzig, stockholm, 11);
        roadMap.addEdge(danzig, stralsund, 8);
        roadMap.addEdge(danzig, turku, 10);

        roadMap.addEdge(turku, kampen, 34);
        roadMap.addEdge(turku, bergen, 29);
        roadMap.addEdge(turku, lubeck, 16);
        roadMap.addEdge(turku, riga, 7);
        roadMap.addEdge(turku, tallin, 4);
        roadMap.addEdge(turku, visby, 6);
        roadMap.addEdge(turku, aalborg, 18);
        roadMap.addEdge(turku, stockholm, 5);
        roadMap.addEdge(turku, danzig, 10);
        roadMap.addEdge(turku, stralsund, 14);








        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
