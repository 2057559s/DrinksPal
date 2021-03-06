package project.hci.hciproject.views.drink_type_bars;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import project.hci.hciproject.views.drink_type_bars.adapter.DrinkTypeBarsAdapter;
import project.hci.hciproject.R;
import project.hci.hciproject.realm.DrinkType;
import project.hci.hciproject.util.GyroSensorLogic;
import project.hci.hciproject.views.bar.BarActivity;
import project.hci.hciproject.views.drink_results.ListDrinkResultsActivity;
import project.hci.hciproject.views.main_activity.MainActivity;

public class DrinkTypeBarsActivity extends AppCompatActivity {

    public static String DRINK_TYPE = "type";

    @BindView(R.id.rvItems)
    protected RecyclerView rvDrinkTypeBars;

    private ArrayList<DrinkType> items;
    private DrinkTypeBarsAdapter adapter;

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
        setContentView(R.layout.activity_drink_type_bars);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences(MainActivity.PREF_NAME,
                Context.MODE_PRIVATE);

        items = new ArrayList<>();
        realm = Realm.getDefaultInstance();


        RealmResults<DrinkType> results = realm.where(DrinkType.class).findAll();

        for (DrinkType drink : results) {
            items.add(drink);
        }

        adapter = new DrinkTypeBarsAdapter(this, items);

        rvDrinkTypeBars.setAdapter(adapter);
        rvDrinkTypeBars.setLayoutManager(new LinearLayoutManager(this));

        adapterPos = (int) Math.floor(items.size() / 2);
        DrinkTypeBarsAdapter.selectedPos = adapterPos;
        rvDrinkTypeBars.getLayoutManager().scrollToPosition(adapterPos);
        adapter.notifyItemChanged(adapterPos);

        gyroscopeListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (timestamp != 0) {

                    GyroSensorLogic.listenerLogic(sensorEvent,
                            timestamp,
                            deltaRotationVector);

                    if (deltaRotationVector[0] > 0.3) {
                        // up
                        int oldPos = adapterPos;
                        adapterPos -= 1;
                        if (adapterPos < 0) {
                            adapterPos = items.size()-1;
                        }
                        DrinkTypeBarsAdapter.selectedPos = adapterPos;
                        rvDrinkTypeBars.getLayoutManager().scrollToPosition(adapterPos);
                        adapter.notifyItemChanged(oldPos);
                        adapter.notifyItemChanged(adapterPos);
                    } else if (deltaRotationVector[0] < -0.3) {
                        // down
                        int oldPos = adapterPos;
                        adapterPos += 1;
                        if (adapterPos == items.size()) {
                            adapterPos = 0;
                        }
                        DrinkTypeBarsAdapter.selectedPos = adapterPos;
                        rvDrinkTypeBars.getLayoutManager().scrollToPosition(adapterPos);
                        adapter.notifyItemChanged(oldPos);
                        adapter.notifyItemChanged(adapterPos);
                    } else if (deltaRotationVector[1] > 0.3) {
                        Log.d("Movement", "RIGHT");
                        DrinkTypeBarsActivity.this.startActivity(
                                new Intent(DrinkTypeBarsActivity.this, BarActivity.class));
                    } else if (deltaRotationVector[1] < -0.3) {
                        // left
                        sharedPreferences.edit()
                                .putString(DRINK_TYPE, items.get(adapterPos).getDrinkType())
                                .apply();
                        DrinkTypeBarsActivity.this.startActivity(
                                new Intent(DrinkTypeBarsActivity.this,
                                        ListDrinkResultsActivity.class));
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
