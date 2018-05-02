package com.example.misc;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SnippetActivity extends AppCompatActivity implements View.OnClickListener {
    private Button backHome;
    private Button addIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snippet);
        backHome = (Button) findViewById(R.id.backHome);
        backHome.setOnClickListener(this);
        addIcon = (Button) findViewById(R.id.addIcon);
        addIcon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == backHome) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        } else if (v == addIcon) {
            Intent intent = new Intent();
            intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "测试");
            Parcelable icon = Intent.ShortcutIconResource.fromContext(this, R.drawable.faces);
            intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
            Intent openIntent = new Intent(this, MainActivity.class);
            intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, openIntent);
            sendBroadcast(intent);
        }
    }
}
