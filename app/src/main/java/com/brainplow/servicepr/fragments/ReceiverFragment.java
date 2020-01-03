package com.brainplow.servicepr.fragments;


import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.brainplow.servicepr.R;
import com.brainplow.servicepr.activities.MyCustomBroadcastReceiverActivity;
import com.brainplow.servicepr.broadcast.MyCustomBroadCastReceiver;
import com.brainplow.servicepr.interfaces.IClickListener;
import com.brainplow.servicepr.services.BroadCastService;

import static com.brainplow.servicepr.utils.App.CHANNEL_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReceiverFragment extends Fragment{

    public  TextView textView;
    public static final String broadAction="Action Happened";
    MyCustomBroadCastReceiver myReceiver;
    public ReceiverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_receiver, container, false);

        textView=view.findViewById(R.id.txt);
        return view;
    }

    @Override
    public void onResume() {

        super.onResume();

        setReceiver();
    }

    public void setTextView(String txt){
        textView.setText(txt);
    }
    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(myReceiver);
    }

    private void setReceiver() {
        myReceiver=new MyCustomBroadCastReceiver();
        IntentFilter intentFilter=new IntentFilter();
       // intentFilter.addAction(broadAction);
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        getContext().registerReceiver(myReceiver,intentFilter);
    }





}
