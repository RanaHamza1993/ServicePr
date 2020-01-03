package com.brainplow.servicepr.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.brainplow.servicepr.R;
import com.brainplow.servicepr.services.MyService;

public class IntentServiceActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    EditText editText;
    MyReceiver myReceiver;
    int i=1;

    public static final String FILTER_ACTION_KEY = "999";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.inputText);

        //Connecting submit on keyboard with the button.
        editText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    button.performClick();
                    return true;
                }
                return false;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message=editText.getText().toString();
                Intent intent=new Intent(IntentServiceActivity.this,MyService.class);
                intent.putExtra("message",message);
                if(i==1)
                startService(intent);
                i++;
            }
        });
    }

    private void setReceiver() {
        myReceiver=new MyReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(FILTER_ACTION_KEY);
        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver,intentFilter);
    }

    @Override
    protected void onStart() {
        setReceiver();
        super.onStart();
    }

    @Override
    protected void onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver);
        super.onStop();
    }


    //Broadcast Receiver to update textview.
   private class MyReceiver extends BroadcastReceiver{


        @Override
        public void onReceive(Context context, Intent intent) {
         String message=intent.getStringExtra("broadcastMessage");
         textView.setText(editText.getText()+"\n"+message);
        }
    }

}
