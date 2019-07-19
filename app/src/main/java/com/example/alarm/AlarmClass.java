package com.example.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class AlarmClass {

    /*private final int Monday = 2;
    private final int Tuesday = 3;
    private final int Wednesday = 4;
    private final int Thursday = 5;
    private final int Friday = 6;
    private final int Saturday = 7;
    private final int Sunday = 1;*/
    AlarmManager alarmManager;
    Schedule schedule;
    Schedule schedule1;
    public void setSchedule(SharedPreferences mPrefs) {
        schedule = new Schedule();
        schedule.setMonday("12:00 PM");
        schedule.setTuesday("12:00 PM");
        schedule.setWednesday("12:00 PM");
        schedule.setThursday("05:00 PM");
        schedule.setFriday("01:04 PM");
        schedule.setSaturday("12:00 PM");
        schedule.setSunday("");

        schedule1 = new Schedule();
        schedule1.setMonday("12:03 PM");
        schedule1.setTuesday("12:03 PM");
        schedule1.setWednesday("12:03 PM");
        schedule1.setThursday("05:02 PM");
        schedule1.setSaturday("12:03 PM");
        schedule1.setFriday("01:06 PM");
        schedule1.setSunday("");
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(schedule);
        prefsEditor.putString("PickDays", json);
        prefsEditor.commit();

        SharedPreferences.Editor prefsEditor1 = mPrefs.edit();
        Gson gson1 = new Gson();
        String json1 = gson1.toJson(schedule);
        prefsEditor1.putString("DropDays", json1);
        prefsEditor1.commit();

    }
    SharedPreferences mPrefs;

    public AlarmClass(AlarmManager alarmManager,Context context,SharedPreferences sharedPreferences){
        this.alarmManager = alarmManager;
        this.mPrefs =sharedPreferences;
        setSchedule(sharedPreferences);

        SetAlarm(schedule,0,context);
        SetAlarm(schedule1,7,context);
    }

    public Schedule RetrieveSchedule(String day){
        Gson gson = new Gson();
        String json = mPrefs.getString(day, "");
        Schedule schedule = gson.fromJson(json, Schedule.class);
        return schedule;
    }

    public String T12toT24(String input){
        //Format of the date defined in the input String
        DateFormat df = new SimpleDateFormat("hh:mm aa");
        //Desired format: 24 hour format: Change the pattern as per the need
        DateFormat outputformat = new SimpleDateFormat("HH:mm");
        Date date = null;
        String output = null;
        try{
            //Converting the input String to Date
            date= df.parse(input);
            //Changing the format of date and storing it in String
            output = outputformat.format(date);
            //Displaying the date
            return output;
        }catch(ParseException pe){
            return pe.getMessage();
        }
    }

    int timetemp;
    private int Change_Time_to_int(String time){
        time = T12toT24(time);
        String temp = "";
        String temp2 = "";
        temp += time.charAt(0)+"";
        temp += time.charAt(1)+"";
        temp2 += time.charAt(3)+"";
        temp2 += time.charAt(4)+"";
        timetemp = Integer.valueOf(temp2);
        return Integer.valueOf(temp);
    }

/*
    public void SetNextWeekDayAlarm(Schedule schedule,Context context, int daytime){
        switch (getDay()){
            case "Monday":
                Calendar calMonday = Calendar.getInstance();
                if(!schedule.getMonday().equals("")) {
                    calMonday = Calendar.getInstance();
                    calMonday.set(Calendar.DAY_OF_WEEK, Monday);
                    calMonday.set(Calendar.HOUR_OF_DAY,Change_Time_to_int(schedule.getMonday()));
                    calMonday.set(Calendar.MINUTE, timetemp);
                    calMonday.set(Calendar.SECOND, 0);
                    Log.d("Day","Day "+Monday+" Time "+Change_Time_to_int(schedule.getMonday())+" minute "+timetemp);
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calMonday.getTimeInMillis(),
                            pIntent(context,Monday));
                }
            case "Tuesday":
                Calendar caltuesday = Calendar.getInstance();
                if(!schedule.getTuesday().equals("")) {
                    schedule.setTuesday("06:49 PM");
                    caltuesday = Calendar.getInstance();
                    caltuesday.set(Calendar.DAY_OF_WEEK, Tuesday);
                    caltuesday.set(Calendar.HOUR_OF_DAY,Change_Time_to_int(schedule.getMonday()));
                    caltuesday.set(Calendar.MINUTE, timetemp);
                    caltuesday.set(Calendar.SECOND, 0);
                    Log.d("Day","Day "+Monday+" Time "+Change_Time_to_int(schedule.getMonday())+" minute "+timetemp);
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, caltuesday.getTimeInMillis(),
                            pIntent(context,Tuesday));
                }
            case "Wednesday":
                Calendar calWednesday = Calendar.getInstance();
                if(!schedule.getWednesday().equals("")) {
                    calWednesday = Calendar.getInstance();
                    calWednesday.set(Calendar.DAY_OF_WEEK, Wednesday);
                    calWednesday.set(Calendar.HOUR_OF_DAY,Change_Time_to_int(schedule.getMonday()));
                    calWednesday.set(Calendar.MINUTE, timetemp);
                    calWednesday.set(Calendar.SECOND, 0);
                    Log.d("Day","Day "+Monday+" Time "+Change_Time_to_int(schedule.getMonday())+" minute "+timetemp);
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calWednesday.getTimeInMillis(),
                            pIntent(context,Wednesday));
                }
            case "Thursday":
                Calendar calThursday = Calendar.getInstance();
                if(!schedule.getMonday().equals("")) {
                    calThursday = Calendar.getInstance();
                    calThursday.set(Calendar.DAY_OF_WEEK, Thursday);
                    calThursday.set(Calendar.HOUR_OF_DAY,Change_Time_to_int(schedule.getMonday()));
                    calThursday.set(Calendar.MINUTE, timetemp);
                    calThursday.set(Calendar.SECOND, 0);
                    Log.d("Day","Day "+Monday+" Time "+Change_Time_to_int(schedule.getMonday())+" minute "+timetemp);
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calThursday.getTimeInMillis(),
                            pIntent(context,Thursday));
                }
            case "Friday":
                Calendar calFriday = Calendar.getInstance();
                if(!schedule.getMonday().equals("")) {
                    calFriday = Calendar.getInstance();
                    calFriday.set(Calendar.DAY_OF_WEEK, Friday);
                    calFriday.set(Calendar.HOUR_OF_DAY,Change_Time_to_int(schedule.getMonday()));
                    calFriday.set(Calendar.MINUTE, timetemp);
                    calFriday.set(Calendar.SECOND, 0);
                    Log.d("Day","Day "+Monday+" Time "+Change_Time_to_int(schedule.getMonday())+" minute "+timetemp);
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calFriday.getTimeInMillis(),
                            pIntent(context,Friday));
                }
            case "Saturday":
                Calendar calSaturday = Calendar.getInstance();
                if(!schedule.getMonday().equals("")) {
                    calSaturday = Calendar.getInstance();
                    calSaturday.set(Calendar.DAY_OF_WEEK, Saturday);
                    calSaturday.set(Calendar.HOUR_OF_DAY,Change_Time_to_int(schedule.getMonday()));
                    calSaturday.set(Calendar.MINUTE, timetemp);
                    calSaturday.set(Calendar.SECOND, 0);
                    Log.d("Day","Day "+Monday+" Time "+Change_Time_to_int(schedule.getMonday())+" minute "+timetemp);
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calSaturday.getTimeInMillis(),
                            pIntent(context,Saturday));
                }
            case "Sunday":
                Calendar calSunday = Calendar.getInstance();
                if(!schedule.getMonday().equals("")) {
                    calSunday = Calendar.getInstance();
                    calSunday.set(Calendar.DAY_OF_WEEK, Sunday);
                    calSunday.set(Calendar.HOUR_OF_DAY,Change_Time_to_int(schedule.getMonday()));
                    calSunday.set(Calendar.MINUTE, timetemp);
                    calSunday.set(Calendar.SECOND, 0);
                    Log.d("Day","Day "+Monday+" Time "+Change_Time_to_int(schedule.getMonday())+" minute "+timetemp);
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calSunday.getTimeInMillis(),
                            pIntent(context,Sunday));
                }
        }
    }
*/




    public void SetAlarm(Schedule schedule,int daytime,Context context){
        Log.d("DayTime", daytime+"");
        Calendar calMonday = Calendar.getInstance();
        if(!schedule.getMonday().equals("")) {
            calMonday.setTimeInMillis(System.currentTimeMillis());
            calMonday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            calMonday.set(Calendar.HOUR_OF_DAY,Change_Time_to_int(schedule.getMonday()));
            calMonday.set(Calendar.MINUTE, timetemp);
            calMonday.set(Calendar.SECOND, 0);
        //    Log.d("Day","Day "+Monday+" Time "+Change_Time_to_int(schedule.getMonday())+" minute "+timetemp);
        }

        Calendar calTuesday = Calendar.getInstance();
        if(!schedule.getTuesday().equals("")) {
            calTuesday.setTimeInMillis(System.currentTimeMillis());
            calTuesday.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
            calTuesday.set(Calendar.HOUR_OF_DAY, Change_Time_to_int(schedule.getTuesday()));
            calTuesday.set(Calendar.MINUTE, timetemp);
            calTuesday.set(Calendar.SECOND, 0);
      //      Log.d("Day","Day "+Tuesday+" Time "+Change_Time_to_int(schedule.getTuesday())+" minute "+timetemp);
        }


        Calendar calWed = Calendar.getInstance();
        if(!schedule.getWednesday().equals("")) {
            calWed.setTimeInMillis(System.currentTimeMillis());
            calWed.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
            calWed.set(Calendar.HOUR_OF_DAY, Change_Time_to_int(schedule.getWednesday()));
            calWed.set(Calendar.MINUTE, timetemp);
            calWed.set(Calendar.SECOND, 0);
        //    Log.d("Day","Day "+Wednesday+" Time "+Change_Time_to_int(schedule.getWednesday())+" minute "+timetemp);
        }
        Calendar calthursday = Calendar.getInstance();
        if(!schedule.getThursday().equals("")) {
            calthursday.setTimeInMillis(System.currentTimeMillis());
            calthursday.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
            int a =  Change_Time_to_int(schedule.getThursday());
            calthursday.set(Calendar.HOUR_OF_DAY,a);
            calthursday.set(Calendar.MINUTE, timetemp);
            calthursday.set(Calendar.SECOND, 0);
         //   Log.d("Day","Day "+Thursday+" Time "+Change_Time_to_int(schedule.getThursday())+" minute "+timetemp);
        }
        Calendar calfriday = Calendar.getInstance();
        if(!schedule.getFriday().equals("")) {
            calfriday.setTimeInMillis(System.currentTimeMillis());
            calfriday.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
            calfriday.set(Calendar.HOUR_OF_DAY, Change_Time_to_int(schedule.getFriday()));
            calfriday.set(Calendar.MINUTE, timetemp);
            calfriday.set(Calendar.SECOND, 0);
         //   Log.d("Day","Day "+Friday+" Time "+Change_Time_to_int(schedule.getFriday())+" minute "+timetemp);
        }

        Calendar calsaturday = Calendar.getInstance();
        if(!schedule.getSaturday().equals("")) {
            calsaturday.setTimeInMillis(System.currentTimeMillis());
            calsaturday.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
            calsaturday.set(Calendar.HOUR_OF_DAY, Change_Time_to_int(schedule.getSaturday()));
            calsaturday.set(Calendar.MINUTE, timetemp);
            calsaturday.set(Calendar.SECOND, 0);
         //   Log.d("Day","Day "+Saturday+" Time "+Change_Time_to_int(schedule.getSaturday())+" minute "+timetemp);
        }

        Calendar calsunday = Calendar.getInstance();
        if(!schedule.getSunday().equals("")) {
            calsunday.setTimeInMillis(System.currentTimeMillis());
            calsunday.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            calsunday.set(Calendar.HOUR_OF_DAY, Change_Time_to_int(schedule.getSunday()));
            calsunday.set(Calendar.MINUTE, timetemp);
            calsunday.set(Calendar.SECOND, 0);
         //   Log.d("Day","Day "+Sunday+" Time "+Change_Time_to_int(schedule.getSunday())+" minute "+timetemp);
        }


        //    Intent intent = new Intent(getApplicationContext(),Notification_reciever.class);
        //    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);


        long MondayTime,TuesTime,WedTime,ThursdayTime,FridayTime,SatTime,SunTime;


        if(!schedule.getMonday().equals("")) {
            if (System.currentTimeMillis() > calMonday.getTimeInMillis()) {
                MondayTime = calMonday.getTimeInMillis() + AlarmManager.INTERVAL_DAY * 7;
            } else {
                MondayTime = calMonday.getTimeInMillis();
            }
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, MondayTime, AlarmManager.INTERVAL_DAY * 7,
                    pIntent(context, daytime + 1));
        }
        if(!schedule.getTuesday().equals("")) {
            if (System.currentTimeMillis() > calMonday.getTimeInMillis()) {
                TuesTime = calTuesday.getTimeInMillis() + AlarmManager.INTERVAL_DAY * 7;
            } else {
                TuesTime = calTuesday.getTimeInMillis();
            }
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, TuesTime,AlarmManager.INTERVAL_DAY * 7,
                    pIntent(context,daytime+2));
        }
        if(!schedule.getWednesday().equals("")) {
            if (System.currentTimeMillis() > calMonday.getTimeInMillis()) {
                WedTime = calWed.getTimeInMillis() + AlarmManager.INTERVAL_DAY * 7;
            } else {
                WedTime = calWed.getTimeInMillis();
            }
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, WedTime,AlarmManager.INTERVAL_DAY * 7,
                    pIntent(context,daytime+3));
        }
        if(!schedule.getThursday().equals("")) {
            if (System.currentTimeMillis() > calMonday.getTimeInMillis()) {
                ThursdayTime = calthursday.getTimeInMillis() + AlarmManager.INTERVAL_DAY * 7;
            } else {
                ThursdayTime = calthursday.getTimeInMillis();
            }
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, ThursdayTime,AlarmManager.INTERVAL_DAY * 7,
                    pIntent(context,daytime+4));
        }
        if(!schedule.getFriday().equals("")) {
            if (System.currentTimeMillis() > calMonday.getTimeInMillis()) {
                FridayTime = calfriday.getTimeInMillis() + AlarmManager.INTERVAL_DAY * 7;
            } else {
                FridayTime = calfriday.getTimeInMillis();
            }
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, FridayTime,AlarmManager.INTERVAL_DAY * 7,
                    pIntent(context,daytime+5));
        }
        if(!schedule.getSaturday().equals("")) {
            if (System.currentTimeMillis() > calMonday.getTimeInMillis()) {
                SatTime = calsaturday.getTimeInMillis() + AlarmManager.INTERVAL_DAY * 7;
            } else {
                SatTime = calsaturday.getTimeInMillis();
            }
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, SatTime,AlarmManager.INTERVAL_DAY * 7,
                    pIntent(context,daytime+6));
        }
        if(!schedule.getSunday().equals("")) {
            if (System.currentTimeMillis() > calMonday.getTimeInMillis()) {
                SunTime = calsunday.getTimeInMillis() + AlarmManager.INTERVAL_DAY * 7;
            } else {
                SunTime = calsunday.getTimeInMillis();
            }
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, SunTime,AlarmManager.INTERVAL_DAY * 7,
                    pIntent(context,daytime+7));
        }

    }


    public PendingIntent pIntent(Context context, int i) {
        Intent intent = new Intent(context, Notification_reciever.class);
        return PendingIntent.getBroadcast(context, i, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public String getDay() {
        String DayOfWeek = "";
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
                DayOfWeek = "Sunday";
                break;
            case Calendar.MONDAY:
                DayOfWeek = "Monday";
                break;
            case Calendar.TUESDAY:
                DayOfWeek = "Tuesday";
                break;
            case Calendar.WEDNESDAY:
                DayOfWeek = "Wednesday";
                break;
            case Calendar.THURSDAY:
                DayOfWeek = "thursday";
                break;
            case Calendar.FRIDAY:
                DayOfWeek = "Friday";
                break;
            case Calendar.SATURDAY:
                DayOfWeek = "Saturday";
                break;
        }
        return DayOfWeek;
    }

    public void CancelAlarm(Context context,AlarmManager alarmManager){
        Intent intent = new Intent(context, Notification_reciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmManager.cancel(pendingIntent);
    }
}
