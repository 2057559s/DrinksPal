package project.hci.hciproject;

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
import android.view.WindowManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import project.hci.hciproject.realm.Bar;
import project.hci.hciproject.realm.Drink;
import project.hci.hciproject.realm.DrinkType;
import project.hci.hciproject.util.GyroSensorLogic;

public class BarResultsActivity extends AppCompatActivity {

    @BindView(R.id.barResultsRV)
    protected RecyclerView barResultsRV;

    private ArrayList<Bar> items;
    private BarResultsAdapter adapter;

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
        setContentView(R.layout.activity_bar_results);

        ButterKnife.bind(this);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        sharedPreferences = getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE);

        realm = Realm.getDefaultInstance();
        items = new ArrayList<>();

        RealmResults<DrinkType> type = realm.where(DrinkType.class)
                .equalTo("type", sharedPreferences.getString(DrinkTypeActivity.TYPE, null))
                .findAll();

        RealmResults<Drink> drinks = realm.where(Drink.class)
                .lessThanOrEqualTo("price",
                        (double) sharedPreferences.getFloat(PriceRangeActivity.PRICE, 0))
                .findAll();

        drinks = drinks.where().equalTo("type.type", type.get(0).getDrinkType()).findAll();

        for (Drink drink : drinks) {
            items.add(drink.getBar());
        }

        adapter = new BarResultsAdapter(this, items);
        // Attach the adapter to the recyclerview to populate items
        barResultsRV.setAdapter(adapter);
        // Set layout manager to position the items
        barResultsRV.setLayoutManager(new LinearLayoutManager(this));

        adapterPos = (int) Math.floor(items.size() / 2);
        PriceRangeAdapter.selectedPos = adapterPos;
        barResultsRV.getLayoutManager().scrollToPosition(adapterPos);
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
                        PriceRangeAdapter.selectedPos = adapterPos;
                        barResultsRV.getLayoutManager().scrollToPosition(adapterPos);
                        adapter.notifyItemChanged(oldPos);
                        adapter.notifyItemChanged(adapterPos);
                    } else if (deltaRotationVector[0] < -0.4) {
                        // down
                        int oldPos = adapterPos;
                        adapterPos += 1;
                        if (adapterPos == items.size()) {
                            adapterPos = 0;
                        }
                        PriceRangeAdapter.selectedPos = adapterPos;
                        barResultsRV.getLayoutManager().scrollToPosition(adapterPos);
                        adapter.notifyItemChanged(oldPos);
                        adapter.notifyItemChanged(adapterPos);
                    } else if (deltaRotationVector[1] > 0.3) {
                        // right
                        // launch next screen
                    } else if (deltaRotationVector[1] < -0.3) {
                        // left
                        BarResultsActivity.this.startActivity(
                                new Intent(BarResultsActivity.this, PriceRangeActivity.class));
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
    protected void onStop() {
        super.onStop();
        realm.close();
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
}
