package com.mitchrussell.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LoadActivity extends AppCompatActivity {

    private TextView txtTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        Intent oldIntent = getIntent();
        txtTitle = this.findViewById(R.id.txtLoadScreen);

        if (oldIntent.getStringExtra("value") != "" | oldIntent.getStringExtra("value") != null) {
            txtTitle.setText(oldIntent.getStringExtra("value"));
        }
    }
}
