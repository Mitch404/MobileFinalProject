package com.mitchrussell.finalproject;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private Button btnMetric;
    private Button btnImperial;
    public ConversionDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: Starting.");

        int orientation = getResources().getConfiguration().orientation;
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int widthPixels = metrics.widthPixels;
        float scaleFactor = metrics.density;

        float widthDp = widthPixels / scaleFactor;

        if (orientation == Configuration.ORIENTATION_PORTRAIT & widthDp < 600) {
            // Set up the ViewPager with the sections adapter
            mViewPager = (ViewPager) findViewById(R.id.container);
            setupViewPager(mViewPager);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(mViewPager);
            mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        }


        btnMetric = this.findViewById(R.id.btnLoadMetric);
        btnMetric.setOnClickListener((view) -> {
            Intent newIntent = new Intent(getApplicationContext(), LoadActivity.class);
            newIntent.putExtra("isMetric", true);
            startActivity(newIntent);
        });

        btnImperial = this.findViewById(R.id.btnLoadImperial);
        btnImperial.setOnClickListener((view) -> {
            Intent newIntent = new Intent(getApplicationContext(), LoadActivity.class);
            newIntent.putExtra("isMetric", false);
            startActivity(newIntent);
        });

        db = new ConversionDB(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new MetricFragment(), "METRIC");
        adapter.addFragment(new ImperialFragment(), "IMPERIAL");
        viewPager.setAdapter(adapter);
    }

}
