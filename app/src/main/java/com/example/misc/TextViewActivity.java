package com.example.misc;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;

public class TextViewActivity extends AppCompatActivity {
    private TextView textView;
    private SpannableString collapsedString;
    private SpannableString expandedString;
    private boolean expanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        textView = (TextView) findViewById(R.id.text);
        textView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            if (expandedString == null) {
                String text = textView.getText().toString();
                String alltext = text + "收起查看<<";
                expandedString = new SpannableString(alltext);
                expandedString.setSpan(new TextClickSpan(textView), alltext.length() - 6, alltext.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

                String ellipse = TextUtils.ellipsize(text, textView.getPaint(), 2.5f * textView.getWidth(), TextUtils.TruncateAt.END).toString();
                String result = ellipse + "查看更多>>";
                collapsedString = new SpannableString(result);
                collapsedString.setSpan(new TextClickSpan(textView), result.length() - 6, result.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                textView.setMovementMethod(LinkMovementMethod.getInstance());
                textView.setHighlightColor(Color.parseColor("#36969696"));
                textView.setText(collapsedString);
            }
        });
    }

    private class TextClickSpan extends ClickableSpan {
        private TextView textView;

        public TextClickSpan(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View widget) {
            if (expanded) {
                textView.setText(collapsedString);
            } else {
                textView.setText(expandedString);
            }
            expanded = !expanded;
        }
    }
}
