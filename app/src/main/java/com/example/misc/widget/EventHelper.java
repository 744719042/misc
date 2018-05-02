package com.example.misc.widget;

import android.view.MotionEvent;

public class EventHelper {
    public static String parse(int event) {
        switch (event) {
            case MotionEvent.ACTION_DOWN:
                return "ACTION_DOWN";
            case MotionEvent.ACTION_MOVE:
                return "ACTION_MOVE";
            case MotionEvent.ACTION_UP:
                return "ACTION_UP";
            case MotionEvent.ACTION_CANCEL:
                return "ACTION_CANCEL";
            default:
                return "";
        }
    }
}
