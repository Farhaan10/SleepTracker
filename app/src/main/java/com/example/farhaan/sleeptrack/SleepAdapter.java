package com.example.farhaan.sleeptrack;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Farhaan on 30-10-2016.
 */
public class SleepAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String> dates;
    private final List<String> times;
    private final List<String> durations;
    TextView date, time, duration;

    public SleepAdapter(Activity context, int resource, List<String> dates, List<String> times, List<String> durations) {
        super(context, resource, dates);
        this.context = context;
        this.dates = dates;
        this.times = times;
        this.durations = durations;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        int size = dates.size()-1;
        LayoutInflater inflater = context.getLayoutInflater();
        final View rowView;
        rowView = inflater.inflate(R.layout.layout_row, null, true);
        date = (TextView) rowView.findViewById(R.id.text_date);
        date.setText(dates.get(size-position));
        time = (TextView) rowView.findViewById(R.id.text_time);
        time.setText(times.get(size-position));
        duration = (TextView) rowView.findViewById(R.id.text_duration);
        duration.setText(durations.get(size-position));
        return rowView;
    }
}
