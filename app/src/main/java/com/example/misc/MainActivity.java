package com.example.misc;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_WRITE_STORAGE = 0x111;
    private Button faceDetector;
    private Button zip;
    private Button okhttp;
    private Button gson;
    private Button snippet;
    private Button scale;
    private Button expand;
    private Button event;
    private Button refresh;
    private Button drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        faceDetector = (Button) findViewById(R.id.faceDetector);
        faceDetector.setOnClickListener(this);
        zip = (Button) findViewById(R.id.zip);
        zip.setOnClickListener(this);
        okhttp = (Button) findViewById(R.id.okhttp);
        okhttp.setOnClickListener(this);
        gson = (Button) findViewById(R.id.gson);
        gson.setOnClickListener(this);
        snippet = (Button) findViewById(R.id.snippet);
        snippet.setOnClickListener(this);
        scale = (Button) findViewById(R.id.scale);
        scale.setOnClickListener(this);
        expand = (Button) findViewById(R.id.expand);
        expand.setOnClickListener(this);
        event = (Button) findViewById(R.id.event);
        event.setOnClickListener(this);
        refresh = (Button) findViewById(R.id.refresh);
        refresh.setOnClickListener(this);
        drawable = (Button) findViewById(R.id.drawable);
        drawable.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_STORAGE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_WRITE_STORAGE && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "权限申请成功", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == faceDetector) {
            Intent intent = new Intent(this, FaceDetectorActivity.class);
            startActivity(intent);
        } else if (v == zip) {
            Intent intent = new Intent(this, ZipActivity.class);
            startActivity(intent);
        } else if (v == okhttp) {
            Intent intent = new Intent(this, OkHttpActivity.class);
            startActivity(intent);
        } else if (v == gson) {
            Intent intent = new Intent(this, GsonActivity.class);
            startActivity(intent);
        } else if (v == snippet) {
            Intent intent = new Intent(this, SnippetActivity.class);
            startActivity(intent);
        } else if (v == scale) {
            Intent intent = new Intent(this, ScaleActivity.class);
            startActivity(intent);
        } else if (v == expand) {
            Intent intent = new Intent(this, TextViewActivity.class);
            startActivity(intent);
        } else if (v == event) {
            Intent intent = new Intent(this, EventActivity.class);
            startActivity(intent);
        } else if (v == refresh) {
            Intent intent = new Intent(this, RefreshActivity.class);
            startActivity(intent);
        } else if (v == drawable) {
            Intent intent = new Intent(this, DrawableActivity.class);
            startActivity(intent);
        }
    }
}
