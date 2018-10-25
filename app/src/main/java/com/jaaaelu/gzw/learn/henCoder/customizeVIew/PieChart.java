package com.jaaaelu.gzw.learn.henCoder.customizeVIew;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jaaaelu.gzw.learn.henCoder.util.Utils;

/**
 * Created by Gzw on 2018/10/23 0023.
 */

public class PieChart extends View {
    private static final int RADIUS = (int) Utils.dp2px(120);
    private static final float LENGTH = Utils.dp2px(20);
    private static final int PULLED_OUT_INDEX = 2;
    //  ANTI_ALIAS_FLAG 用来防锯齿
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //  范围
    private RectF mBounds = new RectF();
    private int[] angles = {60, 100, 120, 80};
    private int[] colors = {Color.parseColor("#2979FF"), Color.parseColor("#C2185B"),
            Color.parseColor("#009688"), Color.parseColor("#FF8F00")};

    public PieChart(Context context) {
        super(context);
    }

    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PieChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PieChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBounds.set(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int currentAngle = 0;
        for (int i = 0; i < 4; i++) {
            mPaint.setColor(colors[i]);
            canvas.save();
            Log.e("Pie", "i -> " + i + " /2 " + (currentAngle + angles[i] / 2));
            Log.e("Pie", "i -> " + i + "  " + (currentAngle + angles[i]));
            Log.e("Pie", "i -> " + i + " /3 " + (currentAngle + angles[i] / 3));
            if (i == PULLED_OUT_INDEX) {
                canvas.translate((float) Math.cos(Math.toRadians(currentAngle + angles[i] / 2)) * LENGTH,
                        (float) Math.sin(Math.toRadians(currentAngle + angles[i] / 2)) * LENGTH);
            }
            canvas.drawArc(mBounds, currentAngle, angles[i], true, mPaint);
            canvas.restore();
            currentAngle += angles[i];
        }
    }
}
