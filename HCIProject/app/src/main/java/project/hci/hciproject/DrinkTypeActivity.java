package project.hci.hciproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import project.hci.hciproject.realm.DrinkType;
import project.hci.hciproject.util.GyroSensorLogic;

public class DrinkTypeActivity extends AppCompatActivity {

    public static String TYPE = "type";

    private ArrayList<DrinkType> items;
    private RecyclerView rvContacts;
    private DrinkTypeAdapter adapter;

    private Realm realm;

    private SensorManager sensorManager;
    private Sensor sensor;
    private long timestamp;
    private final float[] deltaRotationVector = new float[4];

    private SensorEventListener gyroscopeListener;

    private int adapterPos;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_type);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        sharedPreferences = getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE);

        items = new ArrayList<>();
        realm = Realm.getDefaultInstance();
        // ...
        // Lookup the recyclerview in activity layout
        rvContacts = (RecyclerView) findViewById(R.id.rvItems);

        // Initialize contacts
        RealmResults<DrinkType> results = realm.where(DrinkType.class).findAll();

        for (DrinkType drink : results) {
            items.add(drink);
        }
        //items = realm.where(Bar.class).findAll();
        // Create adapter passing in the sample user data
        adapter = new DrinkTypeAdapter(this, items);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));

        adapterPos = (int) Math.floor(items.size() / 2);
        PriceRangeAdapter.selectedPos = adapterPos;
        rvContacts.getLayoutManager().scrollToPosition(adapterPos);
        adapter.notifyItemChanged(adapterPos);

        gyroscopeListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (timestamp != 0) {

                    GyroSensorLogic.listenerLogic(sensorEvent,
                            timestamp,
                            deltaRotationVector);

                    if (deltaRotationVector[0] > 0.4) {
                        // up
                        int oldPos = adapterPos;
                        adapterPos -= 1;
                        if (adapterPos < 0) {
                            adapterPos = items.size()-1;
                        }
                        DrinkTypeAdapter.selectedPos = adapterPos;
                        rvContacts.getLayoutManager().scrollToPosition(adapterPos);
                        adapter.notifyItemChanged(oldPos);
                        adapter.notifyItemChanged(adapterPos);
                    } else if (deltaRotationVector[0] < -0.4) {
                        // down
                        int oldPos = adapterPos;
                        adapterPos += 1;
                        if (adapterPos == items.size()) {
                            adapterPos = 0;
                        }
                        DrinkTypeAdapter.selectedPos = adapterPos;
                        rvContacts.getLayoutManager().scrollToPosition(adapterPos);
                        adapter.notifyItemChanged(oldPos);
                        adapter.notifyItemChanged(adapterPos);
                    } else if (deltaRotationVector[1] > 0.3) {
                        // right
                        sharedPreferences.edit()
                                .putString(TYPE, items.get(adapterPos).getDrinkType()).apply();
                        DrinkTypeActivity.this.startActivity(
                                new Intent(DrinkTypeActivity.this, PriceRangeActivity.class));
                    } else if (deltaRotationVector[1] < -0.3) {
                        // left
                        DrinkTypeActivity.this.startActivity(
                                new Intent(DrinkTypeActivity.this, MainActivity.class));
                    }
                }
                timestamp = sensorEvent.timestamp;
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensor != null) {
            sensorManager.registerListener(gyroscopeListener, sensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensor != null) {
            sensorManager.unregisterListener(gyroscopeListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        realm.close();
    }
}
