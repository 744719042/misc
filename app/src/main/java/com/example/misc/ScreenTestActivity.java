package com.example.misc;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DialogTitle;
import android.util.Log;
import android.view.View;

public class ScreenTestActivity extends AppCompatActivity {
    private static final String TAG = "ScreenTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_test);
        Log.e(TAG, "onCreate() orientation = " + getOrientation());
    }
    public void rotateActivity(View view) {
        setRequestedOrientation(!isFullScreen() ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    public boolean isFullScreen() {
        return this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    private String getOrientation() {
        return isFullScreen() ? "全屏样式" : "竖屏样式";
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e(TAG, "onConfigurationChanged() orientation = " + getOrientation());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy() orientation = " + getOrientation());
    }
}
