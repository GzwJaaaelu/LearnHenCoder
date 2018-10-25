package com.jaaaelu.gzw.learn.henCoder.customizeVIew.two;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.jaaaelu.gzw.learn.henCoder.util.Utils;

/**
 * Created by Gzw on 2018/10/25 0025.
 */

public class SportsView extends View {
    private static final int RADIUS = (int) Utils.dp2px(111);
    private static final int CIRCLE_COLOR = Color.parseColor("#90A4AE");
    private static final int ARC_COLOR = Color.parseColor("#3F51B5");
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Rect mTextArea = new Rect();
    private Paint.FontMetrics mFontMetrics = new Paint.FontMetrics();

    {
        mPaint.setTextSize(Utils.dp2px(27));
        //  修改字体
        mPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Quicksand-Regular.ttf"));
        mPaint.getFontMetrics(mFontMetrics);
    }

    public SportsView(Context context) {
        super(context);
    }

    public SportsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SportsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SportsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(CIRCLE_COLOR);
        mPaint.setStrokeWidth(Utils.dp2px(10));
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, RADIUS, mPaint);

        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(ARC_COLOR);
        mPaint.setStrokeWidth(Utils.dp2px(10));
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                -90, 240, false, mPaint);

        //  写文字的时候 style 的不能是 STROKE，STROKE 是画线
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);
        //  由于基线，我们需要手动调节本文显示位置
//        //  方式一，非常居中，用于固定文字效果更小，TextArea 是文字范围，然后可以获得顶部和底部位置
//        mPaint.getTextBounds("绘制固定本文", 0, "绘制固定本文".length(), mTextArea);
//        int offset = (mTextArea.bottom + mTextArea.top) / 2;
//        canvas.drawText("绘制b固定p本文", getWidth() / 2, getHeight() / 2 - offset, mPaint);
        //  方式二，用于文字经常变化，ascent 与 descent 是大部分文字对应的上线和下线位置
        float offset = (mFontMetrics.ascent + mFontMetrics.descent) / 2;
        canvas.drawText("绘制固定本文", getWidth() / 2, getHeight() / 2 - offset, mPaint);
    }
}
