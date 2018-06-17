package com.onaft.kravchenko.wave.waveandroid;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.onaft.kravchenko.wave.waveandroid.manage.DataManager;
import com.onaft.kravchenko.wave.waveandroid.model.Account;
import com.onaft.kravchenko.wave.waveandroid.model.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShootingSchedulerService extends JobService {

    private static final String TAG = "ShootingService";
    private boolean jobCancelled = false;
    private DataManager mDataManager;
    private Account mAccount;
    NotificationManagerCompat mNotificationManager;
    PendingIntent resultPendingIntent;
    NotificationCompat.Builder mBuilder;
    public static final String EVENT_ACTION = "com.onaft.kravchenko.wave.waveandroid.action.EVENT";


    @Override
    public boolean onStartJob(JobParameters params) {
        Toast.makeText(this, "Start ShootingSchedulerService", Toast.LENGTH_SHORT).show();
        mDataManager = DataManager.getInstance();
        mAccount = mDataManager.getmPreferencesManager().loadAccount();
        settingNotify();
        doBackgroundWork(params);
        return true;
    }

    private void doBackgroundWork(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                if (jobCancelled) {
                    return;
                }
                Call<List<Event>> responseCall = mDataManager.shootingEventById(String.valueOf(mAccount.getId_employee()));
                if (mAccount != null)
                    if (mAccount.getId_type_access()==1)
                        responseCall = mDataManager.shootingEventAll();
                responseCall.enqueue(new Callback<List<Event>>() {
                    @Override
                    public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                        if (mDataManager.getmPreferencesManager().loadShooting().size()<response.body().size()){
                            mNotificationManager.notify(111, mBuilder.build());
                            Intent intent = new Intent();
                            intent.setAction(EVENT_ACTION);
                            intent.putExtra("com.onaft.kravchenko.wave.waveandroid.broadcast.event", new Gson().toJson(response.body()));
                            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                            sendBroadcast(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Event>> call, Throwable t) {

                    }
                });

                Log.d(TAG, "Job finished");
                jobFinished(params, false);
            }
        }).start();
    }

    private void settingNotify(){
        mNotificationManager = NotificationManagerCompat.from(this);
        mDataManager = DataManager.getInstance();
        mBuilder = new NotificationCompat.Builder(getApplicationContext(),"1")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Wave")
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(Notification.PRIORITY_MAX)
                .setContentText("Изменение в графике съёмок");
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job cancelled before completion");
        jobCancelled = true;
        return true;
    }

}
