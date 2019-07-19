package com.example.alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {




    //mohib code

    public static final int REQUEST_CODE=101;
    //Button button;
    AlarmManager alarmManager;
    Intent intent;
    static  Context context;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(this, "On Create Called", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.DAY_OF_WEEK, 6);
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 0);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(System.currentTimeMillis());
        calendar1.set(Calendar.DAY_OF_WEEK, 4);
        calendar1.set(Calendar.HOUR_OF_DAY, 16);
        calendar1.set(Calendar.MINUTE, 15);
        calendar1.set(Calendar.SECOND, 0);


        Log.d("moheb", "calendar " + calendar.getTime() + " milli " + calendar.getTimeInMillis());

        long TimeUntiltrigger;
        if (System.currentTimeMillis() > calendar.getTimeInMillis()) {
            TimeUntiltrigger = calendar.getTimeInMillis() + AlarmManager.INTERVAL_DAY * 7;
            Log.d("TIMEUNTILTRIGGER","Calender");
            Toast.makeText(getApplicationContext(), "Calender", Toast.LENGTH_SHORT).show();
        } else {
            TimeUntiltrigger = calendar.getTimeInMillis();
            Log.d("Not TIMEUNTILTRIGGER","Calender");
            Toast.makeText(getApplicationContext(), "not Calender", Toast.LENGTH_SHORT).show();
        }
        intent = new Intent(MainActivity.this, Notification_reciever.class);
        pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, TimeUntiltrigger
                , AlarmManager.INTERVAL_DAY * 7, pendingIntent);


        if (System.currentTimeMillis() > calendar1.getTimeInMillis()) {
            TimeUntiltrigger = calendar1.getTimeInMillis() + AlarmManager.INTERVAL_DAY * 7;
            Log.d("aTIMEUNTILTRIGGER","Calender");
            Toast.makeText(getApplicationContext(), "Calender1", Toast.LENGTH_SHORT).show();
        } else {
            TimeUntiltrigger = calendar1.getTimeInMillis();
            Log.d("aNot TIMEUNTILTRIGGER","Calender1");
            Toast.makeText(getApplicationContext(), "not Calender1", Toast.LENGTH_SHORT).show();
        }
        intent = new Intent(MainActivity.this, Notification_reciever.class);
        pendingIntent = PendingIntent.getBroadcast(this, 102, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, TimeUntiltrigger
                , AlarmManager.INTERVAL_DAY * 7, pendingIntent);


        /*if (calendar.getTimeInMillis() >= System.currentTimeMillis()) {

            pendingIntent = PendingIntent.getBroadcast(this, 102, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis()
                    , AlarmManager.INTERVAL_DAY * 7, pendingIntent);
            PendingIntent pendingIntent2 = PendingIntent.getBroadcast(getApplicationContext(), REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            if (pendingIntent2 != null)
                Toast.makeText(this, "intent created", Toast.LENGTH_SHORT).show();
        }*/
        Toast.makeText(this, "On Start Called", Toast.LENGTH_SHORT).show();
        super.onStart();
    }





    /*//My Code
    static Context context;
    static AlarmManager alarmManager;
    SharedPreferences sharedPreferences;
    static AlarmClass alarmClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        Toast.makeText(this, "On Create Called", Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onStart() {
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        sharedPreferences = getSharedPreferences("MyDays",MODE_PRIVATE);
        alarmClass = new AlarmClass(alarmManager,getApplicationContext(),sharedPreferences);
    //  alarmClass.CancelAlarm(getBaseContext(),alarmManager);
        SharedPreferences preferences = getSharedPreferences("MyDays", 0);
        preferences.edit().remove("PickDays").commit();
        Log.d("SCHEDULE",alarmClass.RetrieveSchedule("PickDays")+"");
        if(alarmClass.RetrieveSchedule("PickDays") == null){
            Log.d("SCHEDULE",alarmClass.RetrieveSchedule("PickDays")+"");
            Log.d("RetrieveSchedule","NULL");
            alarmClass.setSchedule(sharedPreferences);
            alarmClass.SetAlarm(alarmClass.RetrieveSchedule("PickDays"),0,getApplicationContext());
        }
        Toast.makeText(this, "On Start Called", Toast.LENGTH_SHORT).show();
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "On Stop Called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "On Restart Called", Toast.LENGTH_SHORT).show();
    }

    public boolean CheckAlertService(Context context,int daytime)
    {
        Intent i = new Intent(context, Notification_reciever.class);
        Boolean alarmup=(PendingIntent.getBroadcast(context, daytime, i, PendingIntent.FLAG_NO_CREATE)!=null);
        return alarmup;
    }*/

}
