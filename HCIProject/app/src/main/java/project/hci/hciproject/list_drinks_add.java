package project.hci.hciproject;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.WindowManager;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import project.hci.hciproject.realm.Bar;
import project.hci.hciproject.realm.Drink;
import project.hci.hciproject.realm.DrinkType;
import project.hci.hciproject.util.GyroSensorLogic;


public class list_drinks_add extends ListActivity {

    private ArrayList<Drink> items;
    private RecyclerView rvContacts;
    private BarAdapter adapter;

    private Realm realm;

    private SensorManager sensorManager;
    private Sensor sensor;
    private long timestamp;
    private final float[] deltaRotationVector = new float[4];

    private SensorEventListener gyroscopeListener;

    private int adapterPos;

    private SharedPreferences sharedPreferences;

    private RealmResults<Drink> drinks = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_drinks_add);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        sharedPreferences = getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE);

        realm = Realm.getDefaultInstance();
        items = new ArrayList<>();
        // ...
        // Lookup the recyclerview in activity layout
        rvContacts = (RecyclerView) findViewById(R.id.rvItems);

        RealmResults<Bar> bar = realm.where(Bar.class)
                .equalTo("bar_name", sharedPreferences.getString(BarActivity.BAR_NAME, null))
                .findAll();

        RealmResults<DrinkType> drinkType = realm.where(DrinkType.class)
                .equalTo("type",
                        sharedPreferences.getString(DrinkTypeBarsActivity.DRINK_TYPE, null))
                .findAll();


        if (bar.size() == 1 && drinkType.size() == 1) {
            drinks = realm.where(Drink.class)
                    .equalTo("bar.bar_name", bar.get(0).getBar_name()).findAll();
            drinks = drinks.where().equalTo("type.type", drinkType.get(0).getDrink()).findAll();
        }

        for (Drink drink : drinks) {
            items.add(drink);
        }

    }




}
