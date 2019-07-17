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
    }

}
