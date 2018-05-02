package com.example.misc.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class MyViewGroup extends ViewGroup {
    private static final String TAG = "TouchEventTest";
    private static final String CLASS = "MyViewGroup";
    public MyViewGroup(Context context) {
        this(context, null);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, CLASS + " onTouch() event = " + EventHelper.parse(event.getActionMasked()));
                boolean result = false;
                Log.d(TAG, CLASS + " onTouch() result = " + result);
                return result;
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            for (int i = 0; i < getChildCount(); i++) {
                View view = getChildAt(i);
                view.layout(100, 100, 100 + view.getMeasuredWidth(), 100 + view.getMeasuredHeight());
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.d(TAG, CLASS + " onInterceptTouchEvent() event = " + EventHelper.parse(event.getActionMasked()));
        boolean result = super.onInterceptTouchEvent(event);
        int x = (int) event.getX();
        if (x > 200) {
            result = true;
        }
        Log.d(TAG, CLASS + " onInterceptTouchEvent() result = " + result);
        return result;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, CLASS + " dispatchTouchEvent() event = " + EventHelper.parse(event.getActionMasked()));
        boolean result = super.dispatchTouchEvent(event);
        Log.d(TAG, CLASS + " dispatchTouchEvent() result = " + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, CLASS + " onTouchEvent() event = " + EventHelper.parse(event.getActionMasked()));
        boolean result = super.onTouchEvent(event);
        Log.d(TAG, CLASS + " onTouchEvent() result = " + result);
        return result;
    }
}
