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

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import project.hci.hciproject.realm.Bar;
import project.hci.hciproject.realm.Drink;
import project.hci.hciproject.realm.DrinkType;
import project.hci.hciproject.util.GyroSensorLogic;


public class ListDrinkResultsActivity extends AppCompatActivity {

    @BindView(R.id.listDrinksRV)
    protected RecyclerView listDrinksRV;

    private ArrayList<Drink> items;
    private ListDrinksAdapter adapter;

    private Realm realm;

    private SensorManager sensorManager;
    private Sensor sensor;
    private long timestamp;
    private final float[] deltaRotationVector = new float[4];

    private SensorEventListener gyroscopeListener;

    private int adapterPos;

    private SharedPreferences sharedPreferences;

    private RealmResults<Drink> drinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_drinks_add);

        ButterKnife.bind(this);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        sharedPreferences = getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE);

        realm = Realm.getDefaultInstance();
        items = new ArrayList<>();

        RealmResults<Bar> bar = realm.where(Bar.class)
                .equalTo("bar_name", sharedPreferences.getString(BarActivity.BAR_NAME, null))
                .findAll();

        RealmResults<DrinkType> drinkType = realm.where(DrinkType.class)
                .equalTo("type",
                        sharedPreferences.getString(DrinkTypeBarsActivity.DRINK_TYPE, null))
                .findAll();

//        drinks = realm.where(Drink.class)
//                .equalTo("bar.bar_name", bar.get(0).getBar_name()).findAll();
        drinks = realm.where(Drink.class).equalTo("type.type", drinkType.get(0).getDrinkType()).findAll();

        for (Drink drink : drinks) {
            items.add(drink);
        }

        adapter = new ListDrinksAdapter(this, items);

        listDrinksRV.setAdapter(adapter);
        // Set layout manager to position the items
        listDrinksRV.setLayoutManager(new LinearLayoutManager(this));

        adapterPos = (int) Math.floor(items.size() / 2);
        PriceRangeAdapter.selectedPos = adapterPos;
        listDrinksRV.getLayoutManager().scrollToPosition(adapterPos);
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
                        listDrinksRV.getLayoutManager().scrollToPosition(adapterPos);
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
                        listDrinksRV.getLayoutManager().scrollToPosition(adapterPos);
                        adapter.notifyItemChanged(oldPos);
                        adapter.notifyItemChanged(adapterPos);
                    } else if (deltaRotationVector[1] > 0.3) {
                        // right
                        ListDrinkResultsActivity.this.startActivity(
                                new Intent(ListDrinkResultsActivity.this, DrinkTypeBarsActivity.class));
                    } else if (deltaRotationVector[1] < -0.3) {
                        // left
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
    protected void onPause() {
        super.onPause();
        if (sensor != null) {
            sensorManager.unregisterListener(gyroscopeListener);
        }
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
    protected void onStop() {
        super.onStop();
        realm.close();
    }
}
