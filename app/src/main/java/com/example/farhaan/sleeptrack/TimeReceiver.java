package com.example.farhaan.sleeptrack;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Farhaan on 26-10-2016.
 */
public class TimeReceiver extends BroadcastReceiver {

    public static boolean isScreenOn = true;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            isScreenOn = false;
            System.out.println("Screen off inside receiver");
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            isScreenOn = true;
            System.out.println("Screen on inside receiver");
        }

        Intent intent1 = new Intent(context, TimeService.class);
        intent1.putExtra("screen_state", isScreenOn);
        context.startService(intent1);
    }
}
