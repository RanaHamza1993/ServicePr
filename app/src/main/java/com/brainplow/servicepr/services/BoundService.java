package com.brainplow.servicepr.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;

import androidx.annotation.Nullable;

public class BoundService extends Service {
    private static String LOG_TAG = "BoundService";
    private IBinder mBinder=new MyBinder();
    private int myVal=1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public Integer getMsg(){

        return myVal++;
    }
    public class MyBinder extends Binder {
        public BoundService getService(){

            return BoundService.this;
        }

    }
}