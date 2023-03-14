package com.example.accelerometer;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager != null){

            Sensor acceleratorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            if (acceleratorSensor != null){
                sensorManager.registerListener(this, acceleratorSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }

        } else {
            Toast.makeText(this, "Sensor service not detected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        double xValue = event.values[0];
        double yValue = event.values[1];
        double zValue = event.values[2];

        int decimalPlaces = 7;

        double roundedNumberX = Math.round(xValue * Math.pow(10, decimalPlaces)) / Math.pow(10,decimalPlaces);
        double roundedNumberY = Math.round(yValue * Math.pow(10, decimalPlaces)) / Math.pow(10,decimalPlaces);
        double roundedNumberZ = Math.round(zValue * Math.pow(10, decimalPlaces)) / Math.pow(10,decimalPlaces);

        if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
        ((TextView)findViewById(R.id.txtValues)).setText(String.format("X: %1$8.6f\nY: %2$8.6f\nZ: %3$8.6f", roundedNumberX, roundedNumberY, roundedNumberZ));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}