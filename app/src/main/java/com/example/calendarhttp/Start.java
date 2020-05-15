package com.example.calendarhttp;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Start {
    @SerializedName("date")
    @Expose
    private String date;
//    @SerializedName("dateTime")
//    @Expose
    private String dateTime;
//    @SerializedName("timeZone")
//    @Expose
    private String timeZone;

    public Start(String date){
        date = this.date;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @NonNull
    @Override
    public String toString() {
        return "date: "+this.date;
    }

}