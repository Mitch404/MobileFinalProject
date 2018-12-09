package com.mitchrussell.finalproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MetricFragment extends Fragment {
    private static final String TAG = "MetricFragment";

    private Button btnMetric;
    private EditText metricVolume;
    private EditText metricTemperature;
    private EditText metricOutput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_metric, container, false);
        btnMetric = (Button) view.findViewById(R.id.metricCalculateButton);
        metricVolume = view.findViewById(R.id.metricVolumeEditText);
        metricTemperature = view.findViewById(R.id.metricTemperatureEditText);
        metricOutput = view.findViewById(R.id.metricResult);


        btnMetric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            try{
                int metricVolumeInteger = Integer.parseInt(metricVolume.getText().toString());
                int metricTemperatureInteger = Integer.parseInt(metricTemperature.getText().toString());

                int metricResult = metricTemperatureInteger * metricVolumeInteger;

                Toast.makeText(MetricFragment.this.getActivity(), "This much energy in kcals: " + metricResult, Toast.LENGTH_LONG).show();
                metricOutput.setText(metricResult);


            }
            catch(Exception e){
                Toast.makeText(MetricFragment.this.getActivity(), "A number is invalid.", Toast.LENGTH_LONG).show();
            }
            }
        });
        return view;
    }


}
