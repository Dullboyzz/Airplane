package com.example.airplanemode;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private AirplaneModeReceiver airplaneModeReceiver;
    private TextView tvStatus;
    private ImageView imgAirplane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Airplane Mode Detector");
        }
        toolbar.setTitleTextColor(Color.WHITE);


        tvStatus = findViewById(R.id.tvStatus);
        imgAirplane = findViewById(R.id.imgAirplane);

        // Initialize Receiver
        airplaneModeReceiver = new AirplaneModeReceiver(tvStatus, imgAirplane);

        // Register Receiver for Airplane Mode Change
        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(airplaneModeReceiver, filter);

        // Check initial state of Airplane Mode
        boolean isAirplaneModeOn = Settings.Global.getInt(
                getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
        updateUI(isAirplaneModeOn);
    }

    private void updateUI(boolean isAirplaneModeOn) {
        if (isAirplaneModeOn) {
            tvStatus.setText("Airplane Mode is ON");
            imgAirplane.setImageResource(R.drawable.ic_airplane_on);
        } else {
            tvStatus.setText("Airplane Mode is OFF");
            imgAirplane.setImageResource(R.drawable.ic_airplane_off);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(airplaneModeReceiver);
    }
}
