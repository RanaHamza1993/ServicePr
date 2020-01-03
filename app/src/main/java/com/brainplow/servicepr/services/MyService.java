package com.brainplow.servicepr.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.brainplow.servicepr.activities.IntentServiceActivity;
import com.brainplow.servicepr.activities.MainActivity;

public class MyService extends IntentService {

    public MyService() {
        super("MyService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String message = intent.getStringExtra("message");
        intent.setAction(IntentServiceActivity.FILTER_ACTION_KEY);
        //This wait can be for downloading, sending a request, decoding a file etc.
        SystemClock.sleep(5000);

        String resultMessage = "The Result String  after 6 seconds of processing.." + message;
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent.putExtra("broadcastMessage", resultMessage));
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Toast.makeText(getApplicationContext(), "Intent SERVICE CREATED", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Intent SERVICE DESTROYED", Toast.LENGTH_SHORT).show();
    }


}