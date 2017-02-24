package com.example.youmiclock.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.youmiclock.R;

import java.util.Calendar;

/**
 * Created by hao on 17-2-18.
 */
public class AlarmClockView extends View {

    private static final String TAG = "AlarmClockView";

    private int circleRadius;
    private int circleColor;
    private int circleChangeColor;

    private int hour;
    private int minute;
    private int second;
    private int millisecond;

    private float secondDegree;

    private Paint circlePaint;

    private Paint radiusPaint;

    private RectF oval;

    // 表盘中心X坐标
    private float centerX;

    // 表盘中心Y坐标
    private float centerY;

    // 时间画笔
    private Paint timePaint;

    // 获取时间
    private Calendar calendar;

    // 表盘显示时间
    private String displayTime;

    private Rect textBound = new Rect();

    public AlarmClockView(Context context) {
        super(context);
        init();
    }

    public AlarmClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AlarmClockView);

        try {
            circleRadius = typedArray.getDimensionPixelSize(R.styleable.AlarmClockView_ACVCircle_radius, 50);
            circleColor = typedArray.getColor(R.styleable.AlarmClockView_ACVCircle_color, Color.parseColor("#cfd8dc"));
            circleChangeColor = typedArray.getColor(R.styleable.AlarmClockView_ACVCircle_change_color, Color.BLACK);
        }finally {
            typedArray.recycle();
        }
        init();
    }

    public AlarmClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    invalidate();
                    handler.sendEmptyMessage(0);
                    break;
                default:break;
            }
        }
    };

    private void init(){

        Log.d(TAG, "initCiclePaint: " + circleColor + "*****" + circleRadius);

        circlePaint = new Paint();
        circlePaint.setStrokeWidth(14);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setColor(circleColor);

        radiusPaint = new Paint();
        radiusPaint.setStrokeWidth(14);
        radiusPaint.setColor(circleChangeColor);
        radiusPaint.setStyle(Paint.Style.STROKE);

        timePaint = new Paint();
        timePaint.setStrokeWidth(5);
        timePaint.setAntiAlias(true);
        timePaint.setStyle(Paint.Style.FILL);
        timePaint.setColor(Color.WHITE);
        float densityText = getResources().getDisplayMetrics().scaledDensity;
        timePaint.setTextSize(60 * densityText);
        timePaint.getTextBounds("00:00", 0, "00:00".length(), textBound);

        oval = new RectF(-circleRadius, -circleRadius, circleRadius,circleRadius);

        centerX = getWidth() / 2;
        centerY = getHeight() / 2;

        handler.sendEmptyMessage(0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if(widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
            circleRadius = getWidth() / 3;
        }else {
            width = widthSize / 2;
            circleRadius = 100;
            oval.set(-100, -100, 100, 100);
        }

        if(heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }else {
            height = heightSize / 2;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.drawCircle(0, 0, circleRadius, circlePaint);

        getDegree();
        canvas.drawArc(oval, -90, secondDegree, false, radiusPaint);

        setDisplayTime();
        canvas.drawText(displayTime, centerX - textBound.width() / 2, centerY + textBound.height() / 2, timePaint);
        Log.d(TAG, "onDraw: " + displayTime + ":" + second);
    }

    private void getTime(){
        calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        millisecond = calendar.get(Calendar.MILLISECOND);
    }

    // 获取秒针角度
    private void getDegree(){
        getTime();
        secondDegree = (float)((second * 1000 + millisecond) / (60.0 * 1000) * 360);
    }

    // 获取表盘展示时间
    private void setDisplayTime(){
        displayTime = TimeAddZero(String.valueOf(hour)) + ":" + TimeAddZero(String.valueOf(minute));
    }

    // 如果时间小于12， 加0
    private String TimeAddZero(String time){
        if(time.length() == 1){
            return "0" + time;
        }
        return time;
    }

}
