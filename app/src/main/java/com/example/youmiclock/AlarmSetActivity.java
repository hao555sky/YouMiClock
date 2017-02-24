package com.example.youmiclock;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class AlarmSetActivity extends AppCompatActivity implements View.OnClickListener{

    private PopupWindow popupWindow;

    // 重复选项
    private RelativeLayout repeatChoose;

    // 铃声选择
    private RelativeLayout ringChoose;

    private Button alarmSetSure;

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

//        final Button button = (Button)findViewById(R.id.alarm_set_sure);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(popupWindow.isShowing()){
//                    popupWindow.dismiss();
//                }else {
//                    popupWindow.showAsDropDown(button);
//                }
//            }
//        });
    }

    private void initData(){


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.repeat_choose:
                Toast.makeText(this, "Repeat", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ring_choose:
                break;
            case R.id.alarm_set_sure:
                break;
            default:break;
        }
    }
}
