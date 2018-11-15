package se.mau.ai0026.lab1sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SensorManager mSensorManager;
    private Sensor sensor;
    private SensorListener sensorListener;
    private Controller controller;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
        controller = new Controller(this);
        sensorListener.setController(controller);

        mSensorManager = (SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
        if(mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!=null){
            sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
    }

    private void initializeComponents(){
        fm = getSupportFragmentManager();
        sensorListener = new SensorListener();
    }

    public void setFragment(Fragment fragment, String tag){
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_container, fragment, tag);
        ft.commit();
    }

    public void addFragment(Fragment fragment, String tag){
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(fragment, tag);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if(controller.onBackPressed()){
            super.onBackPressed();
        }
    }

    public Fragment getFragment(String tag){
        return fm.findFragmentByTag(tag);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(sensorListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        Toast.makeText(this, "Registered listener for accelerometer", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(sensorListener);
        Toast.makeText(this, "Un-registered listener for accelerometer", Toast.LENGTH_LONG).show();
        unregister();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSensorManager = null;
        sensor = null;
    }

    public void unregister(){
        mSensorManager.unregisterListener(sensorListener);
        Toast.makeText(this, "Un-registered listener for accelerometer", Toast.LENGTH_LONG).show();
    }
}
