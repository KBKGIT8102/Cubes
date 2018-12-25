package com.example.a208_u_01.cubes;

import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private ImageView imageView1;
    private ImageView imageView2;
    private SensorManager sensorManager;
    private Sensor sensorGravity;
    final Random random = new Random();
    private float acceleration;
    private float currentAcceleration;
    private float lastAcceleration;
    private static final int ACCELERATION_THRESHOLD = 100000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView1=findViewById(R.id.image1);
        imageView2=findViewById(R.id.image2);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorGravity=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(listenerLight,sensorGravity,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listenerLight,sensorGravity);
    }
    SensorEventListener listenerLight = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
           int numbers[]={R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five,R.drawable.six};
           int pos= random.nextInt(numbers.length);
           int pos1= random.nextInt(numbers.length);
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // save previous acceleration value
            lastAcceleration = currentAcceleration;

            // calculate the current acceleration
            currentAcceleration = x * x + y * y + z * z;

            // calculate the change in acceleration
            acceleration = currentAcceleration *
                    (currentAcceleration - lastAcceleration);

            // if the acceleration is above a certain threshold
            if (acceleration > ACCELERATION_THRESHOLD) {
                imageView1.setImageResource(numbers[pos]);
                imageView2.setImageResource(numbers[pos1]);
            }
        }
    };
}
