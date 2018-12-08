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

public class ImperialFragment extends Fragment {
    private static final String TAG = "ImperialFragment";

    private Button btnImperial;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_imperial, container, false);
        btnImperial = (Button) view.findViewById(R.id.imperialButton);

        btnImperial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ImperialFragment.this.getActivity(), "Testing Imperial Button Click", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


}
