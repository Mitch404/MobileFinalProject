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
import android.widget.TextView;
import android.widget.Toast;

public class ImperialFragment extends Fragment {
    private static final String TAG = "ImperialFragment";
    private static final boolean IS_METRIC = false;

    private Button btnImperial;
    private EditText imperialVolume;
    private EditText imperialTemperature;
    private TextView imperialOutput;
    private double fluidOunceToMillilitre = 28.413125;
    private ConversionDB db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_imperial, container, false);
        btnImperial = (Button) view.findViewById(R.id.imperialCalculateButton);
        imperialVolume = view.findViewById(R.id.imperialVolumeEditText);
        imperialTemperature = view.findViewById(R.id.imperialTemperatureEditText);
        imperialOutput = (TextView) view.findViewById(R.id.imperialResult);

        db = new ConversionDB(getContext());

        btnImperial.setOnClickListener(view1 -> {
            try{
                int imperialVolumeInteger = Integer.parseInt(imperialVolume.getText().toString());
                int imperialTemperatureInteger = Integer.parseInt(imperialTemperature.getText().toString());

                double metricVolumeStopGap = imperialVolumeInteger * fluidOunceToMillilitre;
                double metricTemperatureStopGap = (((double) imperialTemperatureInteger)*(5.0/9.0));
                int imperialResult = (int) Math.round(metricTemperatureStopGap * metricVolumeStopGap);

                imperialOutput.setText("Result: " + Integer.toString(imperialResult) + " calories.");

                try {
                    db.insertConversion(imperialVolumeInteger, imperialTemperatureInteger, imperialResult, IS_METRIC);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
            catch(Exception e){
                Toast.makeText(ImperialFragment.this.getActivity(), "A number is invalid.", Toast.LENGTH_LONG).show();
            }

        });
        return view;
    }


}
