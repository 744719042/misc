package com.example.misc.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.misc.R;

public class ExpandableTextView extends LinearLayout {
    private TextView mText;
    private ImageView mArrow;
    private boolean mIsExpanded = true;

    public ExpandableTextView(Context context) {
        this(context, null);
    }

    public ExpandableTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(LinearLayout.VERTICAL);
        mText = new TextView(getContext());
        LinearLayout.LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mText.setText(R.string.long_text);
        mText.setMaxLines(3);
        mText.setEllipsize(TextUtils.TruncateAt.END);
        addView(mText, params);

        mArrow = new ImageView(getContext());
        mArrow.setImageResource(R.drawable.ic_arrow_downward);
        LinearLayout.LayoutParams arrowParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        arrowParams.gravity = Gravity.CENTER_HORIZONTAL;
        addView(mArrow, arrowParams);
        mArrow.setOnClickListener(view -> {
            if (mIsExpanded) {
                mText.setMaxLines(3);
                mText.setEllipsize(TextUtils.TruncateAt.END);
                mArrow.setImageResource(R.drawable.ic_arrow_downward);
            } else {
                mText.setMaxLines(Integer.MAX_VALUE);
                mArrow.setImageResource(R.drawable.ic_arrow_upward);
            }

            mIsExpanded = !mIsExpanded;
        });
    }
}
