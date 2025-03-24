package com.example.airplanemode;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AirplaneModeReceiver extends BroadcastReceiver {
    private TextView tvStatus;
    private ImageView imgAirplane;

    public AirplaneModeReceiver(TextView tvStatus, ImageView imgAirplane) {
        this.tvStatus = tvStatus;
        this.imgAirplane = imgAirplane;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction())) {
            boolean isAirplaneModeOn = Settings.Global.getInt(
                    context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, 0) != 0;

            if (isAirplaneModeOn) {
                tvStatus.setText("Airplane Mode is ON");
                imgAirplane.setImageResource(R.drawable.ic_airplane_on);
                Toast.makeText(context, "Airplane Mode is ON", Toast.LENGTH_SHORT).show();
            } else {
                tvStatus.setText("Airplane Mode is OFF");
                imgAirplane.setImageResource(R.drawable.ic_airplane_off);
                Toast.makeText(context, "Airplane Mode is OFF", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
