package project.hci.hciproject;

<<<<<<< HEAD
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
=======
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import project.hci.hciproject.util.GyroSensorEventListener;
>>>>>>> 3f8bea4edc3cdbb712252d7769f44a4cff041ae3

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor sensor;

    private SensorEventListener gyroscopeListener = new GyroSensorEventListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
