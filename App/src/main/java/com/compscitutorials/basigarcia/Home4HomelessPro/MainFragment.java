package com.compscitutorials.basigarcia.Home4HomelessPro;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.compscitutorials.basigarcia.navigationdrawervideotutorial.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment  {


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);


        final View btrecord =  rootView.findViewById(R.id.RecordButton);
        btrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.RecordButton:

                        Intent i = new Intent(getActivity(), record.class);
                        startActivity(i);
                        break;
                }
            }
        });
        final View btsearch =  rootView.findViewById(R.id.clearSearchButton);
        btsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.clearSearchButton:

                        Intent i = new Intent(getActivity(), search_report.class);
                        startActivity(i);
                        break;
                }
            }
        });
        final View btnotified =  rootView.findViewById(R.id.promptedButton);
        btnotified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.promptedButton:

                        Intent i = new Intent(getActivity(), notified_staff.class);
                        startActivity(i);
                        break;
                }
            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }



}
