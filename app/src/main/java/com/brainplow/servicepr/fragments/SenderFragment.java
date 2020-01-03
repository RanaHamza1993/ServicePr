package com.brainplow.servicepr.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brainplow.servicepr.R;

import static com.brainplow.servicepr.fragments.ReceiverFragment.broadAction;

/**
 * A simple {@link Fragment} subclass.
 */
public class SenderFragment extends Fragment {
    TextView senderTxt;
    public SenderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_sender, container, false);
        senderTxt=view.findViewById(R.id.sendertxt);
        senderTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(broadAction);
//                intent.putExtra("txt","Broadcast from fragment");
//                getActivity().sendBroadcast(intent);
            }
        });

        return view;
    }

}
