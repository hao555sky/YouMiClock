package com.example.youmiclock.tabs;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.youmiclock.R;

import java.util.Calendar;

/**
 * Created by hao on 17-2-16.
 */
public class AlarmClockFragment extends Fragment {

    private FloatingActionButton floatingAddAlarmButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alarm_fragment_layout, null);

        return view;
    }


}
