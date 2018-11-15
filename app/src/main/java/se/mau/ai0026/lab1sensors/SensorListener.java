package se.mau.ai0026.lab1sensors;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class SensorListener extends Service implements SensorEventListener {
    private Controller controller;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        long timestamp = event.timestamp;
        int accuracy = event.accuracy;
        float[] values = event.values;
        controller.displayValues(timestamp, accuracy, values);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void setController(Controller controller){
        this.controller = controller;
    }
}
