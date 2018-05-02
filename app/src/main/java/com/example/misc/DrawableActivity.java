package com.example.misc;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class DrawableActivity extends AppCompatActivity {
    private ImageView clipImg;
    private ImageView scaleImg;
    private ImageView levelImg;
    private ImageView transiteImg;
    private Button addLevel;
    private Button decLevel;
    private Button startTransite;
    private int level = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);

        levelImg = (ImageView) findViewById(R.id.level_img);
        levelImg.setImageLevel(98);
        clipImg = (ImageView) findViewById(R.id.clip_img);
        clipImg.setImageLevel(5000);

        scaleImg = (ImageView) findViewById(R.id.scale_img);
//        Drawable drawable = getResources().getDrawable(R.drawable.scale_bg);
//        ScaleDrawable scaleDrawable = new ScaleDrawable(drawable, Gravity.LEFT, 1f, -1f);
//        scaleImg.setImageDrawable(scaleDrawable);
        scaleImg.getDrawable().setLevel(10000);

        addLevel = (Button) findViewById(R.id.addLevel);
        decLevel = (Button) findViewById(R.id.decLevel);
        addLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level += 1000;
                clipImg.setImageLevel(level);
                scaleImg.getDrawable().setLevel(level);
            }
        });

        decLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level -= 1000;
                clipImg.setImageLevel(level);
                scaleImg.getDrawable().setLevel(level);
            }
        });

        transiteImg = (ImageView) findViewById(R.id.transite_img);
        startTransite = (Button) findViewById(R.id.transite);
        startTransite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionDrawable transitionDrawable = (TransitionDrawable) transiteImg.getDrawable();
                transitionDrawable.startTransition(300);
            }
        });

    }
}
