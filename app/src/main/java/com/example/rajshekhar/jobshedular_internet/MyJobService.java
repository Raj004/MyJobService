package com.example.rajshekhar.jobshedular_internet;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {
    private static final String TAG="MyJobService";
    private boolean jobCancelled=false;
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG,"Job Started");
        doBackgraund(params);

        return true;
    }
    private void doBackgraund(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<40;i++){
                    Log.d(TAG,"run:"+i);
                    if(jobCancelled){
                        return;
                    }
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                Log.d(TAG,"Job Finished");
                jobFinished(params,false);

            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        //clean and check
    Log.d(TAG,"Job cancelled before completion");
    jobCancelled=true;
        return true;
    }
}