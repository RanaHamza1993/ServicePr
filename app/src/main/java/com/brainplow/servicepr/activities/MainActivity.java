package com.brainplow.servicepr.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.brainplow.servicepr.R;
import com.brainplow.servicepr.fragments.ReceiverFragment;
import com.brainplow.servicepr.fragments.SenderFragment;
import com.brainplow.servicepr.interfaces.IClickListener;
import com.brainplow.servicepr.services.ForegroundService;

public class MainActivity extends AppCompatActivity implements IClickListener {
    private Button startServiceButton;
    private Button stopServiceButton;
    FrameLayout sender;
    FrameLayout receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sender=findViewById(R.id.sender);
        receiver=findViewById(R.id.receiver);

        SenderFragment sfragment=new SenderFragment();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.sender,sfragment).commit();
        ReceiverFragment rfragment=new ReceiverFragment();
        FragmentTransaction transaction2=getSupportFragmentManager().beginTransaction();
        transaction2.replace(R.id.receiver,rfragment,"receiver").commit();

//        startServiceButton = findViewById(R.id.start_button);
//        stopServiceButton = findViewById(R.id.stop_button);

//        startServiceButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startService(new Intent(v.getContext(), ForegroundService.class));
//            }
//        });
//
//        stopServiceButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                stopService(new Intent(v.getContext(), ForegroundService.class));
//            }
//        });

    }

    @Override
    public void onClick(String txt) {
        if(txt.equalsIgnoreCase("connected")){
            Toast.makeText(this,"Internet Connected",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Internet notConnected",Toast.LENGTH_SHORT).show();

        }
        ReceiverFragment fragment= (ReceiverFragment) getSupportFragmentManager().findFragmentByTag("receiver");
        fragment.setTextView(txt);
    }
}