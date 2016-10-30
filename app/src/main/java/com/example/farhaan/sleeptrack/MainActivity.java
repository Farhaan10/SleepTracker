package com.example.farhaan.sleeptrack;

import android.content.Intent;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<String> listDates = new ArrayList<String>();
    List<String> listTimes = new ArrayList<String>();
    List<String> listDurations = new ArrayList<String>();
    int check = 1;
    SleepAdapter sleepAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.sleepList);

        final Intent intent1 = new Intent(MainActivity.this, TimeService.class);
        intent1.putExtra("screen_state", true);
        startService(intent1);

        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
        List<TimeElement> list = db.getAllTimes();

        for (int i=0;i<list.size()-1;) {
            System.out.println(list.get(i).getID() + " " + list.get(i).getDate() + " " + list.get(i).getMonth() + " " + list.get(i).getYear() + " " + list.get(i).getHour() + " " + list.get(i).getMinute() + " " + list.get(i).getSeconds() + " " + list.get(i).getState());
            Long duration_seconds = Long.parseLong(list.get(i+1).getSeconds()) - Long.parseLong(list.get(i).getSeconds());
            if (list.get(i).getState().equals("sleep")){
                if (duration_seconds < 10800) {
                    list.remove(i);
                } else {
                    check = 1;
                    i++;
                }
            } else {
                if (check == 0) {
                    list.remove(i);
                } else {
                    i++;
                    check = 0;
                }
            }
        }

        for (int i=0;i<list.size()-1;){
            System.out.println(list.get(i).getID() + " " + list.get(i).getDate() + " " + list.get(i).getMonth() + " " + list.get(i).getYear() + " " + list.get(i).getHour() + " " + list.get(i).getMinute() + " " + list.get(i).getSeconds() + " " + list.get(i).getState());
            if(list.get(i).getState().equals("wake")){
                Long duration_seconds = Long.parseLong(list.get(i+1).getSeconds()) - Long.parseLong(list.get(i).getSeconds());
                if (duration_seconds < 1800){
                    list.remove(i+1);
                    list.remove(i);
                } else {
                    i++;
                }
            } else {
                i++;
            }
        }

        for (int i=0;i<list.size()-1;i++) {
            System.out.println(list.get(i).getID() + " " + list.get(i).getDate() + " " + list.get(i).getMonth() + " " + list.get(i).getYear() + " " + list.get(i).getHour() + " " + list.get(i).getMinute() + " " + list.get(i).getSeconds() + " " + list.get(i).getState());
            if(list.get(i).getState().equals("sleep")){
                listDates.add(String.valueOf(list.get(i).getDate()) + "/" + String.valueOf(list.get(i).getMonth()));
                listTimes.add(String.valueOf(list.get(i).getHour()) + ":" + String.valueOf(list.get(i).getMinute()) + "-" + list.get(i+1).getHour() + ":" + String.valueOf(list.get(i+1).getMinute()));
                listDurations.add(getDurations(list.get(i), list.get(i+1)));
            }
        }

        sleepAdapter = new SleepAdapter(MainActivity.this, R.id.text_date, listDates, listTimes, listDurations);
        listView.setAdapter(sleepAdapter);
    }

    public String getDurations(TimeElement first, TimeElement second) {
        Long duration = Long.parseLong(second.getSeconds()) - Long.parseLong(first.getSeconds());
        Long hours = duration/3600;
        Long minutes = (duration%3600)/60;
        return String.valueOf(hours) + " hours " + String.valueOf(minutes) + " minutes";
    }
}
