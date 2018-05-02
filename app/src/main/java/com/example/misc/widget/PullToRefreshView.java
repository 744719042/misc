package com.example.misc.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class PullToRefreshView extends ListView {

    public PullToRefreshView(Context context) {
        this(context, null);
    }

    public PullToRefreshView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullToRefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
