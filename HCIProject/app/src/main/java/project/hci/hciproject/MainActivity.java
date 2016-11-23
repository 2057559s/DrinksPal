package project.hci.hciproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import project.hci.hciproject.util.GyroSensorEventListener;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor sensor;
    private long timestamp;
    private final float[] deltaRotationVector = new float[4];

    private SensorEventListener gyroscopeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gyroscopeListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (timestamp != 0) {

                    GyroSensorEventListener.listenerLogic(sensorEvent,
                            timestamp,
                            deltaRotationVector);

                    if (deltaRotationVector[0] > 0.2) {
                        Log.d("Movement", "UP");
                    } else if (deltaRotationVector[0] < -0.2) {
                        Log.d("Movement", "DOWN");
                    } else if (deltaRotationVector[1] > 0.3) {
                        Log.d("Movement", "RIGHT");
                        //context.startActivity(new Intent());
                    } else if (deltaRotationVector[1] < -0.3) {
                        Log.d("Movement", "LEFT");
                        //context.startActivity(new Intent());
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
}
