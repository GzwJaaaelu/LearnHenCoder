package com.jaaaelu.gzw.learn.henCoder.customizeVIewSeasonOne;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.jaaaelu.gzw.learn.henCoder.R;

public class LearnOnDrawView extends View {
    private Paint mPaint = new Paint();

    public LearnOnDrawView(Context context) {
        super(context);
    }

    public LearnOnDrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LearnOnDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LearnOnDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        drawColor(canvas);

        drawCircle(canvas);
    }

    private void drawColor(Canvas canvas) {
//        canvas.drawColor(Color.parseColor("#3F51B5"));

        canvas.drawARGB(100, 100, 200, 100);
    }

    private void drawCircle(Canvas canvas) {
        mPaint.setColor(getContext().getResources().getColor(R.color.colorPrimary));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(15);

        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();

        canvas.drawCircle(dm.widthPixels / 2, dm.heightPixels / 2, 200, mPaint);
    }
}
