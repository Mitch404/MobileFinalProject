package com.mitchrussell.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class LoadActivity extends AppCompatActivity {

    private TextView txtTitle, txtVolume, txtTempChange;
    private ConversionDB db;
    private SimpleAdapter adapter;
    private ListView conversionListView;
    private boolean isMetric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        Intent oldIntent = getIntent();
        txtTitle = this.findViewById(R.id.txtLoadScreen);
        txtVolume = (TextView) this.findViewById(R.id.txtVolumeHeader);
        txtTempChange = (TextView) this.findViewById(R.id.txtTempHeader);

        conversionListView = findViewById(R.id.listCalc);

        isMetric = oldIntent.getBooleanExtra("isMetric", true);


        if (isMetric) {
            txtTitle.setText("Metric History");
            txtVolume.setText("Volume (mL)");
            txtTempChange.setText("Temperature Change (C)");
        }
        else {
            txtTitle.setText("Imperial History");
            txtVolume.setText("Volume (fl oz)");
            txtTempChange.setText("Temperature Change (F)");
        }

        db = new ConversionDB(this);
        updateDisplay();
    }

    private void updateDisplay() {
        ArrayList<HashMap<String, String>> data;

        if (isMetric) {
            data = db.getMetricConversions();
        }
        else {
            data = db.getImperialConversions();
        }

        int resource = R.layout.conversion_listview_item;
        String[] from = {"volume", "tempDelta", "calories" };
        int[] to = {R.id.txtVolume, R.id.txtTempChange, R.id.txtCalories};

        adapter = new SimpleAdapter(this, data, resource, from, to);
        conversionListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_load, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent newIntent;
        switch (item.getItemId()) {
            case R.id.action_calc:
                newIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(newIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
