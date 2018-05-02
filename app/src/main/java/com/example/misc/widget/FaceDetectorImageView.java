package com.example.misc.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.example.misc.R;

/**
 * Created by Administrator on 2018/3/13.
 */

public class FaceDetectorImageView extends AppCompatImageView {
    private FaceDetector faceDetector;
    private FaceDetector.Face[] faces;
    private Bitmap bitmap;
    private int maxFaces = 10;
    private int realFaces = 0;
    private Paint paint;

    public FaceDetectorImageView(Context context) {
        this(context, null);
    }

    public FaceDetectorImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FaceDetectorImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.faces, options);
        faces = new FaceDetector.Face[maxFaces];
        faceDetector = new FaceDetector(bitmap.getWidth(), bitmap.getHeight(), maxFaces);
        realFaces = faceDetector.findFaces(bitmap, faces);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getResources().getColor(R.color.colorAccent));
        paint.setStrokeWidth(5);
        setImageBitmap(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < realFaces; i++) {
            FaceDetector.Face face = faces[i];
            float distance = face.eyesDistance();
            PointF mid = new PointF();
            face.getMidPoint(mid);
            canvas.drawRect(mid.x - distance / 2,
                    mid.y - distance / 2,
                    mid.x + distance / 2,
                    mid.y + distance / 2, paint);
        }
    }
}
