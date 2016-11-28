package project.hci.hciproject;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
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
import project.hci.hciproject.util.GyroSensorLogic;


public class list_drinks_add extends ListActivity {

    private ArrayList<Drink> items;
    private RecyclerView rvContacts;
    private static BarAdapter adapter;

    private Realm realm;

    private SensorManager sensorManager;
    private Sensor sensor;
    private long timestamp;
    private final float[] deltaRotationVector = new float[4];

    private SensorEventListener gyroscopeListener;

    private int adapterPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_drinks_add);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        realm = Realm.getDefaultInstance();
        items = new ArrayList<>();
        // ...
        // Lookup the recyclerview in activity layout
        rvContacts = (RecyclerView) findViewById(R.id.rvItems);

        // Initialize contacts
        RealmResults<Drink> results = realm.where(Drink.class)
                                      .equalTo("bar.bar_name", "Curlers Rest")
                                      .equalTo("type.type", "Beer")
          /*to do */                  .findAll();

        for (Drink drink : results) {
            items.add(drink);
        }

    }




}
