package com.example.misc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.example.misc.widget.EventHelper;

public class EventActivity extends AppCompatActivity {
    private static final String TAG = "TouchEventTest";
    private static final String CLASS = "EventActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
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
