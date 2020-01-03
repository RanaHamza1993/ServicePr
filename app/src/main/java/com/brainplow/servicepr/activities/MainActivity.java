package com.brainplow.servicepr.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.brainplow.servicepr.R;
import com.brainplow.servicepr.services.ForegroundService;

public class MainActivity extends AppCompatActivity {
    private Button startServiceButton;
    private Button stopServiceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startServiceButton = findViewById(R.id.start_button);
        stopServiceButton = findViewById(R.id.stop_button);

        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(v.getContext(), ForegroundService.class));
            }
        });

        stopServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(v.getContext(), ForegroundService.class));
            }
        });

    }
}