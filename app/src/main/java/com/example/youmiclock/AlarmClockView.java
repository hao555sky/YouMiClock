package com.example.youmiclock;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

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

    public AlarmClockView(Context context) {
        super(context);
        initPaint();
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
        initPaint();
    }

    public AlarmClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();
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

    private void initPaint(){

        Log.d(TAG, "initCiclePaint: " + circleColor + "*****" + circleRadius);

        circlePaint = new Paint();
        circlePaint.setStrokeWidth(10);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setColor(circleColor);

        radiusPaint = new Paint();
        radiusPaint.setStrokeWidth(10);
        radiusPaint.setColor(circleChangeColor);
        radiusPaint.setStyle(Paint.Style.STROKE);

        oval = new RectF(-circleRadius, -circleRadius, circleRadius,circleRadius);

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
        }else {
            width = widthSize / 2;
            circleRadius = 80;
            oval.set(-80, -80, 80, 80);
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
        Log.d(TAG, "onDraw: " + hour + ":" + minute + ":" + second);
        canvas.translate(getWidth()/ 2, getHeight()/ 2);
        canvas.drawCircle(0, 0, circleRadius, circlePaint);

        getDegree();
        // canvas.drawRect(oval, radiusPaint);
        canvas.drawArc(oval, -90, secondDegree, false, radiusPaint);
    }

    private void getTime(){
        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        millisecond = calendar.get(Calendar.MILLISECOND);

    }
    private void getDegree(){
        getTime();
        secondDegree = (float)((second * 1000 + millisecond) / (60.0 * 1000) * 360);
    }

}
