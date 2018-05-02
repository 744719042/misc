package com.example.misc.widget;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ViewTreeObserver;

import com.example.misc.R;

public class ScaleImageView extends AppCompatImageView implements
        ScaleGestureDetector.OnScaleGestureListener, ViewTreeObserver.OnGlobalLayoutListener {
    private static final String TAG = "ScaleImageView";

    private ScaleGestureDetector scaleDetector;
    private Matrix matrix;
    private BitmapDrawable bitmapDrawable;
    private float[] value = new float[9];
    private boolean mIsFirstInit = true;
    private RectF originRect = new RectF();


    public ScaleImageView(Context context) {
        this(context, null);
    }

    public ScaleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setScaleType(ScaleType.MATRIX);
        setImageResource(R.drawable.dog);
        bitmapDrawable = (BitmapDrawable) getDrawable();
        scaleDetector = new ScaleGestureDetector(getContext(), this);
        matrix = new Matrix();
        originRect.left = 0;
        originRect.top = 0;
        originRect.right = bitmapDrawable.getIntrinsicWidth();
        originRect.bottom = bitmapDrawable.getIntrinsicHeight();
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        float scaleFactor = detector.getScaleFactor();
        Log.d(TAG, "onScale scaleFactor = " + scaleFactor);
        float scale = getScaleValue();
        matrix.setScale(scaleFactor * scale,
                scaleFactor * scale, detector.getFocusX(), detector.getFocusY());
        adjustBorder(matrix);
        setImageMatrix(matrix);
        return true;
    }

    private void adjustBorder(Matrix matrix) {
        RectF rectF = getMatrixRect();
        float dx = 0, dy = 0;
        if (rectF.width() >= getWidth()) {
            if (rectF.left > 0) {
                dx = -rectF.left;
            }

            if (rectF.right < getWidth()) {
                dx = getWidth() - rectF.right;
            }
        }

        if (rectF.width() < getWidth()) {
            dx = rectF.width() / 2 + getWidth() / 2 - rectF.right;
        }

        if (rectF.height() >= getHeight()) {
            if (rectF.top > 0) {
                dy = -rectF.top;
            }

            if (rectF.bottom < getHeight()) {
                dy = getHeight() - rectF.bottom;
            }
        }

        if (rectF.height() < getHeight()) {
            dy = getHeight() / 2f - rectF.bottom + rectF.height() / 2f;
        }

        matrix.postTranslate(dx, dy);
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        Log.d(TAG, "onScaleBegin");
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
        Log.d(TAG, "onScaleEnd");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleDetector.onTouchEvent(event);
        return true;
    }

    public float getScaleValue() {
        Matrix matrix = getImageMatrix();
        matrix.getValues(value);
        return value[Matrix.MSCALE_X];
    }

    @Override
    public void onGlobalLayout() {
        Log.d(TAG, "onGlobalLayout");
        if (mIsFirstInit) {
            int originWidth = bitmapDrawable.getIntrinsicWidth();
            int originHeight = bitmapDrawable.getIntrinsicHeight();

            int viewWidth = getWidth();
            int viewHeight = getHeight();

            float scale = 0;
            if (originWidth > viewWidth && originHeight > viewHeight ||
                    originWidth < viewWidth && originHeight < viewHeight) {
                scale = Math.min((float) viewWidth / originWidth, (float) viewHeight / originHeight);
            } else if (originWidth > viewWidth) {
                scale = viewWidth * 1.0f / originWidth;
            } else {
                scale = viewHeight * 1.0f / originHeight;
            }

            matrix.setScale(scale, scale, viewWidth / 2, viewHeight / 2);
            matrix.preTranslate((viewWidth - originWidth) / 2, (viewHeight - originHeight) / 2);
            setImageMatrix(matrix);
            mIsFirstInit = false;
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }

    public RectF getMatrixRect() {
        originRect.set(0,0, bitmapDrawable.getIntrinsicWidth(), bitmapDrawable.getIntrinsicHeight());
        matrix.mapRect(originRect);
        return originRect;
    }
}
