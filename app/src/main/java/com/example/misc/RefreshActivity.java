package com.example.misc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class RefreshActivity extends AppCompatActivity implements View.OnClickListener {
    private Button incPadding;
    private Button incMargin;
    private Button decPadding;
    private Button decMargin;
    private ImageView pool;
    private ImageView moon;
    private int paddingTop = 0;
    private int marginTop = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        incPadding = (Button) findViewById(R.id.inc_padding_button);
        decPadding = (Button) findViewById(R.id.dec_padding_button);
        incMargin = (Button) findViewById(R.id.inc_margin_button);
        decMargin = (Button) findViewById(R.id.dec_margin_button);
        pool = (ImageView) findViewById(R.id.pool);
        moon = (ImageView) findViewById(R.id.moon);
        incMargin.setOnClickListener(this);
        decMargin.setOnClickListener(this);
        incPadding.setOnClickListener(this);
        decPadding.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == incPadding) {
            paddingTop += 10;
            pool.setPadding(0, paddingTop, 0, 0);
        } else if (v == decPadding) {
            paddingTop -= 10;
            pool.setPadding(0, paddingTop, 0, 0);
        } else if (v == incMargin) {
            marginTop += 10;
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) moon.getLayoutParams();
            layoutParams.topMargin = marginTop;
            moon.setLayoutParams(layoutParams);
        } else if (v == decMargin) {
            marginTop -= 10;
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) moon.getLayoutParams();
            layoutParams.topMargin = marginTop;
            moon.setLayoutParams(layoutParams);
        }
    }
}
