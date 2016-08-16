package com.ojiofong.androidsamples.ui;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ojiofong.androidsamples.R;

import java.util.List;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = SensorActivity.class.getSimpleName();
    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        setTitle(TAG);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        getListOfSensors();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerSensor();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unRegisterSensor();
    }

    private void registerSensor() {
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void unRegisterSensor() {
        sensorManager.unregisterListener(this);
    }

    boolean hasSetAccelerometer = false;
    boolean hasSetMagnetometer = false;
    float[] lastAccelerometerValues = new float[3];
    float[] lastMagnetometerValues = new float[3];

    @Override
    public void onSensorChanged(SensorEvent event) {


//        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
//            lastAccelerometerValues = Arrays.copyOf(event.values, event.values.length);
//            hasSetAccelerometer = true;
//            //useAccelerometer(event);
//        }else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
//            lastMagnetometerValues = Arrays.copyOf(event.values, event.values.length);
//            hasSetMagnetometer = true;
//        }
//
//        if (hasSetAccelerometer && hasSetMagnetometer){
//            SensorManager.getRotationMatrix(null, null, lastAccelerometerValues, lastMagnetometerValues);
//            SensorManager.getOrientation(null, null);
//        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void getListOfSensors(){
        List<Sensor> list = sensorManager.getSensorList(Sensor.TYPE_ALL);
        ((TextView) findViewById(R.id.tvHelloWorld)).setText(list.toString());
        //Log.d(TAG, list.toString());
    }

    private void useAccelerometer(SensorEvent event) {
        float[] values = event.values;
        // Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];

        String s = "x->" + x + " y->" + y + " z->" + z;

        //((TextView) findViewById(R.id.tvHelloWorld)).setText(s);
    }


}
