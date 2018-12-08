package com.mitchrussell.finalproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MetricFragment extends Fragment {
    private static final String TAG = "MetricFragment";

    private Button btnMetric;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_metric, container, false);
        btnMetric = (Button) view.findViewById(R.id.metricButton);

        btnMetric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MetricFragment.this.getActivity(), "Testing Metric Button Click", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


}
