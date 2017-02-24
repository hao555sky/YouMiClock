package com.example.youmiclock.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.youmiclock.AlarmSetActivity;
import com.example.youmiclock.R;

/**
 * Created by hao on 17-2-16.
 */
public class AlarmClockFragment extends Fragment {

    private FloatingActionButton floatingAddAlarmButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alarm_fragment_layout, null);

        floatingAddAlarmButton = (FloatingActionButton)view.findViewById(R.id.floating_add_alarm_button);
        floatingAddAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AlarmSetActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }


}
