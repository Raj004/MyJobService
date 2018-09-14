package com.example.rajshekhar.jobshedular_internet;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.nfc.Tag;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn;
    private static final String TAG ="MainActivity";


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addJobNew();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void addJobNew() {
        ComponentName componentName= new ComponentName(this,MyJobService.class);
        JobInfo info= new JobInfo.Builder(123,componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15*60*1000)
                .build();

        JobScheduler scheduler =(JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode=scheduler.schedule(info);
        if(resultCode == JobScheduler.RESULT_SUCCESS){
            Log.d(TAG,"Job Shedule");
        }else {
            Log.d(TAG,"Job Shedule Failed");
        }
    }

//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public void addJob(View view) {
//        ComponentName componentName= new ComponentName(this,MyJobService.class);
//        JobInfo info= new JobInfo.Builder(123,componentName)
//        .setRequiresCharging(true)
//        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
//        .setPersisted(true)
//        .setPeriodic(15*60*1000)
//        .build();
//
//        JobScheduler scheduler =(JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
//        int resultCode=scheduler.schedule(info);
//        if(resultCode == JobScheduler.RESULT_SUCCESS){
//            Log.d(TAG,"Job Shedule");
//        }else {
//            Log.d(TAG,"Job Shedule Failed");
//        }
//    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void cancel(View view) {
       JobScheduler scheduler=(JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
       scheduler.cancel(123);
       Log.d(TAG,"cancelJob:");
    }
    }


