package com.example.misc.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.misc.R;

public class MyView extends View {
    private static final String TAG = "TouchEventTest";
    private static final String CLASS = "MyView";
    private Paint mPaint;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint.setTextSize(50);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, CLASS + " onTouch() event = " + EventHelper.parse(event.getActionMasked()));
                boolean result = false;
                Log.d(TAG, CLASS + " onTouch() result = " + result);
                return false;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, CLASS + " dispatchTouchEvent() event = " + EventHelper.parse(event.getActionMasked()));
        boolean result = super.dispatchTouchEvent(event);
        Log.d(TAG, CLASS + " dispatchTouchEvent() result = " + result);
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("我是子视图", 10, 150, mPaint);
        mPaint.setStrokeWidth(6);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0, 0, 300, 300, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, CLASS + " onTouchEvent() event = " + EventHelper.parse(event.getActionMasked()));
        boolean result = true; // super.onTouchEvent(event);
        Log.d(TAG, CLASS + " onTouchEvent() result = " + result);
        return result;
    }
}
