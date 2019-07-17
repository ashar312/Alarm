package com.example.alarm;

public class Schedule {

    private String Monday;
    private String Tuesday;
    private String Wednesday;
    private String thursday;
    private String Friday;
    private String Saturday;
    private String Sunday;

    public Schedule(){}

    public String getFriday() {
        return Friday;
    }

    public String getMonday() {
        return Monday;
    }

    public String getSaturday() {
        return Saturday;
    }

    public String getSunday() {
        return Sunday;
    }

    public String getThursday() {
        return thursday;
    }

    public String getTuesday() {
        return Tuesday;
    }

    public String getWednesday() {
        return Wednesday;
    }


    public void setMonday(String monday) {
        Monday = monday;
    }

    public void setTuesday(String tuesday) {
        Tuesday = tuesday;
    }

    public void setWednesday(String wednesday) {
        Wednesday = wednesday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public void setFriday(String friday) {
        Friday = friday;
    }

    public void setSaturday(String saturday) {
        Saturday = saturday;
    }

    public void setSunday(String sunday) {
        Sunday = sunday;
    }
}
