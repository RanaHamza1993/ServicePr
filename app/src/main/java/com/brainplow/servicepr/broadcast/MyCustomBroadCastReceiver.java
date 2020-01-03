package com.brainplow.servicepr.broadcast;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.brainplow.servicepr.R;
import com.brainplow.servicepr.activities.MyCustomBroadcastReceiverActivity;
import com.brainplow.servicepr.interfaces.IClickListener;

import static com.brainplow.servicepr.fragments.ReceiverFragment.broadAction;
import static com.brainplow.servicepr.utils.App.CHANNEL_ID;

public class MyCustomBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null) {
         //   if (action.equals(broadAction)) {
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {

                // Toast.makeText(context,"Receiver Called",Toast.LENGTH_SHORT).show();
                String txt = intent.getStringExtra("txt");
                IClickListener listener = (IClickListener) context;

                boolean isConnected= isNetworkAvailable(context);
                if(isConnected){
                    listener.onClick("connected");
                }else{
                    listener.onClick("notconnected");
                }
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
            }
        }
    }
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm != null ? cm.getActiveNetworkInfo() : null;
        return networkInfo != null && networkInfo.isConnected();
    }

}