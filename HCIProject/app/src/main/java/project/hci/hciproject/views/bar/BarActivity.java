package project.hci.hciproject.views.bar;

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
import project.hci.hciproject.views.bar.adapter.BarAdapter;
import project.hci.hciproject.views.drink_type_bars.DrinkTypeBarsActivity;
import project.hci.hciproject.R;
import project.hci.hciproject.realm.Bar;
import project.hci.hciproject.util.GyroSensorLogic;
import project.hci.hciproject.views.main_activity.MainActivity;


public class BarActivity extends AppCompatActivity {

    public static String BAR_NAME = "bar_name";

    @BindView(R.id.rvItems)
    protected RecyclerView rvBars;


    private ArrayList<Bar> items;
    private BarAdapter adapter;
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
        setContentView(R.layout.activity_bar);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences(MainActivity.PREF_NAME,
                Context.MODE_PRIVATE);

        realm = Realm.getDefaultInstance();
        items = new ArrayList<>();

        // Initialize contacts
        RealmResults<Bar> results = realm.where(Bar.class).findAll();

        for (Bar bar : results) {
            items.add(bar);
        }

        adapter = new BarAdapter(this, items);
        rvBars.setAdapter(adapter);
        rvBars.setLayoutManager(new LinearLayoutManager(this));

        adapterPos = (int) Math.floor(items.size() / 2);
        BarAdapter.selectedPos = adapterPos;
        rvBars.getLayoutManager().scrollToPosition(adapterPos);
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
                        BarAdapter.selectedPos = adapterPos;
                        rvBars.getLayoutManager().scrollToPosition(adapterPos);
                        adapter.notifyItemChanged(oldPos);
                        adapter.notifyItemChanged(adapterPos);
                    } else if (deltaRotationVector[0] < -0.3) {
                        // down
                        int oldPos = adapterPos;
                        adapterPos += 1;
                        if (adapterPos == items.size()) {
                            adapterPos = 0;
                        }
                        BarAdapter.selectedPos = adapterPos;
                        rvBars.getLayoutManager().scrollToPosition(adapterPos);
                        adapter.notifyItemChanged(oldPos);
                        adapter.notifyItemChanged(adapterPos);
                    } else if (deltaRotationVector[1] > 0.3) {
                        // right

                        BarActivity.this.startActivity(
                                new Intent(BarActivity.this, MainActivity.class));
                    } else if (deltaRotationVector[1] < -0.3) {
                        sharedPreferences.edit()
                                .putString(BAR_NAME, items.get(adapterPos).getBar_name()).apply();
                        BarActivity.this.startActivity(
                                new Intent(BarActivity.this, DrinkTypeBarsActivity.class));
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

