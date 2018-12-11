package com.mitchrussell.finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MetricFragment extends Fragment {
    private static final String TAG = "MetricFragment";
    private static final boolean IS_METRIC = true;

    private Button btnMetric;
    private EditText metricVolume;
    private EditText metricTemperature;
    private TextView metricOutput;
    private ConversionDB db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_metric, container, false);
        btnMetric = (Button) view.findViewById(R.id.metricCalculateButton);
        metricVolume = view.findViewById(R.id.metricVolumeEditText);
        metricTemperature = view.findViewById(R.id.metricTemperatureEditText);
        metricOutput = (TextView) view.findViewById(R.id.metricResult);

        db = new ConversionDB(getContext());

        btnMetric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            try{
                int metricVolumeInteger = Integer.parseInt(metricVolume.getText().toString());
                int metricTemperatureInteger = Integer.parseInt(metricTemperature.getText().toString());

                int metricResult = metricTemperatureInteger * metricVolumeInteger;

                metricOutput.setText("Result: " + Integer.toString(metricResult) + " calories.");

                try {
                    db.insertConversion(metricVolumeInteger, metricTemperatureInteger, metricResult, IS_METRIC);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
            catch(Exception e){
                Toast.makeText(MetricFragment.this.getActivity(), "A number is invalid.", Toast.LENGTH_LONG).show();
            }
            }
        });
        return view;
    }


}
