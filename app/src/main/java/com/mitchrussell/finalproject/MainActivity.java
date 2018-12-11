package com.mitchrussell.finalproject;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private Button btnMetric;
    private Button btnImperial;
    private Button btnNotification;
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

        btnNotification = this.findViewById(R.id.btnNotification);
        btnNotification.setOnClickListener((view) -> {
            Intent notificationIntent = new Intent(this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            int flag = PendingIntent.FLAG_UPDATE_CURRENT;
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, flag);
            int icon = R.drawable.ic_launcher_foreground;
            CharSequence tickerText = "NOTIFICATION";
            CharSequence contentTitle = getText(R.string.app_name);
            CharSequence contentText = "YOU'VE BEEN NOTIFIED";

            Notification notification;

            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                String CHANNEL_ID = "my_channel_01";
                CharSequence name = "my_channel";
                String Description = "Channel for the notification";
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                channel.setDescription(Description);
                channel.enableLights(true);
                channel.setLightColor(Color.RED);
                channel.enableVibration(true);
                channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                channel.setShowBadge(false);
                manager.createNotificationChannel(channel);

                notification = new Notification.Builder(this, CHANNEL_ID)
                        .setSmallIcon(icon)
                        .setTicker(tickerText)
                        .setContentTitle(contentTitle)
                        .setContentText(contentText)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .build();
            }
            else {
                notification = new Notification.Builder(this)
                                .setSmallIcon(icon)
                                .setTicker(tickerText)
                                .setContentTitle(contentTitle)
                                .setContentText(contentText)
                                .setContentIntent(pendingIntent)
                                .setAutoCancel(true)
                                .build();
            }

            final int NOTIFICATION_ID = 1;
            manager.notify(NOTIFICATION_ID, notification);

        });

        db = new ConversionDB(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent newIntent;
        switch (item.getItemId()) {
            case R.id.action_imperial_history:
                newIntent = new Intent(getApplicationContext(), LoadActivity.class);
                newIntent.putExtra("isMetric", false);
                startActivity(newIntent);
                return true;
            case R.id.action_metric_history:
                newIntent = new Intent(getApplicationContext(), LoadActivity.class);
                newIntent.putExtra("isMetric", true);
                startActivity(newIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new MetricFragment(), "METRIC");
        adapter.addFragment(new ImperialFragment(), "IMPERIAL");
        viewPager.setAdapter(adapter);
    }

}
