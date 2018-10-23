package com.jaaaelu.gzw.learn.henCoder.customizeVIew;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.jaaaelu.gzw.learn.henCoder.util.Utils;

/**
 * Created by Gzw on 2018/10/23 0023.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class Dashboard extends View {
    //  不画的弧度
    private static final int ANGLE = 120;
    //  仪表盘半径
    private static final int RADIUS = 200;
    private static final float LENGTH = Utils.dp2px(90);
    //  ANTI_ALIAS_FLAG 用来防锯齿
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //  单个刻度
    private Path mDash = new Path();
    //  弧形
    private Path arc = new Path();

    private PathDashPathEffect mPathEffect;

    {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(Utils.dp2px(2));
        mDash.addRect(0, 0, Utils.dp2px(2), Utils.dp2px(10), Path.Direction.CW);
        arc.addArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, 90 + ANGLE / 2, 360 - ANGLE);
        //  用来测量弧形长度
        PathMeasure pathMeasure = new PathMeasure(arc, false);
        //  PathDashPathEffect 来画刻度
        //  mDash 是小长方形，也就是单个刻度
        //  advance 是每个刻度之间空多少
        //  phase 是开始之前要不要留空
        mPathEffect = new PathDashPathEffect(mDash, (pathMeasure.getLength() - Utils.dp2px(2)) / 20, 0, PathDashPathEffect.Style.ROTATE);
    }

    public Dashboard(Context context) {
        super(context);
    }

    public Dashboard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Dashboard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Dashboard(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //  第一遍是为了画弧形
        //  开始角度 90 + ANGLE / 2，扫过角度 360 - ANGLE
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, 90 + ANGLE / 2, 360 - ANGLE,
                false, mPaint);
        //  第二遍加上了 PathEffect 为了画刻度
        mPaint.setPathEffect(mPathEffect);
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, 90 + ANGLE / 2, 360 - ANGLE,
                false, mPaint);
        mPaint.setPathEffect(null);

        //  画指针
        //  用到三角函数
        canvas.drawLine(getWidth() / 2, getHeight() / 2, (float) Math.cos(Math.toRadians(getAngelFormMark(5))) * LENGTH + getWidth() / 2,
                (float) Math.sin(Math.toRadians(getAngelFormMark(5))) * LENGTH + getHeight() / 2, mPaint);
    }

    private int getAngelFormMark(int mark) {
        //  mark 是刻度
        return (int) (90 + (float) ANGLE / 2 + (360 - (float) ANGLE) / 20 * mark);
    }
}
