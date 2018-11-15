package Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import se.mau.ai0026.lab1sensors.Controller;
import se.mau.ai0026.lab1sensors.R;

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.Holder> {
    private LayoutInflater inflater;
    private List<String> content;
    private Controller controller;

    public SensorAdapter(Context context){
        this(context, new ArrayList<String>());
    }

    public SensorAdapter(Context context, List<String> content){
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.content = content;
    }

    public void setContent(List<String> content){
        this.content = content;
        super.notifyDataSetChanged();
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int pos) {
        View view = inflater.inflate(R.layout.fragment_sensor, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int pos) {
        holder.tvSensorName.setText(content.get(pos));

        switch(content.get(0)){

        }

    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvSensorName;
        private ImageView ivSensor;

        public Holder(View itemView){
            super(itemView);
            tvSensorName = itemView.findViewById(R.id.tvSensorName);
            ivSensor = itemView.findViewById(R.id.ivSensor);
            ivSensor.getLayoutParams().height = 80;
            ivSensor.getLayoutParams().width = 80;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
