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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SensorManager mSensorManager;
    private List<Sensor> sensorList;
    private SensorListener sensorListener;
    private Controller controller;
    private FragmentManager fm;
    private List<String> sensorNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = new Controller(this);
        initializeComponents();

        mSensorManager = (SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
        sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        setSensorNameList(sensorList);

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

    private void setSensorNameList(List<Sensor> sensorList){
        sensorNames = new ArrayList<>();
        for(int i = 0; i < sensorList.size(); i++){
            sensorNames.add(sensorList.get(i).getName());
        }
    }

    public List<String> getAvailableSensorNames(){
        return sensorNames;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(sensorListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(sensorListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSensorManager = null;
        mSensor = null;
    }


}
