package Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import se.mau.ai0026.lab1sensors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SensorFragment extends Fragment {
    private TextView tvSensorName;
    private ImageView ivSensor;

    public SensorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sensor, container, false);
        initializeComponents(view);
        return view;
    }

    private void initializeComponents(View view){
        tvSensorName = (TextView) view.findViewById(R.id.tvSensorName);
        ivSensor = (ImageView) view.findViewById(R.id.ivSensor);
    }

    public String getSensorName(){
        return tvSensorName.getText().toString();
    }

    public void setTvSensorName(String name){
        tvSensorName.setText(name);
    }

}
