package com.example.farhaan.sleeptrack;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Farhaan on 26-10-2016.
 */
public class TimeService extends Service {

    BroadcastReceiver broadcastReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        broadcastReceiver = new TimeReceiver();
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean screenOn = intent.getBooleanExtra("screen_state", false);
        if (!screenOn) {
            TimeElement timeElement = new TimeElement();
            Calendar c = Calendar.getInstance();
            Long seconds = System.currentTimeMillis()/1000;
            timeElement.setID(1);
            timeElement.setDate(c.get(Calendar.DATE));
            timeElement.setMonth(c.get(Calendar.MONTH));
            timeElement.setYear(c.get(Calendar.YEAR));
            timeElement.setHour(c.get(Calendar.HOUR));
            timeElement.setMinute(c.get(Calendar.MINUTE));
            timeElement.setSeconds(String.valueOf(seconds));
            timeElement.setState("sleep");
            DatabaseHandler newdb = new DatabaseHandler(this);
            newdb.addTime(timeElement);
        } else {
            TimeElement timeElement = new TimeElement();
            Calendar c = Calendar.getInstance();
            Long seconds = System.currentTimeMillis()/1000;
            timeElement.setID(1);
            timeElement.setDate(c.get(Calendar.DATE));
            timeElement.setMonth(c.get(Calendar.MONTH));
            timeElement.setYear(c.get(Calendar.YEAR));
            timeElement.setHour(c.get(Calendar.HOUR));
            timeElement.setMinute(c.get(Calendar.MINUTE));
            timeElement.setSeconds(String.valueOf(seconds));
            timeElement.setState("wake");
            DatabaseHandler newdb = new DatabaseHandler(this);
            newdb.addTime(timeElement);
        }
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent){
        Intent restartServiceIntent = new Intent(getApplicationContext(), this.getClass());
        restartServiceIntent.setPackage(getPackageName());

        PendingIntent restartServicePendingIntent = PendingIntent.getService(getApplicationContext(), 1, restartServiceIntent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmService = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmService.set(
                AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + 1000,
                restartServicePendingIntent);

        super.onTaskRemoved(rootIntent);
    }
}
