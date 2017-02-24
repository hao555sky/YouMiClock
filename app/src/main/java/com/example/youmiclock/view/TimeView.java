package com.example.youmiclock.view;

/**
 * Created by hao on 17-2-15.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;


/**
 * Created by hao on 17-2-13.
 */
public class TimeView extends View {

    private static final String TAG = "TimeView";

    private Context mContext;

    private Paint mPaint;

    private FloatingActionButton floatingAddAlarmButton;

    public TimeView(Context context){
        super(context);
        this.mContext = context;
        initPain();
    }

    public TimeView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        this.mContext = context;
        initPain();
    }

    private Handler handle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    invalidate();
                    handle.sendEmptyMessageDelayed(0, 1000);
                    break;
                default:break;
            }
        }
    };
    public void initPain(){

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(0);

        handle.sendEmptyMessage(0);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStrokeWidth(2);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 3, mPaint);

        mPaint.setStrokeWidth(5);
        canvas.drawPoint(getWidth() / 2, getHeight() / 2, mPaint);

        drawLines(canvas);

        drawText(canvas);

        drawTime(canvas);
    }

    private void drawTime(Canvas canvas) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

//        int hourAngle = -90 + hour * 30 + minute * 20;
//        int minuteAngle = - 90 + minute * 6 + second / 60 * 0.5;

        int hourAngle = hour * 30 + minute / 2;
        //分针走过的角度
        int minuteAngle = minute * 6 + second / 10;
        //秒针走过的角度
        int secondAngle = second * 6;

        canvas.rotate(-90);

        canvas.rotate(hourAngle);
        canvas.drawLine(0, 0, 50, 0, mPaint);
        canvas.save();
        canvas.restore();
        canvas.rotate(-hourAngle);

        canvas.rotate(minuteAngle);
        canvas.drawLine(0, 0, 70, 0, mPaint);
        canvas.save();
        canvas.restore();
        canvas.rotate(-minuteAngle);

        canvas.rotate(secondAngle);
        canvas.drawLine(0, 0, 80, 0, mPaint);
        canvas.save();
        canvas.restore();
        canvas.rotate(secondAngle);
    }

    private void drawLines(Canvas canvas){
        mPaint.setStrokeWidth(1);
        canvas.translate(getWidth() / 2, getHeight() / 2);
        for(int i = 0; i < 360; i++){
            if(i % 30 == 0){
                canvas.drawLine(getWidth() / 3 - 25, 0, getWidth() / 3, 0, mPaint);
            }else if(i % 6 == 0){
                canvas.drawLine(getWidth() / 3 - 14, 0, getWidth() / 3, 0, mPaint);
            }else {
                canvas.drawLine(getWidth() / 3 - 9, 0, getWidth() / 3, 0, mPaint);
            }
            canvas.rotate(1);
        }
    }

    private void drawText(Canvas canvas){
        mPaint.setTextSize(25);

        // 自己写的数字算法
//        for(int i = 0; i <= 12; i++){
//            Rect textBounds = new Rect();
//            if(i == 0){
//                mPaint.getTextBounds(12 + "", 0, (12 + "").length(), textBounds);
//                mPaint.getTextBounds("12", 0, "12".length(), textBounds);
//                canvas.drawText("12", (float)((getWidth() / 3 - 25 - 15) *
//                        Math.cos(Math.toRadians(i * 30))) - textBounds.width() / 2,
//                        (float)((getWidth() / 3 - 25 - 15) * Math.sin(Math.toRadians(i * 30))) +
//                                textBounds.height() / 2, mPaint);
//            }else {
//                mPaint.getTextBounds(i + "", 0, (i + "").length(), textBounds);
//                mPaint.getTextBounds(i + "", 0, (i + "").length(), textBounds);
//                canvas.drawText(i + "", (float)((getWidth() / 3 - 25 - 15) *
//                                Math.cos(Math.toRadians(i * 30))) - textBounds.width() / 2,
//                        (float)((getWidth() / 3 - 25 - 15) * Math.sin(Math.toRadians(i * 30))) +
//                                textBounds.height() / 2, mPaint);
//            }
//
//        }
        for (int i = 0; i < 12; i++) {
            if (i == 0) {
                drawNum(canvas, i * 30, 12 + "", mPaint);
            } else {
                drawNum(canvas, i * 30, i + "", mPaint);
            }
        }
    }

    private void drawNum(Canvas canvas, int degree, String text, Paint paint) {
        Rect textBound = new Rect();
        paint.getTextBounds(text, 0, text.length(), textBound);
        canvas.rotate(degree);
        canvas.translate(0, 50 - getWidth() / 3);//这里的50是坐标中心距离时钟最外边框的距离，当然你可以根据需要适当调节
        canvas.rotate(-degree);
        canvas.drawText(text, -textBound.width() / 2,
                textBound.height() / 2, paint);
        canvas.rotate(degree);
        canvas.translate(0, getWidth() / 3 - 50);
        canvas.rotate(-degree);
    }
}
