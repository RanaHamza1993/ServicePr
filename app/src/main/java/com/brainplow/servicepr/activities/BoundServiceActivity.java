package com.brainplow.servicepr.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.brainplow.servicepr.R;
import com.brainplow.servicepr.services.BoundService;

public class BoundServiceActivity extends AppCompatActivity {

    boolean isServiceBound=false;
    private BoundService mServiceBound;
    private ServiceConnection mServiceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BoundService.MyBinder myBinder=(BoundService.MyBinder) iBinder;
            mServiceBound=myBinder.getService();
            isServiceBound=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isServiceBound=false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_service);
        final TextView timestampText = (TextView) findViewById(R.id.timestamp_text);
        Button printTimestampButton = (Button) findViewById(R.id.print_timestamp);
        Button stopServiceButon = (Button) findViewById(R.id.stop_service);
        Button bindButton = findViewById(R.id.bind);

        printTimestampButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isServiceBound)
                    timestampText.setText(mServiceBound.getMsg().toString());
                else
                    Toast.makeText(getApplicationContext(),"Please Bind the service",Toast.LENGTH_SHORT).show();              }
        });
        bindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isServiceBound) {
                    Intent intent = new Intent(getApplicationContext(), BoundService.class);
                    //Service starts automatically when binded
                    //startService(intent);
                    bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
                }
            }
        });

        stopServiceButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isServiceBound){
                    isServiceBound=false;
                    unbindService(mServiceConnection);
                }
                //Unbinding stops the service automatically
                /*
                Intent intent = new Intent(MainActivity.this,
                        BoundService.class);
                stopService(intent);
                */
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isServiceBound) {
            unbindService(mServiceConnection);
            isServiceBound = false;
        }
    }


}
