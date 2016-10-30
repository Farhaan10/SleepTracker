package com.example.farhaan.sleeptrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Farhaan on 27-10-2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sleepDatabase";
    private static final String TABLE_NAME = "sleepTable";

    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";
    private static final String KEY_MONTH = "month";
    private static final String KEY_YEAR = "year";
    private static final String KEY_HOUR = "hour";
    private static final String KEY_MINUTE = "minute";
    private static final String KEY_SECONDS = "seconds";
    private static final String KEY_STATE = "state";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_DATE + " INTEGER," + KEY_MONTH + " INTEGER," + KEY_YEAR + " INTEGER," + KEY_HOUR + " INTEGER," + KEY_MINUTE + " INTEGER," + KEY_SECONDS + " TEXT," + KEY_STATE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addTime(TimeElement timeElement) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, timeElement.getDate());
        values.put(KEY_MONTH, timeElement.getMonth());
        values.put(KEY_YEAR, timeElement.getYear());
        values.put(KEY_HOUR, timeElement.getHour());
        values.put(KEY_MINUTE, timeElement.getMinute());
        values.put(KEY_SECONDS, timeElement.getSeconds());
        values.put(KEY_STATE, timeElement.getState());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<TimeElement> getAllTimes() {
        List<TimeElement> timeList = new ArrayList<TimeElement>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                TimeElement timeElement = new TimeElement();
                timeElement.setID(Integer.parseInt(cursor.getString(0)));
                timeElement.setDate(Integer.parseInt(cursor.getString(1)));
                timeElement.setMonth(Integer.parseInt(cursor.getString(2)));
                timeElement.setYear(Integer.parseInt(cursor.getString(3)));
                timeElement.setHour(Integer.parseInt(cursor.getString(4)));
                timeElement.setMinute(Integer.parseInt(cursor.getString(5)));
                timeElement.setSeconds(cursor.getString(6));
                timeElement.setState(cursor.getString(7));
                timeList.add(timeElement);
            } while (cursor.moveToNext());
        }

        return timeList;
    }

    public int getTimesCount() {
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }
}
