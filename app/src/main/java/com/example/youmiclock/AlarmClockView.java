package com.example.youmiclock;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by hao on 17-2-18.
 */
public class AlarmClockView extends View {

    private static final String TAG = "AlarmClockView";

    private int circleRadius;
    private int circleColor;
    private int cicleChangeColor;

    private Paint paint;

    public AlarmClockView(Context context) {
        super(context);
        initCiclePaint();
    }

    public AlarmClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AlarmClockView);
        circleRadius = typedArray.getDimensionPixelSize(R.styleable.AlarmClockView_ACVCircile_radius, 50);
        circleColor = typedArray.getColor(R.styleable.AlarmClockView_ACVCircle_color, Color.parseColor("#cfd8dc"));
        cicleChangeColor = typedArray.getColor(R.styleable.AlarmClockView_ACVCircle_change_color, Color.WHITE);
        typedArray.recycle();
        initCiclePaint();
    }

    public AlarmClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);



        initCiclePaint();
    }

    private void initCiclePaint(){

        Log.d(TAG, "initCiclePaint: " + circleColor + "*****" + circleRadius);

        paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(circleColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth()/ 2, getHeight()/ 2);
        canvas.drawCircle(0, 0, circleRadius, paint);
    }
}
