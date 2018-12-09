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

public class ImperialFragment extends Fragment {
    private static final String TAG = "ImperialFragment";

    private Button btnImperial;
    private EditText imperialVolume;
    private EditText imperialTemperature;
    private EditText imperialOutput;
    private double fluidOunceToMillilitre = 28.413125;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_imperial, container, false);
        btnImperial = (Button) view.findViewById(R.id.imperialCalculateButton);
        imperialVolume = view.findViewById(R.id.imperialVolumeEditText);
        imperialTemperature = view.findViewById(R.id.imperialTemperatureEditText);
        imperialOutput = view.findViewById(R.id.imperialResult);

        btnImperial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            try{
                int imperialVolumeInteger = Integer.parseInt(imperialVolume.getText().toString());
                int imperialTemperatureInteger = Integer.parseInt(imperialTemperature.getText().toString());

                double metricVolumeStopGap = imperialVolumeInteger * fluidOunceToMillilitre;

                double metricTemperatureStopGap = ((imperialTemperatureInteger-32)*(5/9));

                int imperialResult = (int) Math.round(metricTemperatureStopGap * metricVolumeStopGap);



                Toast.makeText(ImperialFragment.this.getActivity(), "This much energy in kcals: "
                        + imperialResult, Toast.LENGTH_LONG).show();
                imperialOutput.setText(imperialResult);


            }
            catch(Exception e){
                Toast.makeText(ImperialFragment.this.getActivity(), "A number is invalid.", Toast.LENGTH_LONG).show();
            }

            }
        });
        return view;
    }


}
