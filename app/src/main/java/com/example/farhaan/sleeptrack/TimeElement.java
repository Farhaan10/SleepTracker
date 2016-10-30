package com.example.farhaan.sleeptrack;

/**
 * Created by Farhaan on 27-10-2016.
 */
public class TimeElement {

    int _id;
    int _date;
    int _month;
    int _year;
    int _hour;
    int _minute;
    String _seconds;
    String _state;

    public TimeElement() {

    }

    public TimeElement(int id, int date, int month, int year, int hour, int minute, String seconds, String state) {
        this._id = id;
        this._date = date;
        this._month = month;
        this._year = year;
        this._hour = hour;
        this._minute = minute;
        this._seconds = seconds;
        this._state = state;
    }

    public TimeElement(int date, int month, int year, int hour, int minute, String seconds, String state) {
        this._date = date;
        this._month = month;
        this._year = year;
        this._hour = hour;
        this._minute = minute;
        this._seconds = seconds;
        this._state = state;
    }

    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public int getDate() {
        return this._date;
    }

    public void setDate(int date) {
        this._date = date;
    }

    public int getMonth() {
        return this._month;
    }

    public void setMonth(int month) {
        this._month = month;
    }

    public int getYear() {
        return this._year;
    }

    public void setYear(int year) {
        this._year = year;
    }

    public int getHour() {
        return this._hour;
    }

    public void setHour(int hour) {
        this._hour = hour;
    }

    public int getMinute() {
        return this._minute;
    }

    public void setMinute(int minute) {
        this._minute = minute;
    }

    public String getSeconds() {
        return this._seconds;
    }

    public void setSeconds(String seconds) {
        this._seconds = seconds;
    }

    public String getState() {
        return this._state;
    }

    public void setState(String state) {
        this._state = state;
    }
}
