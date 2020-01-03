package com.brainplow.servicepr.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.brainplow.servicepr.R;
import com.brainplow.servicepr.activities.MyCustomBroadcastReceiverActivity;

import static com.brainplow.servicepr.utils.App.CHANNEL_ID;

public class BroadCastService extends Service {
    MyCustomBroadcastReceiver myReceiver;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onCreate() {
        setReceiver();
        super.onCreate();
    }
    private void setReceiver() {
        myReceiver=new MyCustomBroadcastReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        registerReceiver(myReceiver,intentFilter);
    }
    @Override
    public void onDestroy() {
        unregisterReceiver(myReceiver);
        super.onDestroy();
    }
    public static class MyCustomBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action != null) {
                if (action.equals(Intent.ACTION_POWER_CONNECTED) ) {
                    Toast.makeText(context,"Receiver Called",Toast.LENGTH_SHORT).show();
                    Intent notificationIntent = new Intent(context, MyCustomBroadcastReceiverActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(context,
                            0, notificationIntent, 0);

                    //Build a notification
                    Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                            .setContentTitle("HandFree Plugged")
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setContentIntent(pendingIntent)
                            .build();
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                    notificationManager.notify(1, notification);
// TO-DO: Code to handle BOOT COMPLETED EVENT
// TO-DO: I can start an service.. display a notification… start an activity
                } } } }
}
