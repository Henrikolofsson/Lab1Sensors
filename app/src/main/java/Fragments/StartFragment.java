package Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import se.mau.ai0026.lab1sensors.Controller;
import se.mau.ai0026.lab1sensors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment {
    private Controller controller;
    private TextView tvTimestamp;
    private TextView tvAccuracy;
    private TextView tvValues;
    private Button btnUnregister;


    public StartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        initializeComponents(view);
        registerListener();
        return view;
    }

    private void initializeComponents(View view){
        tvTimestamp = (TextView) view.findViewById(R.id.tvTimestamp);
        tvAccuracy = (TextView) view.findViewById(R.id.tvAccuracy);
        tvValues = (TextView) view.findViewById(R.id.tvValues);
        btnUnregister = (Button) view.findViewById(R.id.btnUnregister);
    }

    private void registerListener(){
        btnUnregister.setOnClickListener(new UnregisterListener());
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    public void setTexts(long timestamp, int accuracy, float[] values){
        if(tvTimestamp != null && tvAccuracy != null && tvValues != null) {
            tvTimestamp.setText("Timestamp from accelerometer: " + Long.toString(timestamp));
            tvAccuracy.setText("Accuracy from accelerometer: " + Integer.toString(accuracy));
            tvValues.setText("Values from accelerometer: " + Float.toString(values[0]) + ", " + Float.toString(values[1]) + ", " + Float.toString(values[2]));
        }
    }

    private class UnregisterListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            controller.unregister();
        }
    }

}
