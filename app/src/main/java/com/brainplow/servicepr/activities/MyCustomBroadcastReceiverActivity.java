package com.brainplow.servicepr.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.brainplow.servicepr.services.BroadCastService;
import com.brainplow.servicepr.R;

public class MyCustomBroadcastReceiverActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_custom_broadcast_receiver);
        Intent intent=new Intent(MyCustomBroadcastReceiverActivity.this, BroadCastService.class);

        startService(intent);
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {

        super.onStop();
    }


}
