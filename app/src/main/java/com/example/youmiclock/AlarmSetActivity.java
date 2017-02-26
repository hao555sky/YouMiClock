package com.example.youmiclock;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.youmiclock.adapter.TimeRepeatAdapter;

import java.util.ArrayList;
import java.util.List;

public class AlarmSetActivity extends AppCompatActivity implements View.OnClickListener{

    private PopupWindow popupWindow;

    // 重复选项
    private RelativeLayout repeatChoose;

    // 铃声选择
    private RelativeLayout ringChoose;

    private Button alarmSetSure;

    // 存放重复时间的选项
    private List<String> list = new ArrayList<>();

    private boolean TimeRepeatListInitFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_set);

        if(Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        repeatChoose = (RelativeLayout)findViewById(R.id.repeat_choose);
        repeatChoose.setOnClickListener(this);

        ringChoose = (RelativeLayout)findViewById(R.id.ring_choose);
        ringChoose.setOnClickListener(this);

        alarmSetSure = (Button)findViewById(R.id.alarm_set_sure);
        alarmSetSure.setOnClickListener(this);

    }

    private void initData(){


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.repeat_choose:
                Toast.makeText(this, "Repeat", Toast.LENGTH_SHORT).show();
                showPopupWindow();
                break;
            case R.id.ring_choose:
                break;
            case R.id.alarm_set_sure:
                break;
//            case R.id.repeat_time_only_once:
//                Toast.makeText(this, "Only Once", Toast.LENGTH_SHORT).show();
//                break;
            default:break;
        }
    }

    private void showPopupWindow(){
        View contentView = LayoutInflater.from(AlarmSetActivity.this)
                .inflate(R.layout.time_repeat_popupwindow, null);

        popupWindow = new PopupWindow(contentView, 700,
                WindowManager.LayoutParams.WRAP_CONTENT, true);

//        TextView repeatTimeOnce = (TextView)contentView.findViewById(R.id.repeat_time_only_once);
//        TextView repeatTimeEveryday = (TextView)contentView.findViewById(R.id.repeat_time_everyday);
//        TextView repeatTimeMonToFri = (TextView)contentView.findViewById(R.id.repeat_time_Mon_to_Fri);
//
//        repeatTimeOnce.setOnClickListener(this);
//        repeatTimeEveryday.setOnClickListener(this);
//        repeatTimeMonToFri.setOnClickListener(this);


        RecyclerView recyclerView = (RecyclerView)contentView.findViewById(R.id.time_repeat_recycle_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        if(!TimeRepeatListInitFlag){
            initTimeRepeatName();
        }
        TimeRepeatAdapter adapter = new TimeRepeatAdapter(list);
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new com.example.youmiclock.DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);

        View rootView = LayoutInflater.from(AlarmSetActivity.this).
                inflate(R.layout.activity_alarm_set, null);
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);

    }

    private void initTimeRepeatName(){

        TimeRepeatListInitFlag = true;

        String name1 = "只响一次";
        list.add(name1);

        String name2 = "每天";
        list.add(name2);

        String name3 = "周一至周五";
        list.add(name3);

        String name4 = "法定工作日";
        list.add(name4);

        String name5 = "自定义";
        list.add(name5);
    }
}
